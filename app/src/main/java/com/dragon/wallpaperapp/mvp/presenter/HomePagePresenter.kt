package com.dragon.wallpaperapp.mvp.presenter

import com.dragon.wallpaperapp.api.ApiManager
import com.dragon.wallpaperapp.mvp.contract.HomePageContract
import com.dragon.wallpaperapp.mvp.model.HomePageApiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by D22434 on 2017/11/28.
 */

class HomePagePresenter : HomePageContract.Presenter {

    lateinit var mView: HomePageContract.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attachView(view: HomePageContract.View) {
        mView = view
    }

    override fun detachView() {
    }

    override fun getWallpaper(limit: Int, skip: Int, order: String) {
        var map: Map<String, Any> = mapOf("limit" to limit,
                "skip" to skip,
                "order" to order,
                "adult" to "false",
                "first" to "0")
//        Log.e("TAG", map.toString() + "_-")
        ApiManager.instance
                .apiService
                .getHomePageInfo(map as Map<String, String>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: HomePageApiModel ->
                    mView.showWallpaper(t.res?.vertical)
                }, { e: Throwable ->
                    mView.showError(e.message!!)
                })
    }

}



