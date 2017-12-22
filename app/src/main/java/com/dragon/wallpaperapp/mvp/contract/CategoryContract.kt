package com.dragon.wallpaperapp.mvp.contract

import com.dragon.wallpaperapp.mvp.base.IBasePresenter
import com.dragon.wallpaperapp.mvp.base.IBaseView
import com.dragon.wallpaperapp.mvp.model.bean.Category

/**
 * Created by D22434 on 2017/11/28.
 */
object CategoryContract {

    interface View : IBaseView {
        fun showCategory(categories: List<Category>?)

        fun showError(error: String)

    }

    interface Presenter : IBasePresenter<View> {
        fun getCategory()
    }
}