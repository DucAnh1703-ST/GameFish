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

class GameView(context: Context) : SurfaceView(context){
    private val fishes = mutableListOf<Fish>()  // Danh sách các con cá

    private val fish = Fish(500f, 500f, 15f)
    private val paint = Paint()
    private var job: Job? = null  // Job để quản lý coroutine
    private var isRunning = false

    private val backgroundBitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.background)  // Khai báo và khởi tạo hình nền

    private val padding = 60f  // Khoảng cách từ biên tới bể cá
    private var left = padding
    private var top = 400f
    private var right = width.toFloat() - padding
    private var bottom = height.toFloat() - padding

    // Hàm tạo các con cá ngẫu nhiên
    fun createRandomFish() {
        val randomX = (left + (Math.random() * (right - left))).toFloat()
        val randomY = (top + (Math.random() * (bottom - top))).toFloat()
        val randomSpeed = (5f + Math.random() * 10f).toFloat()  // Tốc độ ngẫu nhiên từ 5 đến 15
        val fish = Fish(randomX, randomY, randomSpeed)
        fishes.add(fish)
    }

    // Vẽ bể cá cố định
    private fun drawFishTank(canvas: Canvas) {
        left = padding
        top = 400f
        right = width.toFloat() - padding
        bottom = height.toFloat() - padding

        // Vẽ hình nền
        val backgroundLeft = left

        // Vẽ hình nền chiếm toàn bộ khu vực bể cá
        canvas.drawBitmap(backgroundBitmap, null,
            RectF(backgroundLeft, top, right, bottom), paint)

        // Vẽ khung bể cá (hình chữ nhật)
        paint.color = Color.BLACK  // Màu của khung
        paint.style = Paint.Style.STROKE  // Vẽ đường viền
        paint.strokeWidth = 5f  // Độ dày của đường viền
        canvas.drawRect(left, top, right, bottom, paint)  // Vẽ khung bể cá
    }

    // Sử dụng Coroutine để chạy vòng lặp trò chơi
    @OptIn(DelicateCoroutinesApi::class)
    private fun gameLoop() {
        GlobalScope.launch(Dispatchers.Default) {
            try {
                while (isRunning) {
                    if (holder.surface.isValid) {
                        val canvas = holder.lockCanvas()
                        canvas.drawColor(Color.WHITE)  // Xóa màn hình trước khi vẽ lại

                        // Vẽ bể cá cố định
                        drawFishTank(canvas)

                        // Vẽ và cập nhật tất cả các con cá
                        for (fish in fishes) {
                            fish.update(left, top, right, bottom)
                            fish.draw(canvas, paint)
                        }

                        holder.unlockCanvasAndPost(canvas)
                    }
                    delay(10)  // Khoảng thời gian giữa các frame (60 FPS)
                }
            } catch (e: Exception) {
                Log.e("GameView", "Error in game loop: ${e.message}")
            }
        }
    }

    fun startGame() {
        isRunning = true
        if (job == null || job?.isCompleted == true) {
            job = CoroutineScope(Dispatchers.Default).launch {
                createRandomFish()  // Tạo 3 con cá ngẫu nhiên khi bắt đầu trò chơi
                gameLoop()  // Khởi chạy coroutine cho vòng lặp trò chơi
            }
        }
    }

//    fun stopGame() {
//        isRunning = false
//        job?.cancel()  // Dừng coroutine khi game dừng
//        Log.d("Running123", "isRunning = $isRunning")
//    }
//
//    // Lắng nghe sự kiện touch nếu bạn muốn tương tác
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        if (event.action == MotionEvent.ACTION_DOWN) {
//            // Ví dụ: random hướng di chuyển mới khi người chơi chạm vào màn hình
//            //fish.randomizeDirection()
//            Log.d("Running","isRunning = $isRunning")
//        }
//        return true
//    }
}
