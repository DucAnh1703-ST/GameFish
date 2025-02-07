package com.example.gamefish.view.child

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.view.FishView
import com.example.gamefish.viewmodel.dt_model.Fish

class CrabView(fish: Fish) : FishView(fish) {

    // Phương thức vẽ riêng cho loài Crab (cua)
    override fun draw(canvas: Canvas, paint: Paint) {
        // Vẽ thân cua (hình tròn hoặc ảnh)
        drawBody(canvas, paint)

        // Vẽ càng cua
        drawClaws(canvas, paint)
    }

    // Vẽ 2 càng cua
    private fun drawClaws(canvas: Canvas, paint: Paint) {
        // Vẽ càng cua bên trái (càng bên trái có thể nghiêng một chút)
        val leftClawX = fish.x - fish.size * 1.2f  // Tính vị trí của càng bên trái (bên ngoài thân cua)
        val leftClawY = fish.y  // Cùng y với thân cua

        // Vẽ càng cua bên phải (càng bên phải có thể nghiêng một chút)
        val rightClawX = fish.x + fish.size * 1.2f  // Tính vị trí của càng bên phải (bên ngoài thân cua)
        val rightClawY = fish.y  // Cùng y với thân cua

        // Đường vẽ càng
        paint.color = Color.RED  // Màu càng cua
        paint.strokeWidth = 8f   // Độ dày của đường vẽ càng

        // Vẽ càng cua bên trái
        canvas.drawLine(fish.x, fish.y, leftClawX, leftClawY, paint)

        // Vẽ càng cua bên phải
        canvas.drawLine(fish.x, fish.y, rightClawX, rightClawY, paint)

        // Vẽ các đầu càng (các hình tròn nhỏ ở đầu càng)
        paint.style = Paint.Style.FILL
        canvas.drawCircle(leftClawX, leftClawY, fish.size / 2, paint)  // Càng trái
        canvas.drawCircle(rightClawX, rightClawY, fish.size / 2, paint) // Càng phải
    }
}