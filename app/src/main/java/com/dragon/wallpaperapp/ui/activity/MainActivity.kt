package com.dragon.wallpaperapp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.ui.fragment.CategoryFragment
import com.dragon.wallpaperapp.ui.fragment.HomePageFragment
import com.dragon.wallpaperapp.ui.fragment.RankingFragment


class MainActivity : AppCompatActivity(), BottomNavigationBar.OnTabSelectedListener {
    var lastSelectedPosition: Int = 0
    var bottomNavigationBar: BottomNavigationBar? = null
    var mCategoryFragment: CategoryFragment? = null
    var mHomePageFragment: HomePageFragment? = null
    var mRankingFragment: RankingFragment? = null
    var TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationBar = findViewById<View>(R.id.bottom_navigation_bar) as BottomNavigationBar

        bottomNavigationBar!!
                .addItem(BottomNavigationItem(R.drawable.ic_photo_library, "index").setActiveColor(R.color.teal_500))
                .addItem(BottomNavigationItem(R.drawable.ic_wallpaper, "wallpaper").setActiveColor(R.color.orange_500))
                .addItem(BottomNavigationItem(R.drawable.ic_wallpaper, "category").setActiveColor(R.color.blue_500))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise()


        bottomNavigationBar!!.setTabSelectedListener(this);
        setDefaultFragment()

    }

    /**
     * 设置默认的
     */
    private fun setDefaultFragment() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        mHomePageFragment = HomePageFragment()
        transaction.replace(R.id.fragment_container, mHomePageFragment)
        transaction.commit()
    }

    private fun initCategoryFragment() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        mCategoryFragment = CategoryFragment()
        transaction.replace(R.id.fragment_container, mCategoryFragment)
        transaction.commit()
    }

    private fun initRankingFragment() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        mRankingFragment = RankingFragment()
        transaction.replace(R.id.fragment_container, mRankingFragment)
        transaction.commit()
    }

    override fun onTabSelected(position: Int) {
        Log.d(TAG, "onTabSelected() called with: position = [$position]")
        val fm = this.fragmentManager
        //开启事务
        val transaction = fm.beginTransaction()
        when (position) {
            0 -> {
                setDefaultFragment()
            }
            1 -> {
                initCategoryFragment()
            }
            2 -> {
                initRankingFragment()
            }
        }
        // 事务提交
        transaction.commit()
    }

    override fun onTabUnselected(position: Int) {
        Log.d(TAG, "onTabUnselected() called with: position = [$position]")
    }

    override fun onTabReselected(position: Int) {
        Log.d(TAG, "onTabUnselected() called with: position = [$position]")
    }
}


