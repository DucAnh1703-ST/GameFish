package com.example.gamefish.viewmodel.dt_model

class Crab(
    name: String,
    color: Int,
    x: Float,
    y: Float,
//    size: Float,
//    speed: Float
) : Fish(name,color,x,y){

    init {
        // Random size và speed cho Crab
        this.size = (10f + Math.random() * 10f).toFloat()  // Random kích thước từ 10f đến 20f
        this.speed = (1f + Math.random() * 2f).toFloat()  // Random tốc độ từ 1 đến 3
    }

    // Override phương thức move của lớp Fish
    override fun move(left: Float, top: Float, right: Float, bottom: Float) {
        // Chỉ thay đổi vị trí trên trục X, không thay đổi vị trí trên trục Y
        x += directionX * speed

        // Tạo sự thay đổi ngẫu nhiên trong hướng X (di chuyển sang trái hoặc phải)
        directionX += (Math.random() * 0.2 - 0.1).toFloat()  // Thay đổi nhẹ hướng X

        // Đảm bảo rằng hướng di chuyển vẫn giữ giá trị hợp lý trên trục X
        if (directionX > 1) directionX = 1f
        if (directionX < -1) directionX = -1f

        // Kiểm tra chạm cạnh và điều chỉnh (chỉ áp dụng cho trục X)
        if (x - size < left) {
            x = left + size  // Điều chỉnh khi chạm cạnh trái
            directionX *= -1  // Đổi hướng ngang khi chạm vào cạnh trái
        }
        if (x + size > right) {
            x = right - size  // Điều chỉnh khi chạm cạnh phải
            directionX *= -1  // Đổi hướng ngang khi chạm vào cạnh phải
        }

        // Không cần phải kiểm tra hay thay đổi vị trí trên trục Y nữa
    }

    // Override phương thức xử lý va chạm để rùa chỉ ăn được cua và sứa
    override fun handleCollision(otherFish: Fish) {
        if (otherFish is Jellyfish) { // Chỉ ăn sứa
            if (this.size > otherFish.size) {
                this.size += otherFish.size / 3 // Rùa ăn và tăng kích thước
                otherFish.size = 0f  // Sứa bị ăn mất
            }
        }
    }
}

