package com.dragon.wallpaperapp.api


import com.dragon.wallpaperapp.mvp.model.CategoryApiModel
import com.dragon.wallpaperapp.mvp.model.RankingApiModel
import com.dragon.wallpaperapp.mvp.model.RecommendApiModel
import com.dragon.wallpaperapp.mvp.model.WallpaperApiModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by D22434 on 2017/11/28.
 */

interface ApiManagerService {

    //获取爱壁纸接口
    @GET("baidu_rom.php")
    fun wallpaperApi(): Observable<WallpaperApiModel>

    //获取爱壁纸接口
    @GET
    fun getRanking(@Url url: String): Observable<RankingApiModel>

    //获取推荐壁纸
    @GET
    fun getRecommend(@Url url: String): Observable<RecommendApiModel>

    //获取分类壁纸
    @GET
    fun getCategory(@Url url: String): Observable<CategoryApiModel>
}
