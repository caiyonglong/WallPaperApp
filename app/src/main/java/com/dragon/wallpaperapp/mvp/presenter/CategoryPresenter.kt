package com.dragon.wallpaperapp.mvp.presenter

import com.dragon.wallpaperapp.api.ApiManager
import com.dragon.wallpaperapp.mvp.contract.CategoryContract
import com.dragon.wallpaperapp.mvp.model.CategoryApiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by D22434 on 2017/11/28.
 */

class CategoryPresenter : CategoryContract.Presenter {


    lateinit var mView: CategoryContract.View

    override fun unsubscribe() {

    }

    override fun attachView(view: CategoryContract.View) {
        mView = view
    }

    override fun detachView() {
    }

    override fun subscribe() {
    }

    override fun getCategory() {
        ApiManager.instance
                .apiService
                .getCategoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: CategoryApiModel ->
                    mView.showCategory(t.res?.category)
                }, { e: Throwable ->
                    mView.showError(e.message!!)
                })
    }

}
