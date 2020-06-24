package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class MyView : View {
    constructor(context: Context, attributeSet: AttributeSet) :
            super(context, attributeSet)


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val baseLineX = 0f
        val baseLineY = 200f
        val paint = Paint()
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        val path = Path()
        path.moveTo(100f, 100f)
        path.quadTo(200f, 0f, 300f, 100f)
        paint.color = Color.BLUE
        paint.strokeWidth = 20f
        canvas.drawPath(path, paint)
//        canvas.drawLine(baseLineX,baseLineY,3000f,baseLineY,paint)
//
//        paint.color = Color.GREEN
//        paint.textSize = 120f
//        绘制的文字和基线x坐标的相对位置
//        paint.textAlign = Paint.Align.CENTER
//        canvas.drawText("栾小黑的blog",baseLineX,baseLineY,paint)


    }
}