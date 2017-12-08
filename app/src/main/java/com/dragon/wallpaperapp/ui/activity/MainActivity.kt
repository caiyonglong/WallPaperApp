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


class MainActivity : AppCompatActivity(), BottomNavigationBar.OnTabSelectedListener {
    private var lastSelectedPosition: Int = 0
    private var bottomNavigationBar: BottomNavigationBar? = null
    private var mCategoryFragment: CategoryFragment? = null
    private var mHomePageFragment: HomePageFragment? = null
    private val WALL_TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationBar = findViewById<View>(R.id.bottom_navigation_bar) as BottomNavigationBar

        bottomNavigationBar!!
                .addItem(BottomNavigationItem(R.drawable.ic_photo_library, R.string.wp_homepage).setActiveColorResource(R.color.teal_500))
                .addItem(BottomNavigationItem(R.drawable.ic_wallpaper, R.string.wp_category).setActiveColorResource(R.color.orange_500))
                .addItem(BottomNavigationItem(R.drawable.ic_wallpaper, R.string.wp_new).setActiveColorResource(R.color.blue_500))
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
        if (mHomePageFragment == null)
            mHomePageFragment = HomePageFragment.newInstance("hot")
        transaction.replace(R.id.fragment_container, mHomePageFragment)
        transaction.commit()
    }

    private fun initCategoryFragment() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        if (mCategoryFragment == null)
            mCategoryFragment = CategoryFragment()
        transaction.replace(R.id.fragment_container, mCategoryFragment)
        transaction.commit()
    }

//    private fun initRankingFragment() {
//        val fm = supportFragmentManager
//        val transaction = fm.beginTransaction()
//        if (mRankingFragment == null)
//            mRankingFragment = RankingFragment()
//        transaction.replace(R.id.fragment_container, mRankingFragment)
//        transaction.commit()
//    }

    override fun onTabSelected(position: Int) {
        Log.d(WALL_TAG, "onTabSelected() called with: position = [$position]")
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
//                initRankingFragment()
            }
        }
        // 事务提交
        transaction.commit()
    }

    override fun onTabUnselected(position: Int) {
        Log.d(WALL_TAG, "onTabUnselected() called with: position = [$position]")
    }

    override fun onTabReselected(position: Int) {
        Log.d(WALL_TAG, "onTabUnselected() called with: position = [$position]")
    }
}


