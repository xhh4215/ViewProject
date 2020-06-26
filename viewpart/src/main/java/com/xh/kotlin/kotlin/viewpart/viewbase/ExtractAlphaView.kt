package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.xh.kotlin.kotlin.viewpart.R

class ExtractAlphaView : View {
    var paint = Paint()
    lateinit var bitmap: Bitmap
    lateinit var alphaBitmap: Bitmap

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defstyle: Int) : super(
        context,
        attributeSet,
        defstyle
    ) {
        init()

    }

    fun init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.smallpz)
        // 新建一张仅具有Alpha值的空白图像
        alphaBitmap = bitmap.extractAlpha()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.maskFilter = BlurMaskFilter(10f,BlurMaskFilter.Blur.NORMAL)
        canvas.drawBitmap(alphaBitmap,null, Rect(100,100,300,300),paint)

        paint.maskFilter = null
        canvas.drawBitmap(bitmap,null,Rect(90,90,290,290),paint)

    }
}