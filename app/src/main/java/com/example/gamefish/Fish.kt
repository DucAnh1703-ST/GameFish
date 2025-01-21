package com.example.gamefish

import android.graphics.Canvas
import android.graphics.Paint

class Fish(private var x: Float, private var y: Float, private var speed: Float) {

    private var directionX = 1 // 1 nghĩa là di chuyển phải, -1 nghĩa là di chuyển trái
    private var directionY = 1 // 1 nghĩa là di chuyển xuống, -1 nghĩa là di chuyển lên

    // Vẽ con cá
    fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawCircle(x, y, 20f, paint)
    }

    // Cập nhật vị trí con cá
    fun update(width: Int, height: Int) {
        // Di chuyển cá
        x += directionX * speed
        y += directionY * speed

        // Kiểm tra xem con cá có chạm vào cạnh bể không
        if (x <= 0 || x >= width.toFloat()) {
            directionX *= -1 // Đổi hướng ngang khi chạm vào cạnh trái/phải
        }
        if (y <= 0 || y >= height.toFloat()) {
            directionY *= -1 // Đổi hướng dọc khi chạm vào cạnh trên/dưới
        }
    }

    // Cập nhật tốc độ và hướng ngẫu nhiên
    fun randomizeDirection() {
        val randomDirectionX = (Math.random() * 2 - 1).toInt()  // -1 hoặc 1
        val randomDirectionY = (Math.random() * 2 - 1).toInt()  // -1 hoặc 1
        directionX = randomDirectionX
        directionY = randomDirectionY
    }
}
