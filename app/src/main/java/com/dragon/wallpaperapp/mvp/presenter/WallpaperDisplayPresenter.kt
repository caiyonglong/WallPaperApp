package com.dragon.wallpaperapp.mvp.presenter

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.contract.WallpaperDisplayContract
import com.dragon.wallpaperapp.mvp.model.Wallpaper

/**
 * Created by D22434 on 2017/12/7.
 */
class WallpaperDisplayPresenter : WallpaperDisplayContract.Presenter {

    lateinit var mView: WallpaperDisplayContract.View
    var context: Context? = null
    var mBitmap: Bitmap? = null

    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        mView.setFullScreen()
    }
    private val mShowPart2Runnable = Runnable {
        mView.setNormalScreen()
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }


    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attachView(view: WallpaperDisplayContract.View) {
        mView = view
        context = view as Context
        mVisible = true
    }

    override fun detachView() {

    }

    override fun loadData(wallpapers: List<Wallpaper>, position: Int) {
        GlideApp.with(context)
                .asBitmap()
                .load(wallpapers[position].img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>) {
                        mBitmap = resource
                        mView.setBitmap(mBitmap)
                    }
                })
    }

    override fun setWallpaper() {
        val mWallManager = WallpaperManager.getInstance(context)
        mWallManager.setBitmap(mBitmap)
    }

    override fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        mView.hide()
        mVisible = false
        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, 300)
    }

    private fun show() {
        // Show the system bar
        mView.show()
        mVisible = true
        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, 300)
    }

    fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }


}