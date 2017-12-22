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
import com.dragon.wallpaperapp.ui.fragment.WallpaperFragment


class MainActivity : AppCompatActivity(), BottomNavigationBar.OnTabSelectedListener {
    private var lastSelectedPosition: Int = 0
    private var bottomNavigationBar: BottomNavigationBar? = null
    private var mCategoryFragment: CategoryFragment? = null
    private var mHotFragment: HomePageFragment? = null
    private var mNewFragment: WallpaperFragment? = null
    private val WALL_TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        bottomNavigationBar = findViewById<View>(R.id.bottom_navigation_bar) as BottomNavigationBar

        bottomNavigationBar!!
                .addItem(BottomNavigationItem(R.drawable.ic_photo_library, R.string.wp_recommend).setActiveColorResource(R.color.teal_500))
                .addItem(BottomNavigationItem(R.drawable.ic_photo_library, R.string.wp_new).setActiveColorResource(R.color.teal_500))
                .addItem(BottomNavigationItem(R.drawable.ic_wallpaper, R.string.wp_category).setActiveColorResource(R.color.orange_500))
//                .addItem(BottomNavigationItem(R.drawable.ic_wallpaper, R.string.wp_new).setActiveColorResource(R.color.blue_500))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise()


        bottomNavigationBar!!.setTabSelectedListener(this);
        setDefaultFragment()

    }

    private fun init() {
        
    }

    /**
     * 设置默认的
     */
    private fun setDefaultFragment() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        if (mHotFragment == null)
            mHotFragment = HomePageFragment.newInstance("hot")
        transaction.replace(R.id.fragment_container, mHotFragment)
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

    private fun initNewFragment() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        if (mNewFragment == null)
            mNewFragment = WallpaperFragment.newInstance("", "new", "new")
        transaction.replace(R.id.fragment_container, mNewFragment)
        transaction.commit()
    }

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
                initNewFragment()
            }
            2 -> {
                initCategoryFragment()
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


