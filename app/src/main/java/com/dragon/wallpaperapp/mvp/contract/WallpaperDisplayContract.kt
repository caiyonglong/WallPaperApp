package com.dragon.wallpaperapp.mvp.contract

import android.graphics.Bitmap
import com.dragon.wallpaperapp.mvp.base.IBasePresenter
import com.dragon.wallpaperapp.mvp.base.IBaseView
import com.dragon.wallpaperapp.mvp.model.Wallpaper

/**
 * Created by D22434 on 2017/11/28.
 */
object WallpaperDisplayContract {

    interface View : IBaseView {
        fun showLoading()
        fun hideLoading()
        fun preViewWallpaper(mBitmap: Bitmap)
    }

    interface Presenter : IBasePresenter<View> {
        fun loadData(wallpapers: List<Wallpaper>, position: Int)
        fun showPreview(currentImg: String)
        fun saveWallpaper(currentImg: String, which: Int)
    }
}