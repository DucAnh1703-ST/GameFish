package com.example.gamefish

import android.graphics.Canvas
import android.graphics.Paint

class Shark(
    name: String,
    color: Int,
    x: Float,
    y: Float,
    size: Float,
    speed: Float,
//    howTodo: ((x:Int, y:Int) -> String)?, // check list been fish tank de tim ra cac doi tuong trung x va y -> xu ly
//    check: ((fish: Fish) -> Unit)? // xu ly xem co muc dc con o phia truoc k
) : Fish(name,color,x,y,size,speed){

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.color = color
        paint.style = Paint.Style.FILL

        canvas.drawCircle(x, y, size, paint)// paint fish
    }
}

