package com.dragon.wallpaperapp.mvp.presenter

import android.util.Log
import com.dragon.wallpaperapp.api.ApiManager
import com.dragon.wallpaperapp.mvp.contract.RecommendContract
import com.dragon.wallpaperapp.mvp.model.RecommendApiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by D22434 on 2017/11/28.
 */

class RecommendPresenter : RecommendContract.Presenter {


    lateinit var mView: RecommendContract.View
    var nextUrl: String? = null

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attachView(view: RecommendContract.View) {
        mView = view
    }

    override fun detachView() {

    }

    override fun getNext() {
        if (nextUrl != null) {
            ApiManager.instance
                    .apiService
                    .getRecommend(nextUrl!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ t: RecommendApiModel ->
                        Log.e("TAG", t.toString())
                        mView!!.updateView(t.data)
                    }, { e: Throwable ->
                        Log.e("TAG", e.message)
                        mView!!.setEmpty()
                    })
        } else {
            mView.setEmpty()
        }
    }

    override fun getRecommend(url: String) {
        ApiManager.instance
                .apiService
                .getRecommend(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: RecommendApiModel ->
                    Log.e("TAG", t.toString())
                    mView!!.updateView(t.data)
                }, { e: Throwable ->
                    Log.e("TAG", e.message)
                    mView!!.setEmpty()
                })
    }

}



