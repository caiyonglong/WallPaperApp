package com.dragon.wallpaperapp.ui.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.ui.fragment.HomePageFragment

import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private var mHomePageFragment: HomePageFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        setDefaultFragment()
    }

    /**
     * 设置默认的
     */
    private fun setDefaultFragment() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        if (mHomePageFragment == null)
            mHomePageFragment = HomePageFragment()
        transaction.replace(R.id.fragment_container, mHomePageFragment)
        transaction.commit()
    }

}
