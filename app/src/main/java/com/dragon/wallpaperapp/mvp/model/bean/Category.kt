package com.dragon.wallpaperapp.mvp.model.bean

/**
 * Created by yonglong on 2017/12/5.
 */
class Category {
    var count: Int = 0
    var cover_temp: String? = null
    var name: String? = null
    var cover: String? = null
    var icover: String? = null
    var id: String? = null
    var picasso_cover: String? = null
    override fun toString(): String {
        return "Album(count=$count, cover_temp=$cover_temp, name=$name, cover=$cover, icover=$icover, id=$id, picasso_cover=$picasso_cover)"
    }


}