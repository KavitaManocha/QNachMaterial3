package com.example.qnachlocal.ui.components.dashboard.home

import android.app.SearchManager
import android.content.DialogInterface
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.*
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.databinding.FragmentHomeBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.utils.observe
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterrr: RecyclerView.Adapter<CustomAdapterGreen.ViewHolder>? = null
    private var adapterr: RecyclerView.Adapter<CustomAdapterBlue.ViewHolder>? = null

    override fun getViewModelClass() = HomeViewModel::class.java
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpViews() {
        observeViewModel()
        inIt()
    }

    private fun inIt() {


            binding.rvGreenMenu.apply {
                layoutManager = GridLayoutManager(activity,2)
                var adaptter = CustomAdapterGreen()
                adapter = adaptter
                adaptter.setOnItemClickListener(object : CustomAdapterGreen.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        val intent = Intent(requireContext(), GreenMenuActivity::class.java)
                        startActivity(intent)
                        requireActivity().finishAffinity()
                    }
                })
            }

        binding.rvBlueMenu.apply {
            layoutManager = GridLayoutManager(activity,2)
            var blueAdapter = CustomAdapterBlue()
            adapter = blueAdapter
            blueAdapter.setOnItemClickListener(object :CustomAdapterBlue.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(requireContext(), BlueMenuActivity::class.java)
                    startActivity(intent)
                    requireActivity().finishAffinity()
                }

            })
        }

    }

    private fun observeViewModel() {
        observe(viewModel.userLoginLiveData, ::onLoginResult)
    }


    private fun showAlertMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
    }

    private fun onLoginResult(status: Resource<LoginResponse>) {
        when (status) {
            is Resource.Success -> status.data?.let {
                checkResponse(it)
            }
            is Resource.DataError -> {
                status.errorCode?.let { viewModel.showToastMessage(it) }
            }
            else -> {}
        }
    }

    private fun checkResponse(it: LoginResponse) {
        // showAlertMessage(it.ciphertext + it.aesCipher_nonce + it.authTag)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null) {
            AlertDialog.Builder(requireContext())
                .setMessage("Would you like to go to ${result.contents}?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent(Intent.ACTION_WEB_SEARCH)
                    intent.putExtra(SearchManager.QUERY,result.contents)
                    startActivity(intent)
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->  })
                .create()
                .show()

        }
    }
}