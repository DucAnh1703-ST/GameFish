package com.example.gamefish.view.child

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.view.FishView
import com.example.gamefish.viewmodel.dt_model.Fish

class SwordFishView(fish: Fish) : FishView(fish) {

    // Override phương thức draw() để vẽ cá kiếm
    override fun draw(canvas: Canvas, paint: Paint) {
        drawBody(canvas, paint) // Vẽ phần chung của cá

        // Vẽ "kiếm" dài của cá kiếm
        paint.color = Color.DKGRAY
        canvas.drawLine(fish.x, fish.y - fish.size, fish.x, fish.y - fish.size - fish.size, paint) // Mũi kiếm dài
    }
}