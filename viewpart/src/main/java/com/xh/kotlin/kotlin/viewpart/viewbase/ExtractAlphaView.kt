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
    private var srcBitmap = 0
    private var shadowX = 0
    private var shadowY = 0
    private var shadowColor = 0
    private var shadowRedius = 0f


    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defstyle: Int) : super(
        context,
        attributeSet,
        defstyle
    ) {
        init(context, attributeSet)

    }

    fun init(context: Context, attributeSet: AttributeSet) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        val intArray = context.obtainStyledAttributes(attributeSet, R.styleable.ExtractAlphaView)
        srcBitmap = intArray.getResourceId(R.styleable.ExtractAlphaView_src, R.drawable.smallpz)
        shadowX = intArray.getInt(R.styleable.ExtractAlphaView_shadowX, 0)
        shadowY = intArray.getInt(R.styleable.ExtractAlphaView_shadowY, 0)
        shadowColor = intArray.getInt(R.styleable.ExtractAlphaView_shadowColor, R.color.colorAccent)
        shadowRedius = intArray.getFloat(R.styleable.ExtractAlphaView_shadowRadius, 10f)
        intArray.recycle()
        bitmap = BitmapFactory.decodeResource(this.resources, srcBitmap)
        // 新建一张仅具有Alpha值的空白图像
        alphaBitmap = bitmap.extractAlpha()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        val measureWidthModel = MeasureSpec.getMode(widthMeasureSpec)
        val measureHeightModel = MeasureSpec.getMode(heightMeasureSpec)
        val width = bitmap.width
        val height = bitmap.height
        /***
         * wrap_content --->MeasureSpec.AT_MOST
         * match_parent ---> MeasureSpec.EXACTLY
         * 具体值 -> MeasureSpec.EXACTLY
         */
        setMeasuredDimension(if(measureWidthModel== MeasureSpec.EXACTLY)measureWidth else width,if(measureHeightModel==MeasureSpec.EXACTLY)measureHeight else height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = shadowColor
        paint.maskFilter = BlurMaskFilter(shadowRedius, BlurMaskFilter.Blur.NORMAL)
        canvas.drawBitmap(alphaBitmap, null, Rect(90 + shadowX, 90 + shadowY, 300, 300), paint)

        paint.maskFilter = null
        canvas.drawBitmap(bitmap, null, Rect(90, 90, 290, 290), paint)

    }
}