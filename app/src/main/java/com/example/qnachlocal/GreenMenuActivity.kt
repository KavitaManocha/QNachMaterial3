package com.example.qnachlocal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount != 0) {
            fragmentManager.popBackStack()
        } else {
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle(R.string.dialogTitle)
            //set message for alert dialog
            builder.setMessage(R.string.message)

            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->
                val intent = Intent(this,DashBoardActivity::class.java)
                startActivity(intent)
            }
            //performing negative action
            builder.setNegativeButton("Cancel"){dialogInterface, which ->
                val intent = Intent(this,GreenMenuActivity::class.java)
                startActivity(intent)
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
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
