package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawTextView : View {
    val path = Path()
    val paint = Paint()
    var mPreX: Float = 0f
    var mPreY: Float = 0f


    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //设置手指刚接触的位置为贝塞尔曲线的起始点
                path.moveTo(event.x, event.y)
                //手指刚刚碰到的时候的坐标点
                mPreX = event.x
                mPreY = event.y
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                //设置贝塞尔曲线的结束点
                val endx = (mPreX+event.x)/2
                val endy = (mPreY+event.y)/2
                //设置贝塞尔曲线控制点和结束点
                path.quadTo(mPreX,mPreY,endx, endy)
                mPreX = event.x
                mPreY = event.y
                invalidate()
                return true

            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 30f
        canvas.drawPath(path, paint)
    }

    fun reset() {
        path.reset()
        invalidate()
    }
}