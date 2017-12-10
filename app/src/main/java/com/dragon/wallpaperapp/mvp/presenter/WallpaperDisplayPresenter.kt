package com.dragon.wallpaperapp.mvp.presenter

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.contract.WallpaperDisplayContract
import com.dragon.wallpaperapp.mvp.model.Wallpaper
import java.io.IOException

/**
 * Created by D22434 on 2017/12/7.
 */
class WallpaperDisplayPresenter : WallpaperDisplayContract.Presenter {

    lateinit var mView: WallpaperDisplayContract.View
    lateinit var wallpapers: List<Wallpaper>

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

    /**
     * 加载数据
     * @param wallpapers 所有壁纸
     * @param position 当前壁纸ID
     */
    override fun loadData(wallpapers: List<Wallpaper>, position: Int) {
        mView.showLoading()

        this.wallpapers = wallpapers
        val len = wallpapers.size
        val lists = ArrayList<ImageView>()
        (0 until len).mapTo(lists) { getImageView(wallpapers[it].img) }
        mView.setImageList(lists, position)
        mView.hideLoading()
    }

    /**
     * TODO 获取ImageView控件
     * @param imgUrl 壁纸地址
     *
     */
    fun getImageView(imgUrl: String?): ImageView {
        val imageView = LayoutInflater.from(context).inflate(
                R.layout.wp_image, null) as ImageView
        getImageRequest(imageView, imgUrl)
        return imageView
    }

    /**
     * TODO 将壁纸显示在 ImageView上
     * @param imgUrl 壁纸地址
     *
     */
    private fun getImageRequest(imageView: ImageView, imgUrl: String?) {
        GlideApp.with(context)
                .asBitmap()
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>) {
                        imageView.setImageBitmap(resource)
                    }
                })
    }

    /**
     * 设置壁纸 currentItem当前壁纸
     * which 设置模式
     */
    override fun setWallpaper(currentItem: Int, which: Int) {
        GlideApp.with(context)
                .asBitmap()
                .load(wallpapers[currentItem].img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>) {
                        mBitmap = resource
                        when (which) {
                            0 -> setDesktopWallpaper()
                            1 -> setLockScreenWallpaper()
                            2 -> setAllWallpaper()
                        }
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
            Log.e("wallpaper", "width=${mBitmap!!.width} ==height=${mBitmap!!.height}")
            setFullScreenWallpaper()
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
            setFullScreenWallpaper()
            Toast.makeText(context, "桌面壁纸设置成功", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(context, "桌面壁纸设置失败", Toast.LENGTH_SHORT).show()
        }

    }

}