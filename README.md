# WallpaperApp
---

本项目主要采用Kotlin语言编写,学习Kotlin 和适应java8 Lambda表达式的一个工程。结合mvp+rxjava2+retrofit2+glide项目架构，开发的一款小应用。
其中壁纸api主要来自 爱壁纸 应用，使用Fiddler抓取的api，找出有规律的api
影视来源于电影天堂，使用 **jsoup** 抓取网页数据。

## Preview

![](https://github.com/caiyonglong/WallPaperApp/blob/master/art/p1.png)
![](https://github.com/caiyonglong/WallPaperApp/blob/master/art/p2.png)
![](https://github.com/caiyonglong/WallPaperApp/blob/master/art/p3.png)
![](https://github.com/caiyonglong/WallPaperApp/blob/master/art/p4.png)
![](https://github.com/caiyonglong/WallPaperApp/blob/master/art/p5.png)
![](https://github.com/caiyonglong/WallPaperApp/blob/master/art/p6.png)

## TODO

- [x] 阅读 Android 原生壁纸应用WallpaperPicker
- [x] 图片加载优化
- [x] 增加附加功能
- [ ] 优化

## 使用到的技术点

 - [RxJava2](https://github.com/ReactiveX/RxJava)
 - [Retrofit2](https://github.com/square/retrofit)
 - [Glide](https://github.com/bumptech/glide)
 - [Logger](https://github.com/orhanobut/logger)
 - [FlycoTabLayout](https://github.com/H07000223/FlycoTabLayout)
 - [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)
 - [MZBannerView](https://github.com/pinguo-zhouwei/MZBannerView)
 - [jsoup](https://github.com/jhy/jsoup)
 
## 遇到的问题

1. 使用MZBannerView，出现点击事件无反应：点击事件在set之前初始化即可
2. 使用BaseRecyclerViewAdapterHelper，上拉加载死循环：madapter没有设置recyclerview
...

## 声明

项目中的 API 均来自三方，纯属学习交流使用，不得用于商业用途

## License

开源协议[Apache-2.0](https://opensource.org/licenses/apache2.0.php)