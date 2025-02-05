package com.example.gamefish.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.gamefish.viewmodel.dt_model.Fish

abstract class FishView(val fish: Fish) {

    // Hàm vẽ cá trên canvas
    abstract fun draw(canvas: Canvas, paint: Paint)

}