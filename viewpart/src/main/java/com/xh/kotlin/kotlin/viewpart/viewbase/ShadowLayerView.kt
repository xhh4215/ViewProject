package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.xh.kotlin.kotlin.viewpart.R

class ShadowLayerView : View {
    private var isClear = false
    var paint = Paint()
    var Dx = 0
    var Dy = 0
    var radius = 0
    lateinit var bitmap: Bitmap

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
        paint.isAntiAlias = true
        paint.color = Color.GREEN
        paint.textSize = 50f
//        bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.smallpz)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isClear) {
            paint.clearShadowLayer()
        } else {
            paint.setShadowLayer(radius.toFloat(), Dx.toFloat(), Dy.toFloat(), Color.GRAY)

        }

        canvas.drawText("栾小黑的blog", 300f, 300f, paint)
        canvas.drawCircle(500f, 500f, 50f, paint)
//        canvas.drawBitmap(bitmap,null,Rect(200,300,200+bitmap.width,300+bitmap.height),paint)

    }


    fun AddShadowLayerX(value: Int) {
        Dx += value
        postInvalidate()
    }

    fun AddShadowLayerY(value: Int) {
        Dy += value
        postInvalidate()
    }

    fun AddShadowLayerR(value: Int) {
        radius += value
        postInvalidate()
    }

    fun ClearShadowLayer() {
        if (!isClear) {
            isClear = true
        }
        postInvalidate()
    }
}