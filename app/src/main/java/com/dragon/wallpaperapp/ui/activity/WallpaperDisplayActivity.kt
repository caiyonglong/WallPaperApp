package com.dragon.wallpaperapp.ui.activity

import android.content.DialogInterface
import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.e
import android.view.View
import android.view.WindowManager
import com.chad.library.adapter.base.BaseQuickAdapter
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
    lateinit var mAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        mPresenter.attachView(this)

        init()
    }

    private fun init() {
        //init wallpaper_list
        wallpaper_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        mAdapter = ImageAdapter(null)
        wallpaper_list.adapter = mAdapter

        //init cropView
        showLoading()

        //get data from prev screen
        initData()

        //presenter load data
        mPresenter.loadData(mWallpapers, position)

        //
        btn_set_wallpaper.setOnClickListener { _ ->
            AlertDialog.Builder(this)
                    .setItems(R.array.which_wallpaper_options, DialogInterface.OnClickListener { dialog, which ->
                        mPresenter.saveWallpaper(mWallpapers[position].wp!!, which)
                    })
                    .create()
                    .show()
        }

        //点击事件
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            mAdapter.checkedID = position
            this.position = position
            mAdapter.notifyDataSetChanged()
            mPresenter.showPreview(mWallpapers[position].wp!!)
        }

        cropView.setOnClickListener { v ->
            if (wallpaper_scroll_container.visibility == 0) {
                wallpaper_scroll_container.visibility = View.INVISIBLE
            } else {
                wallpaper_scroll_container.visibility = View.VISIBLE
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        var orientation = newConfig!!.orientation

        e("TAG", newConfig!!.orientation as String + "---")
        if (orientation == ORIENTATION_PORTRAIT) {

        } else {

        }
        cropView.refreshDrawableState()
    }

    private fun initData() {
        mWallpapers = intent.getParcelableArrayListExtra("wallpapers")
        position = intent.getIntExtra("position", -1)
        e("TAG", "mWallpaper = " + mWallpapers.size + "poi = " + position)
        //没有数据直接返回
        if (position == -1) {
            finish()
        }
        mAdapter.setNewData(mWallpapers)
        mAdapter.checkedID = position
        mAdapter.notifyDataSetChanged()
        wallpaper_list.scrollToPosition(position)
    }

    override fun showLoading() {
        //init CropView
        cropView.visibility = View.INVISIBLE
        //init Progress
        loading.visibility = View.VISIBLE

    }

    override fun hideLoading() {
        //init CropView
        cropView.visibility = View.VISIBLE
        //init Progress
        loading.visibility = View.INVISIBLE
    }

    override fun preViewWallpaper(mBitmap: Bitmap) {
        cropView.setImageBitmap(mBitmap)
    }

}