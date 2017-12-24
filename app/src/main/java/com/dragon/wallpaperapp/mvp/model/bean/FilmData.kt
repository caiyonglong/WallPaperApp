package com.dragon.wallpaperapp.mvp.model.bean

/**
 * Created by yonglong on 2017/12/24.
 */
class FilmData(var title: String, var date: String, var url: String) {
    override fun toString(): String {
        return "FilmData(title='$title', date='$date', url='$url')"
    }
}