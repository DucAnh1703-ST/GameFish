package com.example.gamefish.viewmodel

import android.graphics.Color
import com.example.gamefish.viewmodel.dt_model.Fish
import com.example.gamefish.viewmodel.dt_model.Shark
import com.example.gamefish.viewmodel.dt_model.SwordFish
import com.example.gamefish.viewmodel.dt_model.Tuna

class FishTank {
    private val fishes = mutableListOf<Fish>()  // Danh sách các con cá

    var left = 0f
    var top = 0f
    var right = 0f
    var bottom = 0f

    // Hàm tạo các con cá ngẫu nhiên
    fun createRandomFish() {
        val randomX = (left + (Math.random() * (right - left))).toFloat()
        val randomY = (top + (Math.random() * (bottom - top))).toFloat()
        val randomSpeed = (2f + Math.random() * 4f).toFloat()  // Tốc độ ngẫu nhiên từ 5 đến 15
        val randomSize = (20f + Math.random() * 30f).toFloat()  // Kích thước ngẫu nhiên từ 20f đến 50f

        // Chọn một loại cá ngẫu nhiên giữa 0, 1 và 2
        val fishType = (Math.random() * 3).toInt()

        val fish = when (fishType) {
            0 -> Shark("Shark", Color.RED, randomX, randomY, randomSize, randomSpeed)
            1 -> Tuna("Tuna", Color.YELLOW, randomX, randomY, randomSize, randomSpeed)
            else -> SwordFish("SwordFish", Color.GREEN, randomX, randomY, randomSize, randomSpeed)
        }

        fish.startFishMovement(left, top, right, bottom, fishes) // Bắt đầu di chuyển
        fishes.add(fish)
    }

    // Lấy danh sách các con cá
    fun getFishes() = fishes
}
