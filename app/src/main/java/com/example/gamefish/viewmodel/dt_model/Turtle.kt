package com.example.gamefish.viewmodel.dt_model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Turtle(
    name: String,
    color: Int,
    x: Float,
    y: Float,
//    size: Float,
//    speed: Float
) : Fish(name,color,x,y){
    init {
        // Random size và speed cho cá mập
        this.size = (10f + Math.random() * 15f).toFloat()  // Random kích thước từ 30f đến 40f
        this.speed = (0.5f + Math.random() * 1f).toFloat()  // Random tốc độ từ 4 đến 7
    }

    // Override phương thức xử lý va chạm để rùa chỉ ăn được cua và sứa
    override fun handleCollision(otherFish: Fish) {
        if (otherFish is Crab || otherFish is Jellyfish) { // Chỉ ăn cua và sứa
            if (this.size > otherFish.size) {
                this.size += otherFish.size / 3 // Rùa ăn và tăng kích thước
                otherFish.size = 0f  // Cua/Sứa bị ăn mất
            }
        }
    }
}