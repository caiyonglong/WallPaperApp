package com.dragon.wallpaperapp.mvp.model

/**
 * Created by D22434 on 2017/11/28.
 * 点击分类获取的json数据类型
 */

class CategoryApiModel() {

    var msg: String? = null
    var res: ResBean? = null

    class ResBean {
        var category: List<CategoryBean>? = null

        class CategoryBean {
            
            var count: Int = 0
            var cover_temp: String? = null
            var name: String? = null
            var cover: String? = null
            var icover: String? = null
            var id: String? = null
            var picasso_cover: String? = null
        }
    }
}
