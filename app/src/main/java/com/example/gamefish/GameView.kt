package com.example.gamefish

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceView

class GameView(context: Context) : SurfaceView(context), Runnable {

    private val fish = Fish(500f, 500f, 5f)
    private val paint = Paint()
    private var thread: Thread? = null
    private var isRunning = false

    init {
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
    }

    override fun run() {
        while (isRunning) {
            if (holder.surface.isValid) {
                val canvas = holder.lockCanvas()
                canvas.drawColor(Color.WHITE)  // Xóa màn hình trước khi vẽ lại
                fish.update(canvas.width, canvas.height)  // Cập nhật vị trí con cá
                fish.draw(canvas, paint)  // Vẽ con cá lên Canvas
                holder.unlockCanvasAndPost(canvas)
                Thread.sleep(16)  // Khoảng thời gian giữa các frame (60 FPS)
            }
        }
    }

    fun startGame() {
        isRunning = true
        thread = Thread(this)
        thread?.start()
    }

    fun stopGame() {
        isRunning = false
        thread?.join()
    }

    // Lắng nghe sự kiện touch nếu bạn muốn tương tác
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            // Ví dụ: random hướng di chuyển mới khi người chơi chạm vào màn hình
            fish.randomizeDirection()
        }
        return true
    }
}
