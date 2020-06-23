package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class CanvasView(context: Context) : View(context) {
    val paint = Paint()

    init {
        paint.color = Color.GREEN
        paint.isAntiAlias = true
        paint.strokeWidth = 12f
        paint.style = Paint.Style.STROKE

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val rect = Rect(100, 100, 600, 300)
        canvas.drawRect(rect, paint)
        //平移画布
//        canvas.translate(100f,100f)
        //旋转画布
//        canvas.rotate(20f)
        //指定旋转的中心点
//        canvas.rotate(20f, 100f, 100f)
        //缩放操作
//        canvas.scale(1f,2f)
        //扭曲
//        canvas.skew(1.723f, 0f)
//        paint.color = Color.RED
//        canvas.drawRect(rect, paint)
//        canvas.drawColor(Color.RED)
//        canvas.clipRect(Rect(100, 100, 1000, 1000))
//        canvas.drawColor(Color.GREEN)
          canvas.drawColor(Color.RED)
          canvas.save()
          canvas.clipRect( Rect(10,10,100,100))
          canvas.restore()
          canvas.drawColor(Color.BLUE)


    }
}