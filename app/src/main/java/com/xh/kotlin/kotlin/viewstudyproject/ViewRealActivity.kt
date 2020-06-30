package com.xh.kotlin.kotlin.viewstudyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_view_real.*

class ViewRealActivity : AppCompatActivity() {
    var height = 0
    var width = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_real)


        methodOne.setOnClickListener {
            Toast.makeText(
                this,
                "method one    measureHeight:$height, measuredWidth$width",
                Toast.LENGTH_SHORT
            )
                .show()

        }
        methodTwo.setOnClickListener {
            Toast.makeText(
                this,
                "method two    measureHeight:$height, measuredWidth$width",
                Toast.LENGTH_SHORT
            )
                .show()

        }
        methodThree.setOnClickListener {
            Toast.makeText(
                this,
                "method three   measureHeight:$height, measuredWidth$width",
                Toast.LENGTH_SHORT
            )
                .show()

        }

    }


    override fun onStart() {
        super.onStart()
        drawView.post {
            kotlin.run {
                height = drawView.measuredHeight
                width = drawView.measuredWidth
            }
        }
        val observer = drawView.viewTreeObserver
        observer.addOnGlobalLayoutListener {
            ViewTreeObserver.OnGlobalLayoutListener {
                width = drawView.measuredWidth
                height = drawView.measuredHeight
            }
        }

    }

    /***
     * 该方法的含义是View已经测量完毕 该方法会被多次调用，在Activity得到或者是失去
     * 焦点的时候均会被调用
     */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            height = drawView.measuredHeight
            width = drawView.measuredWidth

        }
    }
}