package com.dragon.wallpaperapp.ui.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
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


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
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
                if (position == 0) {
                    return WallpaperFragment.newInstance(id, "new", "category")
                } else {
                    return WallpaperFragment.newInstance(id, "hot", "category")
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
