package com.dragon.wallpaperapp.mvp.presenter

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Message
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.contract.WallpaperDisplayContract
import com.dragon.wallpaperapp.mvp.model.bean.Wallpaper
import com.orhanobut.logger.Logger
import java.io.IOException

/**
 * Created by D22434 on 2017/12/7.
 */
class WallpaperDisplayPresenter : WallpaperDisplayContract.Presenter {


    lateinit var mView: WallpaperDisplayContract.View
    lateinit var wallpapers: List<Wallpaper>

    lateinit var context: Context
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

    /**
     * 加载数据
     * @param wallpapers 所有壁纸
     * @param position 当前壁纸ID
     */
    override fun loadData(wallpapers: List<Wallpaper>, position: Int) {
        this.wallpapers = wallpapers
    }

    /**
     * 显示预览
     */
    private fun showWallpaper(url: String?) {
//        GlideApp.with(context)
//                .asBitmap()
//                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(object : SimpleTarget<Bitmap>() {
//                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>) {
//                        mView.preViewWallpaper(resource)
//                        mView.hideLoading()
//                    }
//                })
    }

    override fun showPreview(currentImg: String) {
        showWallpaper(currentImg)
    }


    /**
     * 设置壁纸 currentItem当前壁纸
     * which 设置模式
     */
    override fun saveWallpaper(currentImg: String, which: Int) {
        GlideApp.with(context)
                .asBitmap()
                .load(currentImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>) {
                        mBitmap = resource
                        TaskThread(which).start()
                    }
                })
    }

    /**
     * 设置全屏壁纸
     */
    fun setFullScreenWallpaper() {
        val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val desiredMinimumWidth = dm.widthPixels
        val desiredMinimumHeight = dm.heightPixels
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        mWallManager.suggestDesiredDimensions(desiredMinimumWidth, desiredMinimumHeight)
        mWallManager.setBitmap(mBitmap)
    }

    //设置桌面壁纸
    private fun setDesktopWallpaper() {
        try {
            Logger.e("wallpaper", "width=${mBitmap!!.width} ==height=${mBitmap!!.height}")
            setFullScreenWallpaper()
            handler.sendEmptyMessage(0)
        } catch (e: IOException) {
            handler.sendEmptyMessage(1)
        }

    }

    /**
     *  设置锁屏壁纸
     */
    private fun setLockScreenWallpaper() {
        try {
            //获取类名
            val class1 = mWallManager.javaClass
            //获取设置锁屏壁纸的函数
            val setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper", Bitmap::class.java)
            //调用锁屏壁纸的函数，并指定壁纸的路径imageFilesPath
            setWallPaperMethod.invoke(mWallManager, mBitmap)
            handler.sendEmptyMessage(2)
        } catch (e: Throwable) {
            handler.sendEmptyMessage(3)
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
            handler.sendEmptyMessage(2)
        } catch (e: Throwable) {
            handler.sendEmptyMessage(3)

        }

        try {
            setFullScreenWallpaper()
            handler.sendEmptyMessage(0)
        } catch (e: IOException) {
            handler.sendEmptyMessage(1)
        }
    }

    var handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 ->
                    Toast.makeText(context, "桌面壁纸设置成功", Toast.LENGTH_SHORT).show()
                1 ->
                    Toast.makeText(context, "桌面壁纸设置失败", Toast.LENGTH_SHORT).show()
                2 ->
                    Toast.makeText(context, "锁屏壁纸设置成功", Toast.LENGTH_SHORT).show()
                3 ->
                    Toast.makeText(context, "锁屏壁纸设置失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    internal inner class TaskThread(var which: Int) : Thread() {
        override fun run() {
            synchronized(which) {
                when (which) {
                    0 -> setDesktopWallpaper()
                    1 -> setLockScreenWallpaper()
                    2 -> setAllWallpaper()
                }
            }
        }
    }
}