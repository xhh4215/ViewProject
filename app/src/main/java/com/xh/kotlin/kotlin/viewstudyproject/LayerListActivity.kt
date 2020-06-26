package com.xh.kotlin.kotlin.viewstudyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/****
 *   public void setShadowLayer(float radius, float dx, float dy, int color) 实现阴影
 *   float radius：意思是模糊半径，radius越大越模糊，越小越清晰，但是如果radius设置为0，则阴影消失不见
 *   float dx：阴影的横向偏移距离，正值向右偏移，负值向左偏移
 *   float dy：阴影的纵向偏移距离，正值向下偏移，负值向上偏移
 *   int color：绘制阴影的画笔颜色，即阴影的颜色（对图片阴影无效）
 *
 *   1 . 使用的是高斯模糊算法
 *   2 . 绘制阴影的画笔对图片无效
 */
class LayerListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layer_list)
    }
}
