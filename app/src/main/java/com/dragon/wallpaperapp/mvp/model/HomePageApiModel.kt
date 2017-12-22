package com.dragon.wallpaperapp.mvp.model

/**
 * Created by D22434 on 2017/12/5.
 */

class HomePageApiModel(var msg: String? = null, var res: ResBean? = null) {

    class ResBean {
        var homepage: List<HomepageBean>? = null
        var vertical: List<Wallpaper>? = null

        class HomepageBean {
            var type: Int = 0
            var title: String? = null
            var items: List<ItemsBean>? = null

            class ItemsBean {
                var value: ValueBean? = null
                var target: String? = null
                var thumb: String? = null
                var type: String? = null
                var id: String? = null

                class ValueBean {

                    var name: String? = null
                    var cover: String? = null
                    var id: String? = null
                    var lcover: String? = null
                    var desc: String? = null
                    override fun toString(): String {
                        return "ValueBean(name=$name, cover=$cover, id=$id, lcover=$lcover, desc=$desc)"
                    }

                }

                override fun toString(): String {
                    return "ItemsBean(value=$value, target=$target, thumb=$thumb)"
                }

            }

            override fun toString(): String {
                return "HomepageBean(type=$type, title=$title, items=$items)"
            }

        }

        override fun toString(): String {
            return "ResBean(homepage=$homepage, vertical=$vertical)"
        }

    }

    override fun toString(): String {
        return "HomePageApiModel(msg=$msg, res=$res)"
    }

}
