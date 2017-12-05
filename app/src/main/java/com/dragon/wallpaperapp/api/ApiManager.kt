package com.dragon.wallpaperapp.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by yonglong on 2017/9/11.
 */

class ApiManager private constructor() {
    var apiService: ApiManagerService
    var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 使用RxJava作为回调适配器
                .addConverterFactory(GsonConverterFactory.create()) // 使用Gson作为数据转换器
                .build()
        apiService = retrofit.create(ApiManagerService::class.java)
    }

    companion object {

        private val BASE_URL = "http://service.aibizhi.adesk.com/"


        private var sApiManager: ApiManager? = null

        //获取ApiManager的单例
        val instance: ApiManager
            get() {
                if (sApiManager == null) {
                    synchronized(ApiManager::class.java) {
                        if (sApiManager == null) {
                            sApiManager = ApiManager()
                        }
                    }
                }
                return this!!.sApiManager!!
            }
    }

}
