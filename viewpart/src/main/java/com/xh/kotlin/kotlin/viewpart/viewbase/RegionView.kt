package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.*
import android.view.View

/***
 *
 */
class RegionView : View {
    private var paint = Paint()

    constructor(context: Context) : super(context) {
        paint.initPaint()

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        drawRegionOne(canvas)
//        drawRegionTwo(canvas)
           drawRegionThree(canvas)
    }

    fun Paint.initPaint() {
        this.color = Color.RED
        this.style = Paint.Style.FILL
        this.strokeWidth = 12f
    }

    fun drawRegionOne(canvas: Canvas) {
        val region = Region(10, 10, 100, 100)
        drawRegion(canvas, region, paint)

    }


    fun drawRegionTwo(canvas: Canvas) {
        paint.color = Color.BLUE
        val ovalpath = Path()
        val ovalrect = RectF(50f, 50f, 200f, 500f)
        ovalpath.addOval(ovalrect, Path.Direction.CCW)

        val ovalregion = Region()
        ovalregion.setPath(ovalpath, Region(50, 50, 200, 200))
        drawRegion(canvas, ovalregion, paint)
    }


    fun drawRegionThree(canvas: Canvas) {
        val rect1 = Rect(100, 100, 400, 200)
        val rect2 = Rect(200, 0, 300, 300)
        canvas.drawRect(rect1, paint)
        canvas.drawRect(rect2, paint)
        val region1 = Region(rect1)
        val region2 = Region(rect2)
        region1.op(region2, Region.Op.INTERSECT)
        paint.style = Paint.Style.FILL
        paint.color = Color.YELLOW
        drawRegion(canvas,region1,paint)

    }

    fun drawRegion(canvas: Canvas, region: Region, paint: Paint) {
        val iter = RegionIterator(region)
        val rect = Rect()
        while (iter.next(rect)) {
            canvas.drawRect(rect, paint)

        }
    }

}