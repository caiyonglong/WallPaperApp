package com.dragon.wallpaperapp.mvp.model.bean

/**
 * Created by yonglong on 2017/12/24.
 */
class Movie(var title: String, var date: String, var url: String) {

    var detail: List<TypeInfo>? = null
    var img: String? = null
    override fun toString(): String {
        return "Movie(title='$title', date='$date', url='$url', detail=$detail, img=$img)"
    }

    class TypeInfo(var value: String? = null, var type: String? = null) {
        override fun toString(): String {
            return "TypeInfo(value=$value, type=$type)"
        }
    }
}