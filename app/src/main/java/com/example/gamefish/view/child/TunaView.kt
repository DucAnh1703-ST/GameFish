package com.example.gamefish.view.child

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.view.FishView
import com.example.gamefish.viewmodel.dt_model.Tuna

class TunaView(fish: Tuna) : FishView(fish) {

    override fun draw(canvas: Canvas, paint: Paint) {
        drawBody(canvas, paint)  // Vẽ phần thân cá ngừ

        // Vẽ đuôi cá ngừ với nhiều vây
        paint.color = Color.BLUE

        // Vây đuôi bên trái
        canvas.drawLine(fish.x - fish.size / 2, fish.y + fish.size, fish.x - fish.size * 1.5f, fish.y + fish.size + 20f, paint) // Vây đuôi trái
        canvas.drawLine(fish.x - fish.size / 2, fish.y + fish.size, fish.x - fish.size * 1.7f, fish.y + fish.size + 40f, paint) // Vây đuôi phụ trái

        // Vây đuôi bên phải
        canvas.drawLine(fish.x + fish.size / 2, fish.y + fish.size, fish.x + fish.size * 1.5f, fish.y + fish.size + 20f, paint) // Vây đuôi phải
        canvas.drawLine(fish.x + fish.size / 2, fish.y + fish.size, fish.x + fish.size * 1.7f, fish.y + fish.size + 40f, paint) // Vây đuôi phụ phải
    }
}