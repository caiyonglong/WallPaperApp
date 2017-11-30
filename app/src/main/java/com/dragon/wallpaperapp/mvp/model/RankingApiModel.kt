package com.dragon.wallpaperapp.mvp.model

/**
 *  排行榜获取的数据类型
 */

class RankingApiModel(var data: List<WallpaperDetailModel>? = null) {

    override fun toString(): String {
        return "RankingApiModel(data=$data)"
    }
}
