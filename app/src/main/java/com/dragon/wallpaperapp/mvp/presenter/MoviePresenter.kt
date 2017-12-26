package com.dragon.wallpaperapp.mvp.presenter

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.dragon.wallpaperapp.mvp.contract.MovieContract
import com.dragon.wallpaperapp.mvp.model.bean.FilmData
import com.dragon.wallpaperapp.mvp.model.bean.FilmSection
import org.jsoup.Jsoup

/**
 * Created by yonglong on 2017/12/24.
 */
class MoviePresenter : MovieContract.Presenter {

    private var films = arrayListOf<FilmSection>()

    lateinit var mView: MovieContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
    }

    override fun attachView(view: MovieContract.View) {
        mView = view
    }

    override fun detachView() {
    }

    override fun getData() {
        MovieLoader().execute()
    }

    fun getDetailData(url: String) {
        MovieDetailLoader().execute(url)
    }

    fun getDataFromUrl(): List<FilmSection> {
        val result = "http://www.dy2018.com"
        println(result)
        try {
            val document = Jsoup.connect(result)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .get()
            val classes = document.getElementsByClass("co_area2")
            println(classes.size)
            films.clear()
            for (cate in classes) {
                var title = cate.getElementsByTag("span")[0].text()
                var url = cate.select("em a").attr("href")
                if (!url.contains(result)) {
                    url = result + url
                }

                var section = FilmSection(true, title, url)
                films.add(section)

                val links = cate.select("li")
                for (link in links) {
                    val linkTitle = link.select("a").attr("title")
                    var linkHref = link.select("a").attr("href")
                    val linkDate = link.select("span").text()
                    println(linkTitle)
                    println(linkHref)
                    println(linkDate)
                    if (!linkHref.contains(result)) {
                        linkHref = result + linkHref
                    }
                    films.add(FilmSection(FilmData(linkTitle, linkDate, linkHref)))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return films
    }

    fun getDataFromUrl(url: String): String {
        println(url)
        val document = Jsoup.connect(url)
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .get()

        val classes = document.getElementsByClass("co_area2")
        println(classes.text())
        return classes.text()
    }

    @SuppressLint("StaticFieldLeak")
    inner class MovieLoader : AsyncTask<String, Int, List<FilmSection>>() {

        override fun doInBackground(vararg params: String?): List<FilmSection> {
            return getDataFromUrl()
        }

        override fun onPostExecute(result: List<FilmSection>?) {
            super.onPostExecute(result)
            mView.showMovies(result)
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class MovieDetailLoader : AsyncTask<String, Int, String>() {

        override fun doInBackground(vararg params: String): String {
            return getDataFromUrl(params[0])
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            mView.showMovies(result)
        }
    }

}