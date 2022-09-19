package com.example.qnachlocal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.leandroferreira.wizard_forms.contract.WizardNavigator
import com.example.qnachlocal.databinding.ActivityGreenMenuBinding
import com.example.qnachlocal.ui.components.greenmenuflow.personaldetails.PersonalDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GreenMenuActivity: AppCompatActivity() {
    private lateinit var binding: ActivityGreenMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGreenMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.viewModel = GreenMenuViewModel(this)
        supportFragmentManager.beginTransaction()
            .add(R.id.container_a, PersonalDetailsFragment())
            .commit()
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGreenMenuBinding.inflate(layoutInflater)
//        setContentView(binding.root)
////        binding.viewModel = GreenMenuViewModel(this)
//    }

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
