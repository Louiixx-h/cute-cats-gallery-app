package com.luishenrique.cutecatsgallery.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            xRefresh.visibility = View.GONE
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
            setRecyclerView(it.data)
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
            mBinding.xRefresh.visibility = it
            if (it == View.INVISIBLE) {
                mBinding.xMessage.visibility = View.VISIBLE
                mBinding.xTryAgain.visibility = View.VISIBLE
                mBinding.xIconErrorConnection.visibility = View.VISIBLE
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
            adapter = HomeAdapter(this@HomeActivity).apply {
                this.images = images as MutableList<Image>
            }
        }
    }
}