package com.dragon.wallpaperapp.mvp.presenter

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.dragon.wallpaperapp.mvp.contract.MovieContract
import com.dragon.wallpaperapp.mvp.model.bean.CategoryFilm
import com.dragon.wallpaperapp.mvp.model.bean.FilmData
import org.jsoup.Jsoup

/**
 * Created by yonglong on 2017/12/24.
 */
class MoviePresenter : MovieContract.Presenter {

    private var films = arrayListOf<CategoryFilm>()

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

    fun getDataFromUrl(): List<CategoryFilm> {
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
                var data = FilmData(linktitle, linkHref, linkDate)
                lists.add(data)
            }
            var categoryfilm = CategoryFilm(title, url, lists)
            films.add(categoryfilm)
        }
        return films
    }

    @SuppressLint("StaticFieldLeak")
    inner class MovieLoader : AsyncTask<String, Int, List<CategoryFilm>>() {

        override fun doInBackground(vararg params: String?): List<CategoryFilm> {
            return getDataFromUrl()
        }

        override fun onPostExecute(result: List<CategoryFilm>?) {
            super.onPostExecute(result)
            mView.showMovies(result)
        }
    }

}