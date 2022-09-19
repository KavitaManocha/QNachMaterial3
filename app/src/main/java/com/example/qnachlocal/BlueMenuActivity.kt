package com.example.qnachlocal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.leandroferreira.wizard_forms.contract.WizardNavigator
import com.example.qnachlocal.databinding.ActivityBlueMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlueMenuActivity : AppCompatActivity(), WizardNavigator {

    private lateinit var binding:ActivityBlueMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlueMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = BlueMenuViewModel(this)
    }

    override fun nextPage() {
        if (binding.viewPager.currentItem < binding.viewPager.adapter!!.count) {
            binding.viewPager.currentItem = binding.viewPager.currentItem + 1
        }
    }

    override fun previousPage() {
        if (binding.viewPager.currentItem > 0) {
            binding.viewPager.currentItem = binding.viewPager.currentItem - 1
        }
    }

    override fun navigateToPage(page: Int) {
        binding.viewPager.currentItem = page
    }

    override fun cancelNavigation() {
        finish()
    }

    override fun currentPage(): Int = binding.viewPager.currentItem
}