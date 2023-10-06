package com.luishenrique.cutecatsgallery.ui.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luishenrique.cutecatsgallery.R
import com.luishenrique.cutecatsgallery.databinding.ActivityHomeBinding
import com.luishenrique.cutecatsgallery.ui.adapter.HomeAdapter
import com.luishenrique.cutecatsgallery.ui.viewmodel.HomeViewModel
import com.luishenrique.domain.entity.Image
import org.koin.android.viewmodel.ext.android.viewModel
import com.roger.catloadinglibrary.CatLoadingView

class HomeActivity : AppCompatActivity() {

    private val mViewModel: HomeViewModel by viewModel()
    private lateinit var mBinding: ActivityHomeBinding
    private val mProgressBarCat: CatLoadingView = CatLoadingView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mProgressBarCat.setBackgroundColor(R.color.primary_color)
        mViewModel.findAllCats()
        setObservables()

        with(mBinding) {
            xTryAgain.setOnClickListener {
                mViewModel.findAllCats()
            }
            xRefresh.setOnClickListener {
                mViewModel.findAllCats()
            }
            xList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(1)) {
                        this@with.xRefresh.visibility = View.VISIBLE
                    } else {
                        this@with.xRefresh.visibility = View.GONE
                    }
                }
            })
        }
    }

    private fun setObservables() {
        mViewModel.gallery.observe(this) {
            setRecyclerView(it.filterToPhoto())
        }
        mViewModel.loading.observe(this) {
            if (it) {
                mProgressBarCat.show(supportFragmentManager, "cat")
            } else {
                mProgressBarCat.dismiss()
            }
        }
        mViewModel.stateHome.observe(this) {
            mBinding.xList.visibility = it
            if (it == View.INVISIBLE) {
                mBinding.xMessage.visibility = View.VISIBLE
                mBinding.xTryAgain.visibility = View.VISIBLE
                mBinding.xIconErrorConnection.visibility = View.VISIBLE
                mBinding.xRefresh.visibility = View.GONE
            } else {
                mBinding.xMessage.visibility = View.GONE
                mBinding.xTryAgain.visibility = View.GONE
                mBinding.xIconErrorConnection.visibility = View.GONE
            }
        }
    }

    private fun setRecyclerView(images: List<Image>) {
        with(mBinding.xList) {
            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            adapter = HomeAdapter(this@HomeActivity, ::verifyPermissions).apply {
                this.images = images
            }
        }
    }

    private fun verifyPermissions(): Boolean {
        val permissionExternalMemory = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permissionExternalMemory != PackageManager.PERMISSION_GRANTED) {
            val storagePermission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, storagePermission, 1)
            return false
        }
        return true
    }
}