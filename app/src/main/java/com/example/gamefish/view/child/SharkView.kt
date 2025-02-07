package com.example.gamefish.view.child

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.view.FishView
import com.example.gamefish.viewmodel.dt_model.Shark

class SharkView(fish: Shark) : FishView(fish) {

    // Override phương thức draw() để vẽ cá mập
    override fun draw(canvas: Canvas, paint: Paint) {
        drawBody(canvas, paint) // Vẽ phần chung của cá

        // Vẽ vây cá mập (vây lưng)
        drawDorsalFin(canvas, paint)

        // Vẽ hàm cá mập
        paint.color = Color.BLACK
        canvas.drawLine(fish.x - fish.size / 3, fish.y + fish.size / 2, fish.x + fish.size / 3, fish.y + fish.size / 2, paint) // Hàm dưới
    }

    // Vẽ vây lưng cá mập
    private fun drawDorsalFin(canvas: Canvas, paint: Paint) {
        paint.color = Color.GRAY  // Màu vây cá mập
        val path = android.graphics.Path()

        // Tạo hình vây lưng dạng tam giác
        val topX = fish.x  // Tọa độ X của đỉnh vây (trên đỉnh cá mập)

        // Dịch vây lên gần đỉnh hình tròn của thân cá
        val topY = fish.y - fish.size / 1.2f  // Dịch lên cao hơn, gần đỉnh thân cá

        // Xác định 2 điểm đáy của vây
        val leftX = fish.x - fish.size / 2f  // Điểm đáy bên trái (mở rộng ra)
        val rightX = fish.x + fish.size / 2f  // Điểm đáy bên phải (mở rộng ra)
        val bottomY = fish.y - fish.size / 3f  // Điểm đáy của vây (xuống thấp hơn một chút)

        // Vẽ tam giác làm vây
        path.moveTo(topX, topY)  // Di chuyển đến đỉnh vây
        path.lineTo(leftX, bottomY)  // Vẽ đường từ đỉnh xuống đáy bên trái
        path.lineTo(rightX, bottomY)  // Vẽ đường từ đáy bên trái sang đáy bên phải
        path.close()  // Đóng đường vẽ

        // Vẽ hình vây lên canvas
        canvas.drawPath(path, paint)
    }
}

