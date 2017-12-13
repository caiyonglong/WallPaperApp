package com.dragon.wallpaperapp.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button

/**
 * Created by D22434 on 2017/12/13.
 */

@SuppressLint("AppCompatCustomView")
class AlphaDisableableButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : Button(context, attrs, defStyleAttr) {

    init {
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (enabled) {
            alpha = 1.0f
        } else {
            alpha = DISABLED_ALPHA_VALUE
        }
    }

    companion object {
        var DISABLED_ALPHA_VALUE = 0.4f
    }

}
