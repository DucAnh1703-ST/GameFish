package com.example.gamefish.view.child

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.view.FishView
import com.example.gamefish.viewmodel.dt_model.Shark

class SharkView(fish: Shark) : FishView(fish) {

    // Override phương thức draw() để vẽ cá kiếm
    override fun draw(canvas: Canvas, paint: Paint) {
        drawBody(canvas, paint) // Vẽ phần chung của cá

        // Vẽ vây cá mập
        paint.color = Color.GRAY
        canvas.drawRect(fish.x - fish.size / 2, fish.y - fish.size / 2, fish.x + fish.size / 2, fish.y - fish.size, paint) // Vây trên

        // Vẽ hàm cá mập
        paint.color = Color.BLACK
        canvas.drawLine(fish.x - fish.size / 3, fish.y + fish.size / 2, fish.x + fish.size / 3, fish.y + fish.size / 2, paint) // Hàm dưới
    }
}