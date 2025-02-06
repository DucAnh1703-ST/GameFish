package com.example.gamefish.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamefish.viewmodel.dt_model.Fish

class FishTankViewModel : ViewModel() {
    // LiveData chứa danh sách cá
    private val _fishes = MutableLiveData<List<Fish>>()
    val fishes: LiveData<List<Fish>> get() = _fishes

    // LiveData để cập nhật trạng thái của game
    private val _isGameRunning = MutableLiveData<Boolean>(false)
    val isGameRunning: LiveData<Boolean> get() = _isGameRunning

    private val fishTank = FishTank()

    // Tạo cá ngẫu nhiên và cập nhật danh sách cá
    fun createRandomFish() {
        fishTank.createRandomFish()
        _fishes.value = fishTank.getFishes()  // Cập nhật danh sách cá
    }

    // Bắt đầu game
    fun startGame() {
        _isGameRunning.value = true
    }

    // Dừng game
    fun stopGame() {
        _isGameRunning.value = false
    }

    // Hàm cập nhật các con cá trong vòng lặp game
    fun updateFishTank() {
        _fishes.value = fishTank.getFishes()
    }
}