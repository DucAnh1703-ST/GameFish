package com.example.gamefish.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.viewmodel.dt_model.Fish

abstract class FishView(val fish: Fish) {

    // Phương thức vẽ chung cho tất cả các loài cá
    protected fun drawBody(canvas: Canvas, paint: Paint) {
        // Vẽ thanh máu phía trên đầu cá
        drawHealthBar(canvas, paint)

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

    private fun drawHealthBar(canvas: Canvas, paint: Paint) {
        // Độ dài cố định của thanh máu (ví dụ: 200 pixel)
        val fixedBarWidth = 150f
        val barHeight = 10f

        // Tọa độ thanh máu:
        // - Nằm phía trên đầu cá, cách đầu cá 20 pixel.
        // - Thanh máu được căn giữa theo trục X của cá.
        val left = fish.x - fixedBarWidth / 2
        val top = fish.y - fish.size - 20f
        val right = left + fixedBarWidth
        val bottom = top + barHeight

        // Vẽ thanh máu màu đỏ
        // Đảm bảo vẽ thanh máu đầy màu đỏ
        paint.style = Paint.Style.FILL  // Đặt kiểu vẽ là FILL (đầy màu)
        paint.color = Color.RED
        canvas.drawRect(left, top, right, bottom, paint)

        // Vẽ giá trị size của cá phía trên thanh máu để người chơi có thể nhìn thấy
        paint.color = Color.WHITE
        paint.isFakeBoldText = false
        paint.textSize = 30f        // Kích thước chữ, bạn có thể điều chỉnh
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("Size: ${"%.2f".format(fish.size)}", fish.x, top - 5f, paint)
    }

    // Phương thức vẽ riêng cho mỗi loài cá (các lớp con phải override phương thức này)
    abstract fun draw(canvas: Canvas, paint: Paint)

}