package com.dragon.wallpaperapp.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView

/**
 * Created by D22434 on 2017/12/13.
 */

@SuppressLint("AppCompatCustomView")
class CropView : ImageView {
    // view的中心点坐标
    private var centerX: Float = 0.0f
    private var centerY: Float = 0.0f
    // view的宽、高
    private var w: Int = 0
    private var h: Int = 0
    // 屏幕的宽、高
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    private var mMatrix: Matrix? = null
    // ImageView的资源图片
    internal var mBitmap: Bitmap? = null
    // 旋转过的角度
    var totalDegree = 0f
    // 是否初始化过
    private var isInit = false

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 已初始化完毕的话那就直接滚回老家去吧
        if (isInit) return
        var measureWidth = width
        var measureHeight = height
        Log.e("CropView", "onMeasure: w: $measureWidth, h: $measureHeight")

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

    }

    fun refreshLayout(isPortrait: Boolean) {
        if (isPortrait) {

        } else {

        }
    }
}
