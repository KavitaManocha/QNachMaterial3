package com.example.qnachlocal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qnachlocal.databinding.ActivityBlueMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlueMenuActivity : AppCompatActivity(){

    private lateinit var binding:ActivityBlueMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlueMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}