package com.example.gamefish

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tạo GameView và đặt nó làm content view
        gameView = GameView(this)
        setContentView(gameView)

        // Bắt đầu game
        gameView.startGame()
    }

    override fun onPause() {
        super.onPause()
        gameView.stopGame()  // Dừng game khi chuyển sang trạng thái pause
    }

    override fun onResume() {
        super.onResume()
        gameView.startGame()  // Tiếp tục game khi quay lại
    }
}