package com.example.gamefish.viewmodel

import android.graphics.Color
import com.example.gamefish.viewmodel.dt_model.Fish
import com.example.gamefish.viewmodel.dt_model.Shark
import com.example.gamefish.viewmodel.dt_model.Crab
import com.example.gamefish.viewmodel.dt_model.Jellyfish

class FishFactory {

    // Hàm tạo cá ngẫu nhiên
    fun createRandomFish(left: Float, top: Float, right: Float, bottom: Float): Fish {
        val randomX = (left + (Math.random() * (right - left))).toFloat()
        val randomY = (top + (Math.random() * (bottom - top))).toFloat()

        // Chọn một loại cá ngẫu nhiên
        val fishType = (Math.random() * 3).toInt()

        return when (fishType) {
            0 -> Shark("Shark", Color.RED, randomX, randomY)
            1 -> Jellyfish("Jellyfish", Color.YELLOW, randomX, randomY)
            else -> Crab("Crab", Color.GREEN, randomX, randomY)
        }
    }
}