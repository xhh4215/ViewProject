package com.xh.kotlin.kotlin.viewstudyproject

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xh.kotlin.kotlin.interfaces.OnItemClickListener
import com.xh.kotlin.kotlin.viewmeasure.WaterfallLayout
import kotlinx.android.synthetic.main.measure_activity_three.*

class MeasureActivity : AppCompatActivity() {
    private val IMG_COUNT = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.measure_activity_three)
        add_btn.setOnClickListener {
            addView(waterfalllayout)

        }

    }

    private fun addView(waterfalllayout: WaterfallLayout) {
        var random = (1..5).random()
        Log.d("random", "$random")
        val imageView = ImageView(this)

        when {
            random % IMG_COUNT == 0 -> {
                imageView.setImageResource(R.drawable.pic_1)
            }
            random % IMG_COUNT == 1 -> {
                imageView.setImageResource(R.drawable.pic_2)
            }
            random % IMG_COUNT == 2 -> {
                imageView.setImageResource(R.drawable.pic_3)
            }
            random % IMG_COUNT == 3 -> {
                imageView.setImageResource(R.drawable.pic_4)
            }
            random % IMG_COUNT == 4 -> {
                imageView.setImageResource(R.drawable.pic_5)
            }
        }

        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        waterfalllayout.addView(
            imageView,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        waterfalllayout.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(view: View, index: Int) {
                Toast.makeText(this@MeasureActivity, "item=$index", Toast.LENGTH_SHORT).show()
            }

        })
        waterfalllayout.setOnTouchListener { v, event ->
            Toast.makeText(this, "瀑布布局被Touch", Toast.LENGTH_SHORT).show()
            false
        }
    }

    /****
     * 返回值表示事件是否被消费了
     * return false :表示没有人要消费这个事件，需要在当前的View中对事件进行具体的消费 具体的消费逻辑在
     * 当前的Activity的 onTouchEvent()中处理
     *
     *
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Toast.makeText(this, "this is onTouchEvent", Toast.LENGTH_SHORT).show()
        return true
    }
}