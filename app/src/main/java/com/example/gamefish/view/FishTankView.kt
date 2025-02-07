package com.example.gamefish.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import android.view.SurfaceView
import com.example.gamefish.R
import com.example.gamefish.view.child.JellyfishView
import com.example.gamefish.view.child.SharkView
import com.example.gamefish.view.child.CrabView
import com.example.gamefish.viewmodel.FishTank
import com.example.gamefish.viewmodel.dt_model.Fish
import com.example.gamefish.viewmodel.dt_model.Shark
import com.example.gamefish.viewmodel.dt_model.Jellyfish
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FishTankView(context: Context) : SurfaceView(context) {

    private val fishTank = FishTank()  // Lớp quản lý cá
    private val fishViews = mutableListOf<FishView>()  // Danh sách các FishView để vẽ cá

    private val paint = Paint()

    private val backgroundBitmap: Bitmap = BitmapFactory.decodeResource(context.resources,
        R.drawable.background
    )  // Khai báo và khởi tạo hình nền

    private val padding = 30f  // Khoảng cách từ biên tới bể cá

    // Hàm tạo các con cá ngẫu nhiên
    fun createRandomFish() {
        fishTank.createRandomFish()  // Tạo cá mới trong FishTank

        val fishView = when (val fish = fishTank.getFishes().last()) {  // Lấy cá mới được tạo
            is Shark -> SharkView(fish)
            is Jellyfish -> JellyfishView(fish)  // TunaView là một lớp tương tự như SharkView
            else -> CrabView(fish) // SwordFishView tương tự
        }

        fishViews.add(fishView)
    }

    // Cập nhật danh sách FishView từ LiveData (UI)
    fun updateFishViews(fishes: List<Fish>) {

        val fishViews = fishes.map { fish ->
            when (fish) {
                is Shark -> SharkView(fish)
                is Jellyfish -> JellyfishView(fish)
                else -> CrabView(fish)
            }
        }

        // Cập nhật danh sách FishViews để vẽ
        this.fishViews.clear()
        this.fishViews.addAll(fishViews)
    }

    // Vẽ bể cá cố định
    private fun drawFishTank(canvas: Canvas) {
        fishTank.left = padding
        fishTank.top = padding
        fishTank.right = width.toFloat() - padding
        fishTank.bottom = height.toFloat() - padding

        // Vẽ hình nền chiếm toàn bộ khu vực bể cá
        canvas.drawBitmap(backgroundBitmap, null,
            RectF(fishTank.left, fishTank.top, fishTank.right, fishTank.bottom), paint)

        // Vẽ khung bể cá (hình chữ nhật)
        paint.color = Color.BLACK  // Màu của khung
        paint.style = Paint.Style.STROKE  // Vẽ đường viền
        paint.strokeWidth = 5f  // Độ dày của đường viền
        canvas.drawRect(fishTank.left, fishTank.top, fishTank.right, fishTank.bottom, paint)  // Vẽ khung bể cá
    }

    // Vẽ các con cá
    private fun drawFishes(canvas: Canvas) {
        val iterator = fishViews.iterator()
        while (iterator.hasNext()) {
            val fishView = iterator.next()
            fishView.draw(canvas, paint)

            // Nếu cá có kích thước 0, loại bỏ khỏi danh sách
            if (fishView.fish.size <= 0) {
                iterator.remove()  // Xóa cá đã bị ăn
            }
        }
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
                        drawFishes(canvas)

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
        gameLoop()  // Khởi chạy vòng lặp trò chơi
    }
}