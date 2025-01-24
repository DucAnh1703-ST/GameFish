package com.example.gamefish

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import android.view.SurfaceView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class GameView(context: Context) : SurfaceView(context){
    private val fishes = mutableListOf<Fish>()  // Danh sách các con cá

    private val paint = Paint()
    private var job: Job? = null  // Job để quản lý coroutine
    private var isRunning = false

    private val backgroundBitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.background)  // Khai báo và khởi tạo hình nền

    private val padding = 60f  // Khoảng cách từ biên tới bể cá
    private var left = 0f
    private var top = 0f
    private var right = 0f
    private var bottom = 0f

    // Hàm tạo các con cá ngẫu nhiên
    fun createRandomFish() {
        val randomX = (left + (Math.random() * (right - left))).toFloat()
        val randomY = (top + (Math.random() * (bottom - top))).toFloat()
        val randomSpeed = (2f + Math.random() * 7f).toFloat()  // Tốc độ ngẫu nhiên từ 5 đến 15
        val randomSize = (20f + Math.random() * 30f).toFloat()  // Kích thước ngẫu nhiên từ 20f đến 50f
        val fish1 = Shark(
            "abc",
            Color.RED,
            randomX,
            randomY,
            randomSize,
            randomSpeed,
            { randomX, randomY -> "CouldMoveForward" },
            { fish -> println("Checking fish...") }
        )

        val fish2 = Tuna(
            "abc",
            Color.GREEN,
            randomX,
            randomY,
            randomSize,
            randomSpeed,
            { randomX, randomY -> "CouldMoveForward" },
            { fish -> println("Checking fish...") }
        )
        fish1.startFishMovement(left, top, right, bottom) // Bắt đầu vòng lặp di chuyển của cá
        fish2.startFishMovement(left, top, right, bottom) // Bắt đầu vòng lặp di chuyển của cá
        fishes.add(fish1)
        fishes.add(fish2)
    }

    // Vẽ bể cá cố định
    private fun drawFishTank(canvas: Canvas) {
        left = padding
        top = padding
        right = width.toFloat() - padding
        bottom = height.toFloat() - padding

        // Vẽ hình nền chiếm toàn bộ khu vực bể cá
        canvas.drawBitmap(backgroundBitmap, null,
            RectF(left, top, right, bottom), paint)

        // Vẽ khung bể cá (hình chữ nhật)
        paint.color = Color.BLACK  // Màu của khung
        paint.style = Paint.Style.STROKE  // Vẽ đường viền
        paint.strokeWidth = 5f  // Độ dày của đường viền
        canvas.drawRect(left, top, right, bottom, paint)  // Vẽ khung bể cá
    }

    // Vòng lặp trò chơi (sử dụng coroutine)
    @OptIn(DelicateCoroutinesApi::class)
    private fun gameLoop() {
        GlobalScope.launch(Dispatchers.Default) {
            try {
                while (true) {
                    if (holder.surface.isValid) {
                        val canvas = holder.lockCanvas()
                        canvas.drawColor(Color.WHITE)  // Xóa màn hình trước khi vẽ lại
                        drawFishTank(canvas)

                        // Vẽ và cập nhật tất cả các con cá
                        for (fish in fishes) {
                            fish.draw(canvas, paint)
                        }

                        holder.unlockCanvasAndPost(canvas)
                    }
                    delay(10) // Khoảng thời gian giữa các frame (60 FPS)
                }
            } catch (e: Exception) {
                Log.e("GameView", "Error in game loop: ${e.message}")
            }
        }
    }

    fun startGame() {
        createRandomFish()  // Tạo con cá ngẫu nhiên khi bắt đầu trò chơi
        gameLoop()  // Khởi chạy vòng lặp trò chơi
    }
}
