package com.dragon.wallpaperapp.mvp.presenter

import android.util.Log
import com.dragon.wallpaperapp.api.ApiManager
import com.dragon.wallpaperapp.mvp.contract.RankingContract
import com.dragon.wallpaperapp.mvp.model.RankingApiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by D22434 on 2017/11/28.
 */

class RankingPresenter : RankingContract.Presenter {


    var mView: RankingContract.View? = null

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attachView(view: RankingContract.View) {
        mView = view
    }

    override fun detachView() {
        if (mView != null)
            mView = null
    }

    override fun getRanking(url: String) {
        ApiManager.instance
                .apiService
                .getRanking(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: RankingApiModel ->
                    Log.e("TAG", t.toString())
                    mView!!.updateView(t.data)
                }, { e: Throwable ->
                    Log.e("TAG", e.message)
                    mView!!.setEmpty()
                })
    }

}



