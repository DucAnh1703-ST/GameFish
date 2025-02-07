package com.example.gamefish.view.child

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.view.FishView
import com.example.gamefish.viewmodel.dt_model.Jellyfish
import kotlin.math.cos
import kotlin.math.sin

class JellyfishView(fish: Jellyfish) : FishView(fish) {

    // Phương thức vẽ riêng cho loài Jellyfish (sứa)
    override fun draw(canvas: Canvas, paint: Paint) {
        // Vẽ thân sứa (hình tròn hoặc ảnh)
        drawBody(canvas, paint)

        // Vẽ xúc tu ở nửa dưới của sứa
        drawTentacles(canvas, paint)
    }

    // Vẽ xúc tu sứa ở nửa dưới
    private fun drawTentacles(canvas: Canvas, paint: Paint) {
        paint.color = Color.CYAN  // Màu xúc tu (có thể thay đổi)
        paint.strokeWidth = 5f    // Độ dày của xúc tu

        val numTentacles = 16  // Thay đổi số lượng xúc tu ở đây (ví dụ 16 xúc tu)

        // Vẽ xúc tu từ nửa dưới của thân sứa
        for (i in 0 until numTentacles) {  // Tạo 16 xúc tu (hoặc thay đổi giá trị này)
            val angle = Math.PI * 2 / numTentacles * i  // Góc phân bố đều trên vòng tròn
            val startX = fish.x + cos(angle) * fish.size  // Tính tọa độ bắt đầu của xúc tu
            val startY = fish.y + sin(angle) * fish.size  // Tính tọa độ bắt đầu của xúc tu

            // Điều chỉnh xúc tu chỉ vẽ ở nửa dưới (y >= fish.y)
            if (startY > fish.y) {
                // Tính tọa độ kết thúc của xúc tu (xúc tu sẽ kéo dài xuống dưới)
                val endX = startX + cos(angle) * 100  // Độ dài xúc tu
                val endY = startY + sin(angle) * 100  // Độ dài xúc tu

                // Vẽ xúc tu (các đường thẳng)
                canvas.drawLine(startX.toFloat(), startY.toFloat(), endX.toFloat(), endY.toFloat(), paint)
            }
        }
    }
}