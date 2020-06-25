package com.xh.kotlin.kotlin.viewstudyproject

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_saturation.*

/****
 * 实现对图片的饱和度的修改
 */
class SaturationActivity : AppCompatActivity() {
    lateinit var bitmap: Bitmap
    lateinit var tempBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saturation)
        bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.smallpz)
        tempBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        seekbar.max = 20
        seekbar.progress = 1
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var bitmap = handleColorMatrixBmp(progress)
                imageView.setImageBitmap(bitmap)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

    }

    fun handleColorMatrixBmp(progress: Int): Bitmap {
        val canvas = Canvas(tempBitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        //创建一个ColorMatrix对象
        val mSaturationMatrix = ColorMatrix()
        //设置图片颜色的饱和度
        mSaturationMatrix.setSaturation(progress.toFloat())
        //设置颜色过滤器
        paint.colorFilter = ColorMatrixColorFilter(mSaturationMatrix)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return tempBitmap


    }
}
