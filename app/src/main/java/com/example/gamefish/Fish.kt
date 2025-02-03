package com.example.gamefish

import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class Fish(
    var name: String,
    var color: Int,
    var x: Float,
    var y: Float,
    var size: Float,
    var speed: Float,
//    val howTodo: ((x:Int, y:Int) -> String)?, // check list been fish tank de tim ra cac doi tuong trung x va y -> xu ly
//    val check: ((fish: Fish) -> Unit)? // xu ly xem co muc dc con o phia truoc k
)
{
//    open var speed: Float = 0.0f
//    var size: Float = 20f

    private var directionX = (Math.random() * 2 - 1).toFloat()  // Hướng ngẫu nhiên trên trục X (-1 hoặc 1)
    private var directionY = (Math.random() * 2 - 1).toFloat()  // Hướng ngẫu nhiên trên trục Y (-1 hoặc 1)

    private var job: Job? = null
    private var isRunning = false

    // Kiểm tra va chạm với các cá khác
    fun checkCollision(fishes: List<Fish>) {
        for (otherFish in fishes) {
            // Tránh kiểm tra va chạm với chính con cá mình
            if (this == otherFish) continue

            // Tính khoảng cách giữa hai con cá
            val distance = Math.sqrt(
                ((x - otherFish.x) * (x - otherFish.x) + (y - otherFish.y) * (y - otherFish.y)).toDouble()
            ).toFloat()

            // Nếu khoảng cách nhỏ hơn tổng bán kính, có thể xảy ra va chạm
            if (distance < (size + otherFish.size)) {
                handleCollision(otherFish)
            }
        }
    }

    // Xử lý va chạm giữa hai con cá
    private fun handleCollision(otherFish: Fish) {
        if (this.size > otherFish.size) {
            this.size += otherFish.size  // Cá lớn hơn ăn cá nhỏ hơn, tăng kích thước
            otherFish.size = 0f  // Cá nhỏ hơn sẽ bị xóa (kích thước thành 0)
        } else {
            otherFish.size += this.size  // Cá lớn hơn ăn cá nhỏ hơn, tăng kích thước
            this.size = 0f  // Cá nhỏ hơn sẽ bị xóa (kích thước thành 0)
        }
    }

    // Bắt đầu vòng lặp di chuyển của con cá
    fun startFishMovement(left: Float, top: Float, right: Float, bottom: Float, fishes: List<Fish>) {
        if (isRunning) return // Nếu cá đã đang chạy, không tạo thêm coroutine

        isRunning = true
        job = CoroutineScope(Dispatchers.Default + SupervisorJob()).launch {
            try {
                while (isRunning) {
                    // Cập nhật vị trí và vẽ cá
                    update(left, top, right, bottom)
                    checkCollision(fishes)  // Kiểm tra va chạm với các cá khác
                    delay(10) // 60 FPS
                }
            } catch (e: Exception) {
                Log.e("Fish", "Error in fish movement coroutine: ${e.message}")
            }
        }
    }

    // Vẽ con cá
//    fun draw(canvas: Canvas, paint: Paint) {
//        paint.color = color
//        paint.style = Paint.Style.FILL
//
//        canvas.drawCircle(x, y, size, paint)// paint fish
//    }

    open fun draw(canvas: Canvas, paint: Paint){

    }

    // Cập nhật vị trí con cá
    fun update(left: Float, top: Float, right: Float, bottom: Float) {
        // Di chuyển cá
        x += directionX * speed
        y += directionY * speed

        // Tạo sự thay đổi ngẫu nhiên trong hướng di chuyển
        directionX += (Math.random() * 0.2 - 0.1).toFloat()  // Thay đổi nhẹ hướng X
        directionY += (Math.random() * 0.2 - 0.1).toFloat()  // Thay đổi nhẹ hướng Y

        // Đảm bảo rằng hướng di chuyển vẫn giữ giá trị hợp lý (không quá lớn)
        if (directionX > 1) directionX = 1f
        if (directionX < -1) directionX = -1f
        if (directionY > 1) directionY = 1f
        if (directionY < -1) directionY = -1f

        // Kiểm tra xem con cá có chạm vào cạnh bể không và điều chỉnh nếu cần thiết
        if (x - size < left) {
            x = left + size // Điều chỉnh lại vị trí khi chạm cạnh trái
            directionX *= -1 // Đổi hướng ngang khi chạm vào cạnh trái
        }
        if (x + size > right) {
            x = right - size // Điều chỉnh lại vị trí khi chạm cạnh phải
            directionX *= -1 // Đổi hướng ngang khi chạm vào cạnh phải
        }
        if (y - size < top) {
            y = top + size // Điều chỉnh lại vị trí khi chạm cạnh trên
            directionY *= -1 // Đổi hướng dọc khi chạm vào cạnh trên
        }
        if (y + size > bottom) {
            y = bottom - size // Điều chỉnh lại vị trí khi chạm cạnh dưới
            directionY *= -1 // Đổi hướng dọc khi chạm vào cạnh dưới
        }
    }
}
