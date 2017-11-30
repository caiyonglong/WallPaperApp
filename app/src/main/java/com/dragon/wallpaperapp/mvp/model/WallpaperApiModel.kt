package com.dragon.wallpaperapp.mvp.model

/**
 * Created by D22434 on 2017/11/28.
 */

data class WallpaperApiModel(
        /**
         * ranking : http://open.lovebizhi.com/bdrom/ranking?code=ucVXPVt16dyxjQmPHiADoXyNEg3D4NoWNPmV%291ifpny447eongHf3OOsW2Y
         * banner : http://open.lovebizhi.com/bdrom/banner?code=ucVXPVt16dyxjQmPHiADoXyNEg3D4NoWNPmV%291ifpny447eongHf3OOsW2Y
         * wallpaper : http://open.lovebizhi.com/bdrom/wallpaper?code=ucVXPVt16dyxjQmPHiADoXyNEg3D4NoWNPmV%291ifpny447eongHf3OOsW2Y
         * recommend : http://open.lovebizhi.com/bdrom/recommend?code=ucVXPVt16dyxjQmPHiADoXyNEg3D4NoWNPmV%291ifpny447eongHf3OOsW2Y
         * category : [{"name":"影视明星","cover":"http://s.qdcdn.com/cl/13608680,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=HKGqQlC0GaR7Xfc4WmsuqZYR5eye7lfgwDyMd7mQtLDWH2GLDa8OBr%7CVVKG2Jcdp"},{"name":"风光风景","cover":"http://s.qdcdn.com/cl/13630962,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=1gPSju1MgFDcJSuK2yZhSNEgMVwBL1nwGHspiT8KFDbz%296wgN836S9NmX0N%7CA0ra"},{"name":"花卉植物","cover":"http://s.qdcdn.com/cl/11670817,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=AvTYRXKo9Plvap0nz7LByESE%29qzCJ1t65HMg9P6XWjpGvQBGD76WAV%29UbM6xmCVP"},{"name":"魅力女性","cover":"http://s.qdcdn.com/cl/13430765,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=kBszLD%7Clj50snMR3E9%7C2AvSBSL6TBA6Zctk6eIpUnmS99wZDcV7uqD2%7CQFeg8Jw1"},{"name":"游戏CG","cover":"http://s.qdcdn.com/cl/10836272,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=o6reJ1lY3aJuIKnPcmocvulLRsgpht3P99oBsKrckKSWUx0NUA0ozuSSqKWjxh1A"},{"name":"动物宠物","cover":"http://s.qdcdn.com/cl/13251341,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=ExfISoIKfYNO3j5cNM0V34mZeJ7udDxuPU5V7zmPQdfEBE%7CJsHXhfWhy2P9ZLLfD"},{"name":"卡通动漫","cover":"http://s.qdcdn.com/cl/13044469,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=nQhTQeEtFIm6%29sWNNAbKKiQaCVMcZK13WLRyr9aF8%29ADOm5G0%29LTVDlpU3b5hDeV"},{"name":"机车世界","cover":"http://s.qdcdn.com/cl/11727273,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=mq%7CwfQxyq%29E4e7tU6AaDBB9ih94AsrEKyy9qL5Xyr1x%7Cth1lXivha2fhq7jPwbyhfVtTcA"},{"name":"炫彩美图","cover":"http://s.qdcdn.com/cl/13044390,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=kLPoGaLbZiP7oVlHwpLh19Tp7d1OBEjQwD4k1FPRyaJ2lW4ZU8dbct2bwzlou3t%7C9ucfAg"},{"name":"品牌欣赏","cover":"http://s.qdcdn.com/cl/13275504,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=FTYCHvket2YLZ%7CoAx3sXFUFtSzlLOrh2J3GNSDVxEykwCl%7Cggq17HBEQ2X5K%7Cv9F3jEtuA"},{"name":"美食天下","cover":"http://s.qdcdn.com/cl/12662441,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=%29msQfsbdRO1ir3bsv1YKBZqdwAnGgMEUf5Ysr2Y%29UTGDOtKA62kUJzvVitYDdXvIUyDByQ"},{"name":"影视剧集","cover":"http://s.qdcdn.com/cl/14099880,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=6gSGm5bpqyN5tWA%29TKCFaq9hvFEJOI2SHXOOO0ByGBEw%7CGroOg3ANgSxRTBl46aTe00RYw"},{"name":"美丽文字","cover":"http://s.qdcdn.com/cl/12463405,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=sQ1LGl7vXkpbOFK1MhAOA%7CQMEHkNb8cfJGuOyIb92iIGgnXPRKE3Gno3SRhAWzAf73Ze1A"},{"name":"体育运动","cover":"http://s.qdcdn.com/cl/11823129,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=8RJEL%29ACtZ%29Uzhes5020GrWrOF3VFnQxPpQUPGMRyVAnu5lK5gXSDI06Zmw2ss1pMhVztA"},{"name":"艺术设计","cover":"http://s.qdcdn.com/cl/13015600,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=Rz5jZ5i4WITybz4eLr3J%7CzPpTroLufk7yrIeGhohJLjwRiKKUGPE4mu1IAE6aHI438R%29Ng"},{"name":"节庆假日","cover":"http://s.qdcdn.com/cl/13414639,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=ttXL8uXAznej7zj2zYHRS5MHaRlxe3BY%7CXsY1RrdtAwwaPVDmM5c9KLkxZ56psyx"},{"name":"军事战争","cover":"http://s.qdcdn.com/cl/11494498,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=MMfaWc3iTSLA2mYQiUGkzDXd8G%29ADvgy51NMvp%7C4%292DbNL44aI8rlluAmEK2OpTvTwQ2fA"},{"name":"美图杂烩","cover":"http://s.qdcdn.com/cl/13614861,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/category?code=Sdz4%7CF52ldvrNquq5IBvHcHCaAheMJqRz6tLYVbsSBEDYTjd23GxctqEWoThbzyhHU6yaQ"}]
         * everyday : [{"date":"2017-08-31","image":"http://s.qdcdn.com/cl/14543477,256,256.jpg","name":"08-31","url":"http://open.lovebizhi.com/bdrom/everyday?code=Y2WW%7Cm2MEIwJuz6Gq7DpVkSSLsVwCq7esMJLnLlitDj1dWsTGHvlby%294%7Cz1z5DKE3qLHJHU0srFUSugl"},{"date":"2017-08-30","image":"http://s.qdcdn.com/cl/14552723,256,256.jpg","name":"08-30","url":"http://open.lovebizhi.com/bdrom/everyday?code=pI62fNTMBQ3ZHCIUl%29l6N5VfhgsUcc1OB8E9N401RgGoleX8Am4zYW7SKZ9G320YpdRdxM6vWLVrnGuu"},{"date":"2017-08-29","image":"http://s.qdcdn.com/cl/14548170,256,256.jpg","name":"08-29","url":"http://open.lovebizhi.com/bdrom/everyday?code=UFqBcv23hEqQ2fBxgorlbAYliiC3E8GI190CKy8encdG5qmZaahXdpMu7rCLiU8MXO9yuNLj%29N2Bl3ZA"},{"date":"2017-08-28","image":"http://s.qdcdn.com/cl/14556134,256,256.jpg","name":"08-28","url":"http://open.lovebizhi.com/bdrom/everyday?code=N7HP%29yGf1xqubX3agVBazOQsqW32%7C3F%7CvXNo2JBTA%7Cpa6HOYOSWbRDwlFPsXfFwQS7H5MDz0QYoWRqcS"},{"date":"2017-08-27","image":"http://s.qdcdn.com/cl/14551769,256,256.jpg","name":"08-27","url":"http://open.lovebizhi.com/bdrom/everyday?code=tZo5opg0ltmQVFwDnxHOzehGC8eiPO1VUQXYLfyiG5UxDffi9mN%7CQvubPN9hNbik3dYWhkwMlvp2ojBa"},{"date":"2017-08-26","image":"http://s.qdcdn.com/cl/14536420,256,256.jpg","name":"08-26","url":"http://open.lovebizhi.com/bdrom/everyday?code=vGaqzONn6OCpG9OYCX4oUHBcuD1Tf3lICDMJ7Sc0bf5pr76PBWPXH8AsbLwWD%29bMcumHV9rJj4HftUv%29"},{"date":"2017-08-25","image":"http://s.qdcdn.com/cl/14557063,256,256.jpg","name":"08-25","url":"http://open.lovebizhi.com/bdrom/everyday?code=ZGMVYj9dw20kJAy0lE%29iiREqLP1rZ4QXLhcksJ5RjsyL44ztIOE6lk4ETyv8EoGStkVAYHfLpvvXpU4z"},{"date":"2017-08-24","image":"http://s.qdcdn.com/cl/14548163,256,256.jpg","name":"08-24","url":"http://open.lovebizhi.com/bdrom/everyday?code=Eu3P1mgeTbOi0nfOhuR3dVFs4QfjxBALz9HBmEUZw5CpPSmiN55SiXmiMC9NkpLXO5A3pi7wKGQaejET"},{"date":"2017-08-23","image":"http://s.qdcdn.com/cl/14543434,256,256.jpg","name":"08-23","url":"http://open.lovebizhi.com/bdrom/everyday?code=RtXNa6QmCusZRQXe6s95xNLR8gIF%7CAShd%29xidLfVmsZeZRDULix4laLiNSetSf%29n%29A33ig2Pk7PmkQ1q"},{"date":"2017-08-22","image":"http://s.qdcdn.com/cl/14544544,256,256.jpg","name":"08-22","url":"http://open.lovebizhi.com/bdrom/everyday?code=dmxAzyE22u%29upEkz8z%29guwNIAo%7CUWT523%29nb6XQxrKqIls4B%29%7C6b9PNuOKatAWKfvigp0zQT4eX8Wndk"},{"date":"2017-08-21","image":"http://s.qdcdn.com/cl/14541708,256,256.jpg","name":"08-21","url":"http://open.lovebizhi.com/bdrom/everyday?code=rGkGYIRppjUliAckM8CzMCxAS2zaurk8c9g9L3xpgcY8E1hlFUiXDiM6xeGgSf7gzdpSe8o6AGXoSA1u"},{"date":"2017-08-20","image":"http://s.qdcdn.com/cl/14554350,256,256.jpg","name":"08-20","url":"http://open.lovebizhi.com/bdrom/everyday?code=ZQErs5e3c%7CnDwQza%7C%7CweOxtUWRPx5jEXZWH9WkzNGMJiXlfJgGj7vuv0XMYHW9dIqKBm3XqlIpt5Ufhi"},{"date":"2017-08-19","image":"http://s.qdcdn.com/cl/14551994,256,256.jpg","name":"08-19","url":"http://open.lovebizhi.com/bdrom/everyday?code=p9ps3U028136qUSTc3pIY4sY9HruShN8aTNIN0vvH0GrhwA4XxW3DALRe2OZkvU2DgjvauuWowGgLN5S"},{"date":"2017-08-18","image":"http://s.qdcdn.com/cl/14556225,256,256.jpg","name":"08-18","url":"http://open.lovebizhi.com/bdrom/everyday?code=gdwt6S5rBjf7QRQ9yDBfBYo%7Cpq%29%29pkzjSBMXoSsJADlZur2jBbPO7Q4JW2hxE98WpvBxvhVYo0LhCSgV"},{"date":"2017-08-17","image":"http://s.qdcdn.com/cl/14546274,256,256.jpg","name":"08-17","url":"http://open.lovebizhi.com/bdrom/everyday?code=vyRG8Qz2SaUg9x%29ph5xqvRb1RR7TcIrVhiLtSLff8KjWJJzvtzB%7CTfxA%7C0rCXYa4VCQjTGEAvhg25YCK"}]
         * special : [{"name":"壹周壁纸精选集","desc":"壹周里的精彩壁纸不容错过，快来看看吧！","icon":"http://s.qdcdn.com/c/14540556,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/special?code=JUEoK5OvH1vjqs6JuwrOAg2DSyp6TTJphskCO81bj8TJE%7CEqE%7CCMUrF3cOIMLOua%7CiXAJw"},{"name":"轻盈的背影","desc":"采一丝暖风，揉捏成白色的鹅毛笔，在小溪，在湖面，在海边，一遍一遍浅入水中，抒写你的名字，荡起的细纹，随时光，随岁月流浪，并融入生命的华章，美好的人儿，看得见的是我眼眸里，全是你的背影，你的背影就像一条清溪，慢慢的流进了我的心里。","icon":"http://s.qdcdn.com/c/14534295,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/special?code=ySgcjcOvJj59xcdl8maC7uN1RSqdm7F8FoL5MsXzf4iNlPFcHaWaDFr4a9S5TA%7Cvi6QgKQ"},{"name":"壹周壁纸精选集","desc":"这里有没有你想领略的美图，过来看看吧！","icon":"http://s.qdcdn.com/c/14529205,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/special?code=0w1%7CL5cN0jHVFigjtarqsEN0we8oGE0Aq5qKbNklxIKUPZw%29Sdf%29udng%29Mwfs8FHexA0Mw"},{"name":"滴水美景","desc":"在细雨中行走，雨滴滴落在地上，融入水洼之中，掀起一丝涟漪。小小的雨滴，清新了空气，湿润了心灵。","icon":"http://s.qdcdn.com/c/14522730,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/special?code=cGrRALEyfOXb4oVxBrWHaxzKpWXAjCzZ46x9%29YxhI7ZXhWDJCknE6RqKgYHDBEYr2Fo8lg"},{"name":"城堡王国","desc":"在广阔的天宇下，这古老的砖墙随着群山万壑绵延伸展，跌宕起伏。那高大的城堡，像奋起的勇士，傲视长空；像沉思的巨人，默对苍穹。","icon":"http://s.qdcdn.com/c/14515600,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/special?code=WNlhrLQUh7dI2KfOHMHJkpDGREssS5xkprHdyy%29qQZqUffhTZT8nARpc%294wCKxt3xxyvhA"},{"name":"夏日冰棍的回忆","desc":"白色的奶油冰棍，一毛五一根。舔一下，便有浓浓的甜奶香裹紧舌头。夏日里，总要咬一支冰棍消暑，赶走燥热，带来清凉。","icon":"http://s.qdcdn.com/c/14505870,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/special?code=V4Xf5Ac6WSIQ2Qrlo5L%7Ck9gjioH%290LkzqBfjybkaZoXhjXPuBeKftnmWwEVfyCG%29L%29J2cQ"},{"name":"建筑空间","desc":"建筑，是离我们最近的\u201c艺术\u201d。我们每天生活在其中，建筑有美且使用的功能。建筑是一种无声的语言，在诉说着历史，展示着未来。","icon":"http://s.qdcdn.com/c/14503225,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/special?code=IjQqRa0nzBaOmYdSAS7MxeNCZlxf8iH1e01vytNlwtkSEhlmEkjil56PKy3wpQ3ECXn2iw"},{"name":"不断按下快门，记录真实的你","desc":"相机是堪比文字更能表达我想展示的东西，因情感而拍摄，不刻意追求与你无关的影像，表现真实的自己，镜头中的每个女孩，都有着自己独特的魅力。","icon":"http://s.qdcdn.com/c/14495836,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/special?code=w50O4aMkeDQkoXcmuYV0bMFdePRGCstg1LDSvL6PtUQt%29IboUOLOgZmpqZr%7CUx5UVodtug"},{"name":"大儿童，六一，happy","desc":"倘若每个儿童节都能触动心底冰封的童心，我想，你的生活是快乐的，在每个让你难过的日子里，想想一些充满童真童趣的快乐时光，愿我们在未来的时光里，都能像孩子一样快乐，致大龄儿童们。六一儿童节快乐！","icon":"http://s.qdcdn.com/c/14487923,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/special?code=Ib8kMwrkw9XqrZm%29KF1pbf0xUdTL%7CyYLas9uXP9vb%29OPJogcZFP%29fuMQTJbb3XuEZdmZeQ"},{"name":"粽飘香，舞端阳","desc":"又是一年端午时，情浓粽飘香，小爱祝大家端午节快乐。","icon":"http://s.qdcdn.com/c/14482175,256,256.jpg","url":"http://open.lovebizhi.com/bdrom/special?code=vScJxlKeS9STS0QDViyRksKIkO2OPSr5r50Otf2mp0gG0MSkSAhyfEhkS0Ck2Y5nMKlRpw"}]
         */
        var ranking: String? = null, var banner: String? = null, var wallpaper: String? = null, var recommend: String? = null, var category: List<CategoryBean>? = null, var everyday: List<EverydayBean>? = null, var special: List<SpecialBean>? = null) {

    class EverydayBean {
        /**
         * date : 2017-08-31
         * image : http://s.qdcdn.com/cl/14543477,256,256.jpg
         * name : 08-31
         * url : http://open.lovebizhi.com/bdrom/everyday?code=Y2WW%7Cm2MEIwJuz6Gq7DpVkSSLsVwCq7esMJLnLlitDj1dWsTGHvlby%294%7Cz1z5DKE3qLHJHU0srFUSugl
         */

        var date: String? = null
        var image: String? = null
        var name: String? = null
        var url: String? = null
        override fun toString(): String {
            return "EverydayBean(date=$date, image=$image, name=$name, url=$url)"
        }

    }

    class CategoryBean(
            /**
             * name : 影视明星
             * cover : http://s.qdcdn.com/cl/13608680,256,256.jpg
             * url : http://open.lovebizhi.com/bdrom/category?code=HKGqQlC0GaR7Xfc4WmsuqZYR5eye7lfgwDyMd7mQtLDWH2GLDa8OBr%7CVVKG2Jcdp
             */
            var name: String? = null, var cover: String? = null, var url: String? = null) {

        override fun toString(): String {
            return "CategoryModel(name=$name, cover=$cover, url=$url)"
        }
    }

    class SpecialBean {
        /**
         * name : 壹周壁纸精选集
         * desc : 壹周里的精彩壁纸不容错过，快来看看吧！
         * icon : http://s.qdcdn.com/c/14540556,256,256.jpg
         * url : http://open.lovebizhi.com/bdrom/special?code=JUEoK5OvH1vjqs6JuwrOAg2DSyp6TTJphskCO81bj8TJE%7CEqE%7CCMUrF3cOIMLOua%7CiXAJw
         */

        var name: String? = null
        var desc: String? = null
        var icon: String? = null
        var url: String? = null

        override fun toString(): String {
            return "SpecialBean(name=$name, desc=$desc, icon=$icon, url=$url)"
        }
    }

    override fun toString(): String {
        return "WallpaperApiModel(ranking=$ranking, banner=$banner, wallpaper=$wallpaper, recommend=$recommend, category=$category, everyday=$everyday, special=$special)"
    }

}
