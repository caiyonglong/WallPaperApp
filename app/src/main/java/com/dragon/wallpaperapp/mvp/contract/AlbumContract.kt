package com.dragon.wallpaperapp.mvp.contract

import com.dragon.wallpaperapp.mvp.base.IBasePresenter
import com.dragon.wallpaperapp.mvp.base.IBaseView
import com.dragon.wallpaperapp.mvp.model.Banner
import com.dragon.wallpaperapp.mvp.model.bean.Album

/**
 * Created by D22434 on 2017/11/28.
 */

interface AlbumContract {

    interface View : IBaseView {
        fun showAlbum(albums: List<Album>?)
        fun showBanners(banners: List<Banner>)
        fun showError(error: String)
    }

    interface Presenter : IBasePresenter<View> {
        fun getAlbums(limit: Int, skip: Int, order: String)
    }
}
