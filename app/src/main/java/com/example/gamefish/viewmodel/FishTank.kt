package com.example.gamefish.viewmodel

import android.graphics.Color
import com.example.gamefish.viewmodel.dt_model.Fish
import com.example.gamefish.viewmodel.dt_model.Shark
import com.example.gamefish.viewmodel.dt_model.SwordFish
import com.example.gamefish.viewmodel.dt_model.Tuna

class FishTank(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float
) {
    private val fishes = mutableListOf<Fish>()

    // Thêm cá vào bể
    fun addFish(fish: Fish) {
        fishes.add(fish)
    }

    // Tạo cá ngẫu nhiên
    fun createRandomFish() {
        val randomX = (left + (Math.random() * (right - left))).toFloat()
        val randomY = (top + (Math.random() * (bottom - top))).toFloat()
        val randomSpeed = (2f + Math.random() * 7f).toFloat()  // Tốc độ ngẫu nhiên từ 2 đến 9
        val randomSize = (20f + Math.random() * 30f).toFloat()  // Kích thước ngẫu nhiên từ 20f đến 50f

        // Chọn một loại cá ngẫu nhiên
        val fishType = (Math.random() * 3).toInt()

        val fish = when (fishType) {
            0 -> Shark("Shark", Color.RED, randomX, randomY, randomSize, randomSpeed)
            1 -> Tuna("Tuna", Color.BLUE, randomX, randomY, randomSize, randomSpeed)
            else -> SwordFish("SwordFish", Color.GREEN, randomX, randomY, randomSize, randomSpeed)
        }

        fish.startFishMovement(left, top, right, bottom, fishes) // Bắt đầu di chuyển
        fishes.add(fish)
    }

    // Cập nhật và di chuyển tất cả cá trong bể
    fun updateFishTank() {
        // Kiểm tra va chạm và di chuyển cá
        val iterator = fishes.iterator()
        while (iterator.hasNext()) {
            val fish = iterator.next()
            fish.checkCollision(fishes)

            // Nếu cá có kích thước 0, loại bỏ khỏi danh sách
            if (fish.size <= 0) {
                iterator.remove()
            }
        }
    }

    // Lấy danh sách các con cá
    fun getFishes(): List<Fish> {
        return fishes
    }
}