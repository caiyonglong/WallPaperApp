package com.dragon.wallpaperapp.mvp.model

/**
 * Created by D22434 on 2017/11/28.
 * 推荐获取的json数据类型
 */

class RecommendApiModel(var data: List<WallpaperDetailModel>? = null, var link: LinkBean? = null) {
    /**
     * link : {"prev":"http://open.lovebizhi.com/bdrom/recommend?code=K2Pomm2s1bkhGZyQv1IZaerYFevESJfU2rfom%29IlZQ5ZeCvl5lHItNo3ED2QQPecy6iQ5SFwlaA","next":"http://open.lovebizhi.com/bdrom/recommend?code=zGeSQig5k7rHeWS4%7C74uNAyuP5ALgZbQlbSmsFhxVvOZlUf2qABG9LEQE7OSWtoJcEDFmFQrFvk"}
     * data : [{"key":"297820-103","small":"http://s.qdcdn.com/cl/13627676,240,192.jpg","big":"http://s.qdcdn.com/cl/13627676,1280,1024.jpg","down":303306,"down_stat":"http://open.lovebizhi.com/bdrom/stat?code=zGswhiwq5kuNga6e1ckrSLwwqfpIzNRWbfb12QQWVruy1bGY%29uhVzvD3vCpbjArYeLx2P%29yZ1gkxP62UmPvZniIngK8"}]
     */

    class LinkBean(var next: String? = null,
                   /**
                    * prev : http://open.lovebizhi.com/bdrom/recommend?code=K2Pomm2s1bkhGZyQv1IZaerYFevESJfU2rfom%29IlZQ5ZeCvl5lHItNo3ED2QQPecy6iQ5SFwlaA
                    * next : http://open.lovebizhi.com/bdrom/recommend?code=zGeSQig5k7rHeWS4%7C74uNAyuP5ALgZbQlbSmsFhxVvOZlUf2qABG9LEQE7OSWtoJcEDFmFQrFvk
                    */
                   var prev: String? = null) {
        override fun toString(): String {
            return "LinkBean(next=$next, prev=$prev)"
        }
    }

    override fun toString(): String {
        return "RecommendApiModel(data=$data, link=$link)"
    }

}
