package com.example.gamefish.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamefish.viewmodel.dt_model.Fish

class FishTankViewModel : ViewModel() {
    private val fishTank = FishTank()  // Quản lý dữ liệu cá

    private val _fishes = MutableLiveData<List<Fish>>()  // LiveData chứa danh sách cá
    val fishes: LiveData<List<Fish>>
        get() = _fishes

    init {
        // Khởi tạo danh sách cá ban đầu (nếu cần)
        _fishes.value = fishTank.getFishes()
    }

    // Hàm tạo cá ngẫu nhiên qua FishTank và cập nhật LiveData
    fun createRandomFish() {
        fishTank.createRandomFish()          // Tạo cá mới trong FishTank
        _fishes.value = fishTank.getFishes()   // Cập nhật danh sách cá
    }
}

