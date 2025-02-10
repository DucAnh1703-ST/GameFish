package com.example.gamefish.viewmodel.dt_model

class Shark(
    name: String,
    color: Int,
    x: Float,
    y: Float,
//    size: Float,
//    speed: Float
) : Fish(name,color,x,y){
    init {
        // Random size và speed cho cá mập
        this.size = (30f + Math.random() * 20f).toFloat()  // Random kích thước từ 30f đến 40f
        this.speed = (3f + Math.random() * 1f).toFloat()  // Random tốc độ từ 4 đến 7
    }
}



