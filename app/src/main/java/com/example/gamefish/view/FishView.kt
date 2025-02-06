package com.example.gamefish.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.viewmodel.dt_model.Fish

abstract class FishView(val fish: Fish) {

    // Phương thức vẽ chung cho tất cả các loài cá
    protected fun drawBody(canvas: Canvas, paint: Paint) {
        // Vẽ cá (hình tròn hoặc các dạng khác tùy vào loài cá)
        paint.color = fish.color
        paint.style = Paint.Style.FILL
        canvas.drawCircle(fish.x, fish.y, fish.size, paint)

        // Vẽ tên của cá dưới con cá
        paint.color = Color.BLACK // Màu chữ
        paint.textSize = 40f // Kích thước chữ
        paint.textAlign = Paint.Align.CENTER // Căn giữa chữ
        canvas.drawText(fish.name, fish.x, fish.y + fish.size + 35f, paint)
    }

    // Phương thức vẽ riêng cho mỗi loài cá (các lớp con phải override phương thức này)
    abstract fun draw(canvas: Canvas, paint: Paint)

}