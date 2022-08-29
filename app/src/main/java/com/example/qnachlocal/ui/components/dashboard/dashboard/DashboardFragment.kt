package com.example.qnachlocal.ui.components.dashboard.dashboard

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.CustomAdapterBlue
import com.example.qnachlocal.CustomAdapterGreen
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.databinding.FragmentDashboardBinding
import com.example.qnachlocal.databinding.FragmentHomeBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.ui.components.dashboard.home.HomeViewModel
import com.example.qnachlocal.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding,DashboardViewModel>() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterrr: RecyclerView.Adapter<CustomAdapterGreen.ViewHolder>? = null
    private var adapterr: RecyclerView.Adapter<CustomAdapterBlue.ViewHolder>? = null

    override fun getViewModelClass() = DashboardViewModel::class.java
    override fun getViewBinding() = FragmentDashboardBinding.inflate(layoutInflater)

    override fun setUpViews() {
        observeViewModel()
        inIt()
    }

    private fun inIt() {
        binding.dashboardRecyclerview.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = CustomAdapterGreen()
        }

        binding.dashboardRecyclerviewBlue.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = CustomAdapterBlue()
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
}