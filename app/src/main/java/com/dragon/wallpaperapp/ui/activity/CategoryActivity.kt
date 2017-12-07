package com.dragon.wallpaperapp.ui.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dragon.wallpaperapp.R
import com.dragon.wallpaperapp.ui.fragment.WallpaperFragment
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private var mWallpaperFragment: WallpaperFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        var id = intent.extras["id"]
        var name = intent.extras["name"]
        Log.e("TAG", "$id---$name")
        setDefaultFragment("$id")
    }

    /**
     * 设置默认的
     */
    private fun setDefaultFragment(id: String) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        mWallpaperFragment = WallpaperFragment.newInstance(id)
        transaction.replace(R.id.fragment_container, mWallpaperFragment)
        transaction.commit()
    }

}
