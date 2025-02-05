package com.example.gamefish.viewmodel.dt_model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

open class SwordFish(
    name: String,
    color: Int,
    x: Float,
    y: Float,
    size: Float,
    speed: Float,
//    howTodo: ((x:Int, y:Int) -> String)?, // check list been fish tank de tim ra cac doi tuong trung x va y -> xu ly
//    check: ((fish: Fish) -> Unit)? // xu ly xem co muc dc con o phia truoc k
) : Fish(name,color,x,y,size,speed){

//    override fun draw(canvas: Canvas, paint: Paint) {
//        paint.color = color
//        paint.style = Paint.Style.FILL
//
//        canvas.drawCircle(x, y, size, paint)// paint fish
//
//        // Vẽ tên con cá dưới con cá (ví dụ: "Shark")
//        paint.color = Color.BLACK // Màu chữ
//        paint.textSize = 40f // Kích thước chữ
//        paint.textAlign = Paint.Align.CENTER // Căn giữa chữ
//
//        // Vẽ tên dưới cá
//        canvas.drawText(name, x, y + size + 35f, paint) // Dịch xuống 20px dưới vị trí cá
//    }
}