package com.dragon.wallpaperapp.mvp.contract

import com.dragon.wallpaperapp.mvp.base.IBasePresenter
import com.dragon.wallpaperapp.mvp.base.IBaseView
import com.dragon.wallpaperapp.mvp.model.WallpaperApiModel

/**
 * Created by D22434 on 2017/11/28.
 */

interface HomePageContract {

    interface View : IBaseView {
        fun showSpecial(bander: List<WallpaperApiModel.SpecialBean>)

        fun showCategory(categoryList: List<WallpaperApiModel.CategoryBean>)

        fun showEveryDay(categoryList: List<WallpaperApiModel.EverydayBean>)

        fun showError(error: String)

    }

    interface Presenter : IBasePresenter<View> {
        fun getWallpaper();
    }
}
