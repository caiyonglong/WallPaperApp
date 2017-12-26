package com.dragon.wallpaperapp.mvp.contract

import com.dragon.wallpaperapp.mvp.base.IBasePresenter
import com.dragon.wallpaperapp.mvp.base.IBaseView
import com.dragon.wallpaperapp.mvp.model.bean.Wallpaper

/**
 * Created by D22434 on 2017/11/28.
 */

interface WallpaperContract {

    interface View : IBaseView {
        fun showWallpaper(wallpapers: List<Wallpaper>?)

        fun showError(error: String)

    }

    interface Presenter : IBasePresenter<View> {
        fun getWallpaperForCate(cate_id: String, limit: Int, skip: Int, order: String)
        fun getWallpaper(limit: Int, skip: Int, order: String)
        fun getWallpaperForAlbum(album_id: String, limit: Int, skip: Int, order: String)
    }
}
