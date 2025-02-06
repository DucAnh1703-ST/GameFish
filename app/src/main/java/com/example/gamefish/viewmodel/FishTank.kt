package com.example.gamefish.viewmodel

import com.example.gamefish.commons.FishFactory
import com.example.gamefish.viewmodel.dt_model.Fish

class FishTank {
    private val fishes = mutableListOf<Fish>()  // Danh sách các con cá

    var left = 0f
    var top = 0f
    var right = 0f
    var bottom = 0f

    private val fishFactory = FishFactory()  // Khởi tạo FishFactory

    // Hàm tạo các con cá ngẫu nhiên
    fun createRandomFish() {
        val fish = fishFactory.createRandomFish(left, top, right, bottom)  // Sử dụng FishFactory để tạo cá
        fish.startFishMovement(left, top, right, bottom, fishes) // Bắt đầu di chuyển
        fishes.add(fish)
    }

    // Lấy danh sách các con cá
    fun getFishes() = fishes
}
