package com.dragon.wallpaperapp.mvp.contract

import com.dragon.wallpaperapp.mvp.base.IBasePresenter
import com.dragon.wallpaperapp.mvp.base.IBaseView
import com.dragon.wallpaperapp.mvp.model.Banner
import com.dragon.wallpaperapp.mvp.model.bean.Wallpaper

/**
 * Created by D22434 on 2017/11/28.
 */

interface HomePageContract {

    interface View : IBaseView {
        fun showWallpaper(wallpapers: List<Wallpaper>?, page: Int)
        fun showBanners(banners: List<Banner>)
        fun showError(error: String)
    }

    interface Presenter : IBasePresenter<View> {
        fun getWallpaper(limit: Int, skip: Int, order: String)
    }
}
