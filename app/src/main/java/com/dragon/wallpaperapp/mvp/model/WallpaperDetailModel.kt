package com.dragon.wallpaperapp.mvp.model

/**
 * Created by D22434 on 2017/11/28.
 */

data class WallpaperDetailModel(
        /**
         * key : 298125-103
         * small : http://s.qdcdn.com/cl/13630963,240,192.jpg
         * big : http://s.qdcdn.com/cl/13630963,1280,1024.jpg
         * down : 600366
         * down_stat : http://open.lovebizhi.com/bdrom/stat?code=mVUK2VzDgYstQjTMPXny7F0Lt2X9rTLkIagMGwoo2pLQTuwv%7CZK7Co6u%29GEDaKvBewxP8Vpe%29oq2MvcUOSlrPZfQ8vI
         */
        var key: String? = null, var small: String? = null, var big: String? = null, var down: Int = 0, var down_stat: String? = null) {
    override fun toString(): String {
        return "WallpaperDetailModel(key=$key, small=$small, big=$big, down=$down, down_stat=$down_stat)"
    }
}
