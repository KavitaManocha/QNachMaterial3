package com.example.qnachlocal

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import androidx.databinding.DataBindingUtil
//import androidx.lifecycle.ViewModelProvider
//import com.example.qnachlocal.data.data.dto.User
//import com.example.qnachlocal.databinding.ActivitySampleBinding
//
//class SampleActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivitySampleBinding
//    private lateinit var viewModel: SharedViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample)
//        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = this
//
//
//        binding.buttonNext.setOnClickListener {
//            Toast.makeText(this,"Hi ${binding.edtCustId.text?.trim().toString()}! ",Toast.LENGTH_SHORT).show()
//        }
//    }
//}