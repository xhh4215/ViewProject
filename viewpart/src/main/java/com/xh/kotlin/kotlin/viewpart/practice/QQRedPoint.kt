package com.xh.kotlin.kotlin.viewpart.practice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin

class QQRedPoint : FrameLayout {
    private lateinit var startPoint: PointF
    private lateinit var currentPointF: PointF
    private var mRadius = 20f
    private var paint = Paint()
    private var path = Path()
    private var mTouch = false

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        initView()

    }

    private fun initView() {
        startPoint = PointF(100f, 100f)
        currentPointF = PointF()
        paint.color = Color.RED
        paint.style = Paint.Style.FILL

    }

    /****
     * 在ViewGroup中绘制复写这个方法
     */
    override fun dispatchDraw(canvas: Canvas) {
        canvas.saveLayer(
            RectF(0f, 0f, width.toFloat(), height.toFloat()),
            paint,
            Canvas.ALL_SAVE_FLAG
        )
        canvas.drawCircle(startPoint.x, startPoint.y, mRadius, paint)

        if (mTouch) {
            calculatePath()
            canvas.drawPath(path,paint)
            canvas.drawCircle(currentPointF.x, currentPointF.y, mRadius, paint)
        }
        canvas.restore()
        super.dispatchDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mTouch = true
            }
            MotionEvent.ACTION_UP -> {
                mTouch = false
            }
        }

        currentPointF.set(event.x, event.y)
        postInvalidate()
        return true

    }


    private fun calculatePath() {
        var x = currentPointF.x
        var y = currentPointF.y

        var startX = startPoint.x
        var startY = startPoint.y


        var dx = x - startX
        var dy = y - startY

        var a = atan(dy / dx.toDouble())


        var offsetX = mRadius * cos(a)
        var offsetY = mRadius * sin(a)


        var x1 = (startX - offsetX).toFloat()
        var y1 = (startY + offsetY).toFloat()


        var x2 = (x - offsetX).toFloat()
        var y2 = (y + offsetY).toFloat()


        var x3 = (x + offsetX).toFloat()
        var y3 = (y - offsetY).toFloat()

        var x4 = (startX + offsetX).toFloat()
        var y4 = (startY - offsetY).toFloat()


        var anchorX = (startX + x) / 2
        var anchorY = (startY + y) / 2

        path.reset()
        path.moveTo(x1, y1)
        path.quadTo(anchorX,anchorY,x2,y2)
        path.lineTo(x3,y3)
        path.quadTo(anchorX,anchorY,x4,y4)
        path.lineTo(x1,y1)
    }
}