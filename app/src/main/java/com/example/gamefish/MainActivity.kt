package com.example.gamefish

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gamefish.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var gameView: FishTankView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tạo GameView và đặt nó làm content view
        gameView = FishTankView(this)
        binding.gameContainer.addView(gameView)

        // Bắt sự kiện nhấn nút Add để thêm một con cá ngẫu nhiên
        binding.btnAdd.setOnClickListener {
            gameView.createRandomFish()  // Thêm cá ngẫu nhiên vào GameView
        }

        // Bắt đầu game
        gameView.startGame()
    }
}