package com.example.gamefish.viewmodel

import android.util.Log
import com.example.gamefish.viewmodel.dt_model.Fish

class FishTank {
    private val fishes = mutableListOf<Fish>()  // Danh sách các con cá

    var left = 30f
    var top = 30f
    var right = 30f
    var bottom = 30f

    private val fishFactory = FishFactory()  // Khởi tạo FishFactory

    // Hàm tạo các con cá ngẫu nhiên
    fun createRandomFish() {
        val fish = fishFactory.createRandomFish(left, top, right, bottom)  // Sử dụng FishFactory để tạo cá
        fish.startFishMovement(left, top, right, bottom, fishes) // Bắt đầu di chuyển
        fishes.add(fish)
        Log.d("abc","$left,$top,$right,$bottom")
    }

    // Lấy danh sách các con cá
    fun getFishes() = fishes
}
