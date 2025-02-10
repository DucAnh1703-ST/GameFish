package com.example.gamefish.view.child

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.view.FishView
import com.example.gamefish.viewmodel.dt_model.Turtle

class TurtleView(fish: Turtle) : FishView(fish) {

    // Phương thức vẽ riêng cho loài rùa
    override fun draw(canvas: Canvas, paint: Paint) {
        // Vẽ thân rùa (hình tròn hoặc ảnh)
        drawBody(canvas, paint)

        // Vẽ tay chèo
        drawPaddles(canvas, paint)
    }

    // Vẽ 4 tay chèo
    private fun drawPaddles(canvas: Canvas, paint: Paint) {
        val paddleSize = fish.size * 0.5f

        // Màu tay chèo
        paint.color = Color.GRAY
        paint.style = Paint.Style.FILL

        // Tay chèo trên trái
        canvas.drawOval(
            fish.x - fish.size - paddleSize, fish.y - paddleSize,
            fish.x - fish.size, fish.y + paddleSize,
            paint
        )

        // Tay chèo trên phải
        canvas.drawOval(
            fish.x + fish.size, fish.y - paddleSize,
            fish.x + fish.size + paddleSize, fish.y + paddleSize,
            paint
        )

        // Tay chèo dưới trái
        canvas.drawOval(
            fish.x - fish.size - paddleSize, fish.y + fish.size - paddleSize,
            fish.x - fish.size, fish.y + fish.size + paddleSize,
            paint
        )

        // Tay chèo dưới phải
        canvas.drawOval(
            fish.x + fish.size, fish.y + fish.size - paddleSize,
            fish.x + fish.size + paddleSize, fish.y + fish.size + paddleSize,
            paint
        )
    }
}
