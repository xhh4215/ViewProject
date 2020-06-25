package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BitmapCanvasView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {


    val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
    var paint = Paint()

    var bitmapCanvas = Canvas(bitmap)

    init {
        paint.color = Color.RED
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.strokeWidth = 20f
        //此时的文字是绘制在通过bitmap获取的canvas对象了 而不是绘制在当前的自定义View上
        bitmapCanvas.drawText("这里是栾小黑", 100f, 100f, paint)

    }
}