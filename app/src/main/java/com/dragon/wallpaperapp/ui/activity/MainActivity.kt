package com.dragon.wallpaperapp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.mvp.model.bean.TabEntity
import com.dragon.wallpaperapp.ui.fragment.CategoryFragment
import com.dragon.wallpaperapp.ui.fragment.MainFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var defaultIndex: Int = 0
    private var mCategoryFragment: CategoryFragment? = null
    private var mHotFragment: MainFragment? = null
    private val TAG: String = "MainActivity"

    private var mTabEntities: ArrayList<CustomTabEntity> = arrayListOf()

    var titles = arrayOf("壁纸", "电影天堂")
    var checked = intArrayOf(R.drawable.ic_photo_library, R.drawable.ic_wallpaper)
    var normal = intArrayOf(R.drawable.ic_photo_library, R.drawable.ic_wallpaper)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTabLayout()
        switchFragment(defaultIndex)
    }

    private fun initTabLayout() {
        for (i in 0 until titles.size) {
            mTabEntities.add(TabEntity(titles[i], checked[i], normal[i]))
        }

        commonTabLayout.setTabData(mTabEntities)
        commonTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //TODO 选择
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {
                //TODO 重选
            }
        })
    }


    private fun switchFragment(position: Int) {
        Log.d(TAG, "onTabSelected() called with: position = [$position]")
        val fm = supportFragmentManager
        //开启事务
        val transaction = fm.beginTransaction()
        when (position) {
            0 -> {
                if (mHotFragment == null) {
                    mHotFragment = MainFragment()
                    transaction.add(R.id.fragment_container, mHotFragment)
                } else {
                    transaction.show(mHotFragment)
                    if (mCategoryFragment != null)
                        transaction.hide(mCategoryFragment)
                }
            }
            1 -> {
                if (mCategoryFragment == null) {
                    mCategoryFragment = CategoryFragment()
                    transaction.add(R.id.fragment_container, mCategoryFragment)
                } else {
                    transaction.show(mCategoryFragment)
                    if (mHotFragment != null)
                        transaction.hide(mHotFragment)
                }
            }
        }
        commonTabLayout.currentTab = position
        defaultIndex = position
        // 事务提交
        transaction.commitAllowingStateLoss()
    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}


