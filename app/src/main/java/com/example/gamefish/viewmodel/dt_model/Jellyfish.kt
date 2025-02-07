package com.example.gamefish.viewmodel.dt_model

class Jellyfish(
    name: String,
    color: Int,
    x: Float,
    y: Float,
//    size: Float,
//    speed: Float
) : Fish(name,color,x,y){

    init {
        // Random size và speed cho Jellyfish
        this.size = (15f + Math.random() * 10f).toFloat()  // Random kích thước từ 15f đến 25f
        this.speed = (2f + Math.random() * 2f).toFloat()  // Random tốc độ từ 2 đến 4
    }

    // Override phương thức move của lớp Fish
    override fun move(left: Float, top: Float, right: Float, bottom: Float) {
        // Chỉ thay đổi vị trí trên trục Y, không thay đổi vị trí trên trục X
        y += directionY * speed

        // Tạo sự thay đổi ngẫu nhiên trong hướng Y (di chuyển lên hoặc xuống)
        directionY += (Math.random() * 0.2 - 0.1).toFloat()  // Thay đổi nhẹ hướng Y

        // Đảm bảo rằng hướng di chuyển vẫn giữ giá trị hợp lý trên trục Y
        if (directionY > 1) directionY = 1f
        if (directionY < -1) directionY = -1f

        // Kiểm tra chạm cạnh và điều chỉnh (chỉ áp dụng cho trục Y)
        if (y - size < top) {
            y = top + size  // Điều chỉnh khi chạm cạnh trên
            directionY *= -1  // Đổi hướng dọc khi chạm vào cạnh trên
        }
        if (y + size > bottom) {
            y = bottom - size  // Điều chỉnh khi chạm cạnh dưới
            directionY *= -1  // Đổi hướng dọc khi chạm vào cạnh dưới
        }

        // Không cần phải kiểm tra hay thay đổi vị trí trên trục X nữa
    }
}