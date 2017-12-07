package com.dragon.wallpaperapp.ui.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.WallpaperDisplayContract
import com.dragon.wallpaperapp.mvp.model.Wallpaper
import com.dragon.wallpaperapp.mvp.presenter.WallpaperDisplayPresenter
import kotlinx.android.synthetic.main.activity_wallpaper.*
import java.util.*

/**
 * Created by D22434 on 2017/12/6.
 */
class WallpaperDisplayActivity : AppCompatActivity(), WallpaperDisplayContract.View {

    var mPresenter: WallpaperDisplayPresenter = WallpaperDisplayPresenter()

    private var mWallpapers: List<Wallpaper> = ArrayList<Wallpaper>() as List<Wallpaper>
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        mPresenter.attachView(this)
        init()
        cropView.setOnClickListener { mPresenter.toggle() }
        btn_set_wallpaper.setOnTouchListener(mDelayHideTouchListener)
    }

    private fun init() {
        mWallpapers = intent.getParcelableArrayListExtra("wallpapers")
        position = intent.getIntExtra("position", -1)
        Log.e("TAG", "mWallpaper = " + mWallpapers.size + "poi = " + position)
        if (position == -1) {
            return
        }
        mPresenter.loadData(mWallpapers, position)
        btn_set_wallpaper.setOnClickListener { v ->
            Toast.makeText(this, "设置壁纸中", Toast.LENGTH_SHORT).show()
            mPresenter.setWallpaper()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mPresenter.delayedHide(100)
    }

    override fun setBitmap(bitmap: Bitmap?) {
        if (bitmap != null) {
            cropView.setImageBitmap(bitmap)
        }
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun setWallpaper() {

    }

    override fun setFullScreen() {
        cropView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    override fun setNormalScreen() {
        actionBar?.show()
        fullscreen_content_controls.visibility = View.VISIBLE
    }

    override fun show() {
        cropView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

    override fun hide() {
        actionBar?.hide()
        fullscreen_content_controls.visibility = View.GONE
    }

    private val mDelayHideTouchListener = View.OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            mPresenter.delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    companion object {
        private val AUTO_HIDE = true
        private val AUTO_HIDE_DELAY_MILLIS = 3000
        private val UI_ANIMATION_DELAY = 300
    }

}