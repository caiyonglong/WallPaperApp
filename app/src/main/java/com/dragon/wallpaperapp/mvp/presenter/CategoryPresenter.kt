package com.dragon.wallpaperapp.mvp.presenter

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.dragon.wallpaperapp.mvp.contract.CategoryContract


/**
 * Created by D22434 on 2017/11/28.
 */

class CategoryPresenter : CategoryContract.Presenter {

    var mView: CategoryContract.View? = null
    var mHandler: Handler? = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            msg?.data?.get("Category")
        }
    }

    override fun unsubscribe() {

    }

    override fun attachView(view: CategoryContract.View) {
        mView = view
    }

    override fun detachView() {
    }

    override fun subscribe() {
    }
}
