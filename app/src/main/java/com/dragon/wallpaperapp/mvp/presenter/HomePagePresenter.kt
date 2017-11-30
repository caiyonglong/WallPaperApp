package com.dragon.wallpaperapp.mvp.presenter

import android.util.Log
import com.dragon.wallpaperapp.api.ApiManager
import com.dragon.wallpaperapp.mvp.contract.HomePageContract
import com.dragon.wallpaperapp.mvp.model.ApiModel
import com.dragon.wallpaperapp.mvp.model.WallpaperApiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by D22434 on 2017/11/28.
 */

class HomePagePresenter : HomePageContract.Presenter {

    var mView: HomePageContract.View? = null

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attachView(view: HomePageContract.View) {
        mView = view
    }

    override fun detachView() {
        if (mView != null)
            mView = null
    }

    override fun getWallpaper() {
        ApiManager.instance
                .apiService
                .wallpaperApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: WallpaperApiModel ->
                    Log.e("TAG", t.toString())
                    ApiModel.Bander = t.banner
                    ApiModel.Ranking = t.ranking
                    ApiModel.Wallpaper = t.wallpaper
                    mView?.showSpecial(t.special!!)
                    mView?.showCategory(t.category!!)
                    mView?.showEveryDay(t.everyday!!)
                }, { e: Throwable ->
                    mView?.showError(e.message!!)
                })
    }

}



