package com.luishenrique.cutecatsgallery.ui.adapter

import android.R.attr
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.luishenrique.domain.entity.Image
import kotlinx.android.synthetic.main.item_list.view.*
import android.widget.Toast
import android.graphics.drawable.Drawable
import android.graphics.drawable.BitmapDrawable
import com.bumptech.glide.request.target.CustomTarget
import android.os.Environment
import com.bumptech.glide.request.transition.Transition
import com.luishenrique.cutecatsgallery.R
import java.io.File
import android.graphics.Bitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception
import com.bumptech.glide.request.target.SimpleTarget
import android.R.attr.resource

class HomeAdapter(
    private val context: Context,
    private val verifyPermissions: () -> Boolean
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var images: List<Image> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val icon = view.xIcon
        private val username = view.xUsername
        private val score = view.xScore
        private val downloadImage = view.xDownloadImage

        fun bind(image: Image) {
            username.text = image.username
            score.text = image.score.toString()


            Glide.with(context)
                .load(image.images?.get(0)?.link)
                .override(200, 240)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_cat_loading)
                .error(R.drawable.ic_cat_loading)
                .into(icon)

            downloadImage.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    downloadImage(image.images?.get(0)?.link)
                }
            }
        }

        private fun downloadImage(imageURL: String?) {
                if (imageURL.isNullOrBlank() || !verifyPermissions()) return
                val dirPath: String = run {
                    Environment.getExternalStorageDirectory().absolutePath +
                    "/" + context.getString(R.string.app_name_storage)
                }
                val dir = File(dirPath)
                val fileName: String = imageURL.substring(
                    imageURL.lastIndexOf('/') + 1
                )

                Glide.with(context)
                    .load(imageURL)
                    .into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            val bitmap: Bitmap = (resource as BitmapDrawable).bitmap
                            Toast.makeText(
                                context,
                                "Salvando imagem...",
                                Toast.LENGTH_SHORT
                            ).show()
                            saveImage(bitmap, dir, fileName)
                        }
                        override fun onLoadCleared(placeholder: Drawable?) {}
                        override fun onLoadFailed(errorDrawable: Drawable?) {}
                })
        }

        private fun saveImage(image: Bitmap, storageDir: File, imageFileName: String) {
            val theFolderExists = storageDir.exists()
            if (!storageDir.exists()) {
                val successDirCreated = storageDir.mkdir()
                if (!successDirCreated) {
                    Toast.makeText(
                        context,
                        "Falha ao criar pasta!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
            }
            if (theFolderExists) {
                val imageFile = File(storageDir, imageFileName)
                val savedImagePath = imageFile.absolutePath
                try {
                    val fOut: OutputStream = FileOutputStream(imageFile)
                    image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                    fOut.close()
                    Toast.makeText(context, "Imagem Salva!\n$savedImagePath", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Erro ao salvar imagem!",
                        Toast.LENGTH_SHORT
                    ).show()
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount() = images.size
}