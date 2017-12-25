package com.dragon.wallpaperapp

import com.dragon.wallpaperapp.mvp.model.bean.FilmSection
import com.dragon.wallpaperapp.mvp.model.bean.FilmData
import org.jsoup.Jsoup
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val result = "http://www.dy2018.com"
        println(result)
        val document = Jsoup.connect(result)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .get()

        val classes = document.getElementsByClass("co_area2")
        println(classes.size)
        for (cate in classes) {
            var title = cate.getElementsByTag("span")[0].text()
            var url = cate.select("em a").attr("href")
            if (!url.contains(result)) {
                url = result + url
            }
            var lists = arrayListOf<FilmData>()
            val links = cate.select("li")
            for (link in links) {
                val linktitle = link.select("a").attr("title")
                val linkHref = link.select("a").attr("href")
                val linkDate = link.select("span").text()
                println(linktitle)
                println(linkHref)
                println(linkDate)
                var data = FilmData(linktitle, linkHref, linkDate)
                lists.add(data)
            }
            var categoryfilm = FilmSection(title, url, lists)
        }
    }

}
