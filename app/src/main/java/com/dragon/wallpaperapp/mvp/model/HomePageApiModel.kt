package com.dragon.wallpaperapp.mvp.model

/**
 * Created by D22434 on 2017/12/5.
 */

class HomePageApiModel(var msg: String? = null, var res: ResBean? = null) {

    class ResBean {
        var homepage: List<HomepageBean>? = null
        var vertical: List<VerticalBean>? = null

        class HomepageBean {
            var type: Int = 0
            var title: String? = null
            var items: List<ItemsBean>? = null

            class ItemsBean {
                var value: ValueBean? = null
                var target: String? = null
                var thumb: String? = null

                class ValueBean {

                    var name: String? = null
                    var cover: String? = null
                    var id: String? = null
                    var lcover: String? = null
                    var desc: String? = null
                }
            }
        }

        class VerticalBean {
            var preview: String? = null
            var thumb: String? = null
            var img: String? = null
            var wp: String? = null
        }
    }
}
