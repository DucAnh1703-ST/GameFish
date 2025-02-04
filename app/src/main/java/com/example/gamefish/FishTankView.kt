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
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FishTankView(context: Context) : SurfaceView(context){
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

        // Chọn một loại cá ngẫu nhiên giữa 0, 1 và 2
        val fishType = (Math.random() * 3).toInt()

        val fish = when (fishType) {
            0 -> Shark("Shark", Color.RED, randomX, randomY, randomSize, randomSpeed)
            1 -> Tuna("Tuna", Color.BLUE, randomX, randomY, randomSize, randomSpeed)
            else -> SwordFish("SwordFish", Color.GREEN, randomX, randomY, randomSize, randomSpeed)
        }

        fish.startFishMovement(left, top, right, bottom, fishes) // Bắt đầu di chuyển
        fishes.add(fish)
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

//                        // Vẽ và cập nhật tất cả các con cá
//                        for (fish in fishes) {
//                            fish.draw(canvas, paint)
//                        }

                        // Vẽ và cập nhật tất cả các con cá
                        val iterator = fishes.iterator()
                        while (iterator.hasNext()) {
                            val fish = iterator.next()
                            fish.draw(canvas, paint)

                            // Nếu cá có kích thước 0, loại bỏ khỏi danh sách
                            if (fish.size <= 0) {
                                iterator.remove() // Xóa cá đã bị ăn
                            }
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
//        createRandomFish()  // Tạo con cá ngẫu nhiên khi bắt đầu trò chơi
        gameLoop()  // Khởi chạy vòng lặp trò chơi
    }
}
