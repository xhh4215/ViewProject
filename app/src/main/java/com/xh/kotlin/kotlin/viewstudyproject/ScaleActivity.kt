package com.xh.kotlin.kotlin.viewstudyproject

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_scale.*

class ScaleActivity : AppCompatActivity() {
    private lateinit var bitmap: Bitmap
    private lateinit var tempBitmap: Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale)
        //接续资源获取bitmap对象
        bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.smallpz)
        //创建一个和解析出来的bitmap大小相同的bitmap
        tempBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        scaleSeekbar.max = 10
        scaleSeekbar.progress =1
        scaleSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                scaleImageView.setImageBitmap(onHandleBitmap(progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    fun onHandleBitmap(progress: Int): Bitmap {
        val canvas = Canvas(tempBitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        val colorMatrix = ColorMatrix()
        colorMatrix.setScale(1f, progress.toFloat(), 1f, 1f)
        paint.colorFilter = ColorMatrixColorFilter((colorMatrix))
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return tempBitmap
    }
}
