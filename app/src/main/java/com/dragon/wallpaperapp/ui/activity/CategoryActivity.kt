package com.dragon.wallpaperapp.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.ui.fragment.WallpaperFragment
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private var id: String = ""
    private var name: String = ""
    private var cover: String = ""
    private var type: String = ""
    private var desc: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        initData()
        initView()
    }

    private fun initData() {
        id = intent.getStringExtra("id")
        name = intent.getStringExtra("name")
        cover = intent.getStringExtra("cover")
        type = intent.getStringExtra("type")
        desc = intent.getStringExtra("desc")
        toolbar.title = name
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { _ ->
            onBackPressed()
        }
    }

    private fun initView() {
        if (desc != null) {
            tv_desc.visibility = View.VISIBLE
            tv_desc.text = desc
        }

        // 加载网络图片
        GlideApp.with(this)
                .load(cover)
                .placeholder(R.drawable.ic_default_preview)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
        tabLayout.addTab(tabLayout.newTab().setText("最新"))
        tabLayout.addTab(tabLayout.newTab().setText("最热"))
        viewPager.adapter = object : androidx.fragment.app.FragmentPagerAdapter(supportFragmentManager) {

            override fun getCount(): Int {
                return mTitles.size
            }

            private val mTitles = arrayOf("最新", "最热")

            override fun getItem(position: Int): androidx.fragment.app.Fragment {
                return if (position == 0) {
                    WallpaperFragment.newInstance(id, "new", type)
                } else {
                    WallpaperFragment.newInstance(id, "hot", type)
                }
            }

            override fun getPageTitle(position: Int): CharSequence {
                return mTitles[position]
            }
        }
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}
