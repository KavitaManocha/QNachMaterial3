package com.example.qnachlocal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.leandroferreira.wizard_forms.contract.WizardNavigator
import com.example.qnachlocal.databinding.ActivityBlueMenuBinding
import com.example.qnachlocal.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlueMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlueMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlueMenuBinding.inflate(layoutInflater)
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