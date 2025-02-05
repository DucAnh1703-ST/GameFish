package com.example.gamefish.viewmodel

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamefish.viewmodel.dt_model.Fish
import com.example.gamefish.viewmodel.dt_model.Shark
import com.example.gamefish.viewmodel.dt_model.SwordFish
import com.example.gamefish.viewmodel.dt_model.Tuna
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FishTankViewModel : ViewModel() {
    private val _fishes = MutableLiveData<List<Fish>>()
    val fishes: LiveData<List<Fish>> get() = _fishes

    private var isRunning = false
    private var job: Job? = null

    init {
        _fishes.value = emptyList()  // Khởi tạo danh sách cá rỗng
    }

    fun createRandomFish(left: Float, top: Float, right: Float, bottom: Float) {
        val randomX = (left + (Math.random() * (right - left))).toFloat()
        val randomY = (top + (Math.random() * (bottom - top))).toFloat()
        val randomSpeed = (2f + Math.random() * 7f).toFloat()
        val randomSize = (20f + Math.random() * 30f).toFloat()

        val fishType = (Math.random() * 3).toInt()
        val fish = when (fishType) {
            0 -> Shark("Shark", Color.RED, randomX, randomY, randomSize, randomSpeed)
            1 -> Tuna("Tuna", Color.BLUE, randomX, randomY, randomSize, randomSpeed)
            else -> SwordFish("SwordFish", Color.GREEN, randomX, randomY, randomSize, randomSpeed)
        }

        val currentList = _fishes.value.orEmpty()
        _fishes.value = currentList + fish
        startFishMovement(fish, left, top, right, bottom)
    }

    fun startFishMovement(fish: Fish, left: Float, top: Float, right: Float, bottom: Float) {
        if (isRunning) return

        isRunning = true
        job = CoroutineScope(Dispatchers.Default).launch {
            try {
                while (isRunning) {
                    fish.update(left, top, right, bottom)
                    fish.checkCollision(_fishes.value.orEmpty()) // Kiểm tra va chạm
                    delay(10)  // Cập nhật mỗi 10ms (60 FPS)
                }
            } catch (e: Exception) {
                Log.e("FishTankViewModel", "Error in movement coroutine: ${e.message}")
            }
        }
    }

    fun stopGame() {
        isRunning = false
        job?.cancel()
    }
}