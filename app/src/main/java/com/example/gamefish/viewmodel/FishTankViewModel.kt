package com.example.gamefish.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamefish.viewmodel.dt_model.Fish

class FishTankViewModel : ViewModel() {
    private val _fishes = MutableLiveData<List<Fish>>()  // Lưu danh sách các con cá (dữ liệu)
    val fishes: LiveData<List<Fish>> get() = _fishes

    private val fishTank = FishTank()  // Lớp quản lý cá

    // Hàm tạo cá ngẫu nhiên và cập nhật LiveData
    fun createRandomFish() {
        fishTank.createRandomFish()  // Tạo cá mới trong FishTank
        _fishes.value = fishTank.getFishes()  // Cập nhật LiveData với danh sách cá mới
    }

    // Hàm để lấy tất cả cá
    fun getFishes() {
        _fishes.value = fishTank.getFishes()  // Lấy danh sách cá từ FishTank
    }
}
