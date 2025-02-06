package com.example.gamefish.view

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.gamefish.R
import com.example.gamefish.databinding.ActivityMainBinding
import com.example.gamefish.viewmodel.FishTankViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var gameView: FishTankView
    private lateinit var binding: ActivityMainBinding
    private lateinit var fishTankViewModel: FishTankViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo ViewModel
        fishTankViewModel = ViewModelProvider(this).get(FishTankViewModel::class.java)

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
//            fishTankViewModel.createRandomFish()  // Gọi ViewModel để tạo cá mới
        }

        // Bắt đầu game
        gameView.startGame()
    }
}