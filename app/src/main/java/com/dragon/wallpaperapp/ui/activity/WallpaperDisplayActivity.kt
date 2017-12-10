package com.dragon.wallpaperapp.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.contract.WallpaperDisplayContract
import com.dragon.wallpaperapp.mvp.model.Wallpaper
import com.dragon.wallpaperapp.mvp.presenter.WallpaperDisplayPresenter
import com.dragon.wallpaperapp.ui.adapter.ImageAdapter
import kotlinx.android.synthetic.main.activity_wallpaper.*
import java.util.*

/**
 * Created by D22434 on 2017/12/6.
 */
class WallpaperDisplayActivity : AppCompatActivity(), WallpaperDisplayContract.View {

    var mPresenter: WallpaperDisplayPresenter = WallpaperDisplayPresenter()

    private var mWallpapers: List<Wallpaper> = ArrayList<Wallpaper>() as List<Wallpaper>
    private var position: Int = -1
    var mAdapter: ImageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        mPresenter.attachView(this)
        init()
    }

    private fun init() {
        mWallpapers = intent.getParcelableArrayListExtra("wallpapers")
        position = intent.getIntExtra("position", -1)
        Log.e("TAG", "mWallpaper = " + mWallpapers.size + "poi = " + position)
        if (position == -1) {
            return
        }
        mPresenter.loadData(mWallpapers, position)
        btn_set_wallpaper.setOnClickListener { _ ->
            AlertDialog.Builder(this)
                    .setItems(R.array.which_wallpaper_options, DialogInterface.OnClickListener { dialog, which ->
                        mPresenter.setWallpaper(viewpager.currentItem, which)
                    })
                    .create()
                    .show()
        }
    }

    override fun setImageList(strDrawables: List<ImageView>, position: Int) {
        mAdapter = ImageAdapter(this, strDrawables)
        viewpager.adapter = mAdapter
        viewpager.currentItem = position
        viewpager.offscreenPageLimit = 3
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun setWallpaper() {

    }


}