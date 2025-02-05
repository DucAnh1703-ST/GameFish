package com.example.gamefish.view.child

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.view.FishView
import com.example.gamefish.viewmodel.dt_model.Shark

class SharkView(fish: Shark) : FishView(fish) {

    // Override phương thức draw() để vẽ cá mập
    override fun draw(canvas: Canvas, paint: Paint) {
        // Vẽ cá mập (ví dụ vẽ hình tròn cho cá mập)
        paint.color = fish.color
        paint.style = Paint.Style.FILL
        canvas.drawCircle(fish.x, fish.y, fish.size, paint)

        // Vẽ tên của cá mập dưới con cá
        paint.color = Color.BLACK // Màu chữ
        paint.textSize = 40f // Kích thước chữ
        paint.textAlign = Paint.Align.CENTER // Căn giữa chữ
        canvas.drawText(fish.name, fish.x, fish.y + fish.size + 35f, paint)
    }
}