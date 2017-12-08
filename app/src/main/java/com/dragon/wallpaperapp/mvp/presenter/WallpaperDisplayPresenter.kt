package com.dragon.wallpaperapp.mvp.presenter

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.contract.WallpaperDisplayContract
import com.dragon.wallpaperapp.mvp.model.Wallpaper
import java.io.IOException

/**
 * Created by D22434 on 2017/12/7.
 */
class WallpaperDisplayPresenter : WallpaperDisplayContract.Presenter {

    lateinit var mView: WallpaperDisplayContract.View
    var context: Context? = null
    var mBitmap: Bitmap? = null

    private lateinit var mWallManager: WallpaperManager

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attachView(view: WallpaperDisplayContract.View) {
        mView = view
        context = view as Context
        mWallManager = WallpaperManager.getInstance(context)

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
        setDesktopWallpaper()
    }

    //设置桌面壁纸
    private fun setDesktopWallpaper() {
        try {
            mWallManager.setBitmap(mBitmap)
            Toast.makeText(context, "桌面壁纸设置成功", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(context, "桌面壁纸设置失败", Toast.LENGTH_SHORT).show()
        }

    }

    //设置锁屏壁纸
    private fun setLockScreenWallpaper() {
        try {
            //获取类名
            val class1 = mWallManager.javaClass
            //获取设置锁屏壁纸的函数
            val setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper", Bitmap::class.java)
            //调用锁屏壁纸的函数，并指定壁纸的路径imageFilesPath
            setWallPaperMethod.invoke(mWallManager, mBitmap)
            Toast.makeText(context, "锁屏壁纸设置成功", Toast.LENGTH_SHORT).show()
        } catch (e: Throwable) {
            Toast.makeText(context, "锁屏壁纸设置失败", Toast.LENGTH_SHORT).show()
        }

    }

    //设置所有壁纸
    private fun setAllWallpaper() {
        try {
            //获取类名
            val class1 = mWallManager.javaClass
            //获取设置锁屏壁纸的函数
            val setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper", Bitmap::class.java)
            //调用锁屏壁纸的函数，并指定壁纸的路径imageFilesPath
            setWallPaperMethod.invoke(mWallManager, mBitmap)
            Toast.makeText(context, "锁屏壁纸设置成功", Toast.LENGTH_SHORT).show()
        } catch (e: Throwable) {
            Toast.makeText(context, "锁屏壁纸设置失败", Toast.LENGTH_SHORT).show()
        }

        try {
            mWallManager.setBitmap(mBitmap)
            Toast.makeText(context, "桌面壁纸设置成功", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(context, "桌面壁纸设置失败", Toast.LENGTH_SHORT).show()
        }

    }

}