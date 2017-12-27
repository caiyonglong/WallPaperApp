package com.dragon.wallpaperapp.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.mvp.contract.MovieContract
import com.dragon.wallpaperapp.mvp.model.bean.FilmSection
import com.dragon.wallpaperapp.mvp.model.bean.Movie
import com.dragon.wallpaperapp.mvp.presenter.MoviePresenter
import com.dragon.wallpaperapp.ui.adapter.DownloadAdapter
import kotlinx.android.synthetic.main.activity_movie.*


class MovieDetailActivity : AppCompatActivity(), MovieContract.View {
    var url: String = ""
    private var mAdapter = DownloadAdapter(null)
    private val downloads = arrayListOf<String>()

    companion object {
        var WEB_URL: String = "url"
        var NAME: String = "name"
        var TAG: String = "PreImageViewActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        url = intent.getStringExtra(WEB_URL)
        val name = intent.getStringExtra("name")
        toolbar.title = name
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { _ ->
            onBackPressed()
        }
        val mPresenter = MoviePresenter()
        init()

        mPresenter.attachView(this)
        mPresenter.getDetailData(url)
    }

    private fun init() {
        rv_download.layoutManager = LinearLayoutManager(this)
        rv_download.adapter = mAdapter
        rv_download.isNestedScrollingEnabled = false
        mAdapter.setOnItemClickListener { _, _, position ->
            val clipboardManager: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager;
            //创建ClipData对象
            val clipData = ClipData.newPlainText("simple text copy", downloads[position])
            //添加ClipData对象到剪切板中
            clipboardManager.primaryClip = clipData
            Toast.makeText(this, "复制成功，你可以分享到其他地方!", Toast.LENGTH_LONG).show()
        }
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMovies(data: List<FilmSection>?) {
    }

    override fun showMovies(data: Movie) {

        val st = StringBuilder()
        if (data.detail != null) {
            for (d in data.detail!!) {
                if (d.type == "img") {
                    ll_screen.visibility = View.VISIBLE
                    GlideApp.with(this)
                            .load(d.value)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(iv_screenshot)
                    continue
                }
                if (d.type == "download") {
                    ll_download.visibility = View.VISIBLE
                    d.value?.let { downloads.add(it) }
                    continue
                }
                st.append(d.value)
                st.append("\n")
            }
        }
        // 加载网络图片
        GlideApp.with(this)
                .load(data.img)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(iv)


        tv_show.text = st.toString()
        mAdapter.setNewData(downloads)
    }

}
