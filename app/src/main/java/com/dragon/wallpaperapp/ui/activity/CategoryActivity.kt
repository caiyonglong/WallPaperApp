package com.dragon.wallpaperapp.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.WindowManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.api.GlideApp
import com.dragon.wallpaperapp.ui.fragment.WallpaperFragment
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val id: String = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val cover = intent.getStringExtra("cover")
        val type = intent.getStringExtra("type")
        toolbar.title = name
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Log.e("TAG", "$id---$name")

        // 加载网络图片
        GlideApp.with(this)
                .load(cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)


        toolbar.setNavigationOnClickListener { view ->
            onBackPressed()
        }

        tabLayout.addTab(tabLayout.newTab().setText("最新"))
        tabLayout.addTab(tabLayout.newTab().setText("最热"))

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {

            override fun getCount(): Int {
                return mTitles.size
            }

            private val mTitles = arrayOf("最新", "最热")

            override fun getItem(position: Int): Fragment {
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
