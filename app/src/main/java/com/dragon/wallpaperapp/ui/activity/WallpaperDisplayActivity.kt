package com.dragon.wallpaperapp.ui.activity

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import kotlinx.android.synthetic.main.activity_wallpaper.*


/**
 * Created by D22434 on 2017/12/6.
 */
class WallpaperDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        init()
    }

    private fun init() {

        var url = intent.getStringExtra("url")

        GlideApp.with(this)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(cropView)
        btn_set_wallpaper.setOnClickListener { v ->
            val mWallManager = WallpaperManager.getInstance(this)
            GlideApp.with(this)
                    .asBitmap()
                    .load(url)
                    .into(object : SimpleTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>) {
                            mWallManager.setBitmap(resource)
                        }
                    })
            Toast.makeText(this, "设置壁纸中", Toast.LENGTH_SHORT).show()
        }
    }


}