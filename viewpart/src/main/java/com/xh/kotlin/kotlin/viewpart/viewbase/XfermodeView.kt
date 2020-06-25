package com.xh.kotlin.kotlin.viewpart.viewbase

import android.content.Context
import android.graphics.*
import android.graphics.Canvas.ALL_SAVE_FLAG
import android.util.AttributeSet
import android.view.View
import java.util.stream.DoubleStream


class XfermodeView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    var paint = Paint()
    var dstbitmap: Bitmap
    var srcbitmap: Bitmap


    init {
        val width = 400f
        val height = 400f
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        dstbitmap = makeDst(width, height)
        srcbitmap = makeSrc(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //新建图层
        val layerID =
            canvas.saveLayer(
                0f,
                0f,
                (width * 2).toFloat(),
                (height * 2).toFloat(),
                paint,
                ALL_SAVE_FLAG
            )
        canvas.drawBitmap(dstbitmap, 0f, 0f, paint)
        //SRC_IN
//        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        //CLEAR
//        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST)
        canvas.drawBitmap(srcbitmap, 200f, 200f , paint)
        //还原图层
        canvas.restoreToCount(layerID)

    }


    fun makeDst(width: Float, height: Float): Bitmap {
        val DstBitmap = Bitmap.createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(DstBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.YELLOW
        canvas.drawOval(RectF(0f, 0f, width, height), paint)
        return DstBitmap

    }

    fun makeSrc(width: Float, height: Float): Bitmap {
        val SrcBitmap = Bitmap.createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(SrcBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLUE
        canvas.drawRect(RectF(0f, 0f, width, height), paint)
        return SrcBitmap

    }
}