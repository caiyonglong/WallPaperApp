package com.dragon.wallpaperapp.mvp.model

/**
 * Created by D22434 on 2017/11/28.
 * 点击分类获取的json数据类型
 */

class CategoryApiModel(var msg: String? = null, var res: ResBean? = null) {

    class ResBean {
        var category: List<Category>? = null
    }
}
