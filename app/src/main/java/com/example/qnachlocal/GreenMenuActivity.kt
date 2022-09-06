package com.example.qnachlocal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qnachlocal.databinding.ActivityGreenMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GreenMenuActivity: AppCompatActivity() {
    private lateinit var binding: ActivityGreenMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGreenMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}