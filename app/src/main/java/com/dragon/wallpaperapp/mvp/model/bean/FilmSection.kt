package com.dragon.wallpaperapp.mvp.model.bean

import com.chad.library.adapter.base.entity.SectionEntity


/**
 * Created by D22434 on 2017/12/25.
 */
class FilmSection(var flimData: Movie?) : SectionEntity<Movie>(flimData) {

    var url: String = ""

    constructor(isHeader: Boolean, header: String, url: String) : this(null) {
        this.isHeader = isHeader
        this.header = header
        this.t = null
        this.url = url
    }

}