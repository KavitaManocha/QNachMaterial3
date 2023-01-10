package com.example.qnachlocal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.qnachlocal.databinding.ActivityGreenMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GreenMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGreenMenuBinding
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGreenMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stepView.done(false)

        binding.btnNext.setOnClickListener {
            when (position) {
                0 -> {
                    binding.personalDetails.visibility = View.GONE
                    binding.accountDetails.visibility = View.VISIBLE
                    position = 1
                    binding.stepView.done(false)
                    binding.stepView.go(position, true)
                    binding.btnNext.text = "NEXT"
                    binding.btnBack.visibility = View.VISIBLE
                    binding.btnBack.text = "BACK"
                }
                1 -> {
                    binding.accountDetails.visibility = View.GONE
                    binding.mandateDetails.visibility = View.VISIBLE
                    position = 2
                    binding.stepView.done(false)
                    binding.stepView.go(position, true)
                    binding.btnNext.text = "NACH GENERATE"
                    binding.btnBack.visibility = View.VISIBLE
                    binding.btnBack.text = "BACK"

                }
                else -> {
                    binding.mandateDetails.visibility = View.GONE
                    binding.personalDetails.visibility = View.VISIBLE
                    position = 0
                    binding.stepView.done(false)
                    binding.stepView.go(position, true)
                    binding.btnNext.text = "NEXT"
                    binding.btnBack.visibility = View.GONE
                }
            }
        }

        binding.btnBack.setOnClickListener{
            when(position){
                1->{
                    binding.personalDetails.visibility = View.VISIBLE
                    binding.accountDetails.visibility = View.GONE
                    position = 0
                    binding.stepView.done(false)
                    binding.stepView.go(position, true)
                    binding.btnNext.text = "Next"
                    binding.btnBack.visibility = View.GONE
                }
                2 -> {
                    binding.accountDetails.visibility = View.VISIBLE
                    binding.mandateDetails.visibility = View.GONE
                    position = 1
                    binding.stepView.done(false)
                    binding.stepView.go(position, true)
                    binding.btnNext.text = "Next"
                    binding.btnBack.text = "Back"
                }
            }
        }
    }


    override fun onBackPressed() {
        when (position) {
            0 -> {
                val builder = AlertDialog.Builder(this)
                //set title for alert dialog
                builder.setTitle(R.string.dialogTitle)
                //set message for alert dialog
                builder.setMessage(R.string.message)

                //performing positive action
                builder.setPositiveButton("Yes") { dialogInterface, which ->
                    val intent = Intent(this, DashBoardActivity::class.java)
                    startActivity(intent)
                }
                //performing negative action
                builder.setNegativeButton("Cancel") { dialogInterface, which ->
                    val intent = Intent(this, GreenMenuActivity::class.java)
                    startActivity(intent)
                }
                // Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                // Set other dialog properties
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
            1 -> {
                binding.personalDetails.visibility = View.VISIBLE
                binding.accountDetails.visibility = View.GONE
                position = 0
                binding.stepView.done(false)
                binding.stepView.go(position, true)
                binding.btnNext.text = "Next"
                binding.btnBack.visibility = View.GONE

            }
            2 -> {
                binding.accountDetails.visibility = View.VISIBLE
                binding.mandateDetails.visibility = View.GONE
                position = 1
                binding.stepView.done(false)
                binding.stepView.go(position, true)
                binding.btnNext.text = "Next"
                binding.btnBack.text = "Back"
            }
        }
    }
}


//    override fun nextPage() {
//        if (binding.viewPager.currentItem < binding.viewPager.adapter!!.count) {
//            binding.viewPager.currentItem = binding.viewPager.currentItem + 1
//        }
//    }
//
//    override fun previousPage() {
//        if (binding.viewPager.currentItem > 0) {
//            binding.viewPager.currentItem = binding.viewPager.currentItem - 1
//        }
//    }
//
//    override fun navigateToPage(page: Int) {
//        binding.viewPager.currentItem = page
//    }
//
//    override fun cancelNavigation() {
//        finish()
//    }
//
//    override fun currentPage(): Int = binding.viewPager.currentItem
//}
