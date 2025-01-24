//package com.example.gamefish
//
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.sync.Mutex
//import kotlinx.coroutines.sync.withLock
//
//class FishTank(
//    val left: Float,
//    val top: Float,
//    val right: Float,
//    val bottom: Float
//){
//    private val fishes = mutableListOf<Fish>() // Danh sách cá
//    private val mutex = Mutex() // Đảm bảo an toàn khi truy cập danh sách cá
//
//    // Thêm cá vào bể
//    fun addFish(fish: Fish) {
//        runBlocking {
//            mutex.withLock {
//                fishes.add(fish)
//            }
//        }
//    }
//
//    // Loại bỏ cá bị ăn
//    suspend fun removeFish(fish: Fish) {
//        mutex.withLock {
//            fishes.remove(fish)
//        }
//    }
//
//    // Lấy danh sách cá an toàn
//    suspend fun getFishes(): List<Fish> {
//        return mutex.withLock { fishes.toList() }
//    }
//
//    // Cập nhật vị trí cá
//    suspend fun updateFishPosition(fish: Fish, deltaTime: Double) {
//        mutex.withLock {
//            fish.x += fish.vx * deltaTime
//            fish.y += fish.vy * deltaTime
//
//            // Va chạm với tường
//            if (fish.x < 0 || fish.x > width) fish.vx = -fish.vx
//            if (fish.y < 0 || fish.y > height) fish.vy = -fish.vy
//
//            // Giữ cá trong bể
//            fish.x = fish.x.coerceIn(0.0, width)
//            fish.y = fish.y.coerceIn(0.0, height)
//        }
//    }
//
//    // Xử lý va chạm giữa các con cá
//    suspend fun handleCollisions() {
//        mutex.withLock {
//            val toRemove = mutableListOf<Fish>()
//            for (i in fishes.indices) {
//                for (j in i + 1 until fishes.size) {
//                    val fish1 = fishes[i]
//                    val fish2 = fishes[j]
//                    if (fish1.canEat(fish2)) {
//                        fish1.size += fish2.size / 2
//                        toRemove.add(fish2)
//                    } else if (fish2.canEat(fish1)) {
//                        fish2.size += fish1.size / 2
//                        toRemove.add(fish1)
//                    }
//                }
//            }
//            fishes.removeAll(toRemove)
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
