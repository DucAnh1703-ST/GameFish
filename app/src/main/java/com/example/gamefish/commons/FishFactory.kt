package com.example.gamefish.commons

import android.graphics.Color
import com.example.gamefish.viewmodel.dt_model.Fish
import com.example.gamefish.viewmodel.dt_model.Shark
import com.example.gamefish.viewmodel.dt_model.SwordFish
import com.example.gamefish.viewmodel.dt_model.Tuna

class FishFactory {

    // Hàm tạo cá ngẫu nhiên
    fun createRandomFish(left: Float, top: Float, right: Float, bottom: Float): Fish {
        val randomX = (left + (Math.random() * (right - left))).toFloat()
        val randomY = (top + (Math.random() * (bottom - top))).toFloat()
        val randomSpeed = (2f + Math.random() * 4f).toFloat()  // Tốc độ ngẫu nhiên từ 2 đến 6
        val randomSize = (20f + Math.random() * 30f).toFloat()  // Kích thước ngẫu nhiên từ 20f đến 50f

        // Chọn một loại cá ngẫu nhiên
        val fishType = (Math.random() * 3).toInt()

        return when (fishType) {
            0 -> Shark("Shark", Color.RED, randomX, randomY, randomSize, randomSpeed)
            1 -> Tuna("Tuna", Color.YELLOW, randomX, randomY, randomSize, randomSpeed)
            else -> SwordFish("SwordFish", Color.GREEN, randomX, randomY, randomSize, randomSpeed)
        }
    }
}