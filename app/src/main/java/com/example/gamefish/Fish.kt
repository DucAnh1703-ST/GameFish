package com.example.gamefish

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Fish(
    private var x: Float,
    private var y: Float,
    private var speed: Float)
{

    private var directionX = (Math.random() * 2 - 1).toFloat()  // Hướng ngẫu nhiên trên trục X (-1 hoặc 1)
    private var directionY = (Math.random() * 2 - 1).toFloat()  // Hướng ngẫu nhiên trên trục Y (-1 hoặc 1)

    private val radius = 20f

    // Vẽ con cá
    fun draw(canvas: Canvas, paint: Paint) {
        paint.color = Color.YELLOW
        paint.style = Paint.Style.FILL

        canvas.drawCircle(x, y, radius, paint)// paint fish
    }

    // Cập nhật vị trí con cá
    fun update(left: Float, top: Float, right: Float, bottom: Float) {
        // Di chuyển cá
        x += directionX * speed
        y += directionY * speed

        // Kiểm tra xem con cá có chạm vào cạnh bể không và điều chỉnh nếu cần thiết
        if (x - radius < left) {
            x = left + radius // Điều chỉnh lại vị trí khi chạm cạnh trái
            directionX *= -1 // Đổi hướng ngang khi chạm vào cạnh trái
        }
        if (x + radius > right) {
            x = right - radius // Điều chỉnh lại vị trí khi chạm cạnh phải
            directionX *= -1 // Đổi hướng ngang khi chạm vào cạnh phải
        }
        if (y - radius < top) {
            y = top + radius // Điều chỉnh lại vị trí khi chạm cạnh trên
            directionY *= -1 // Đổi hướng dọc khi chạm vào cạnh trên
        }
        if (y + radius > bottom) {
            y = bottom - radius // Điều chỉnh lại vị trí khi chạm cạnh dưới
            directionY *= -1 // Đổi hướng dọc khi chạm vào cạnh dưới
        }
    }
}
