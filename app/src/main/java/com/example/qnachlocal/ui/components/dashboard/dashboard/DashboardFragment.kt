package com.example.qnachlocal.ui.components.dashboard.dashboard

import android.app.SearchManager
import android.content.DialogInterface
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.*
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.DashboardItem
import com.example.qnachlocal.data.data.dto.RecyclerviewItem
import com.example.qnachlocal.databinding.FragmentDashboardBinding
import com.example.qnachlocal.databinding.FragmentHomeBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.ui.components.dashboard.home.HomeViewModel
import com.example.qnachlocal.utils.observe
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterrr: RecyclerView.Adapter<CustomAdapterGreen.ViewHolder>? = null
    private var adapterr: RecyclerView.Adapter<CustomAdapterBlue.ViewHolder>? = null
    private lateinit var menuu: ArrayList<DashboardItem>
    private lateinit var menuBlue: ArrayList<DashboardItem>

    override fun getViewModelClass() = DashboardViewModel::class.java
    override fun getViewBinding() = FragmentDashboardBinding.inflate(layoutInflater)

    override fun setUpViews() {
        observeViewModel()
        inIt()
    }

    private fun inIt() {

        if((activity as AppCompatActivity).supportActionBar != null){
            (activity as AppCompatActivity?)?.getSupportActionBar()?.setTitle("Dashboard")
            (activity as AppCompatActivity).supportActionBar!!.hide()
        }

        menuu = ArrayList()
        menuu.add(
            DashboardItem(
                R.color.light_green,
                "0", getString(R.string.nach_pdf_gend),
                R.drawable.ic_qnach_rightarrow_green
            )
        )
        menuu.add(
            DashboardItem(
                R.color.light_green,
                "0", getString(R.string.phy_nach_scanned),
                R.drawable.ic_qnach_rightarrow_green
            )
        )
//        menuu.add(
//            RecyclerviewItem(
//                R.color.light_blue,
//                R.drawable.ic_qnach_generate_link,
//                "Generate Link For eMandate",
//                R.drawable.ic_qnach_rightarrow_3
//            )
//        )
//        menuu.add(
//            RecyclerviewItem(
//                R.color.light_blue,
//                R.drawable.ic_qnach_scannach_mandate,
//                "Register eMandate",
//                R.drawable.ic_qnach_rightarrow_3
//            )
//        )

        binding.dashboardRecyclerview.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = DashboardAdapterGreen(menuu)
        }

        menuBlue= ArrayList()
        menuBlue.add(DashboardItem(R.color.light_blue,"0",getString(R.string.emandates_pending),R.drawable.ic_qnach_rightarrow_3))
        menuBlue.add(DashboardItem(R.color.light_blue,"0",getString(R.string.emandates_completed),R.drawable.ic_qnach_rightarrow_3))

        binding.dashboardRecyclerviewBlue.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = DashboardAdapterBlue(menuBlue)
        }

        binding.ivScanQr.setOnClickListener {
            val intentIntegrator = IntentIntegrator.forSupportFragment(this@DashboardFragment)//IntentIntegrator(requireActivity())
            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
            intentIntegrator.initiateScan()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null) {
            AlertDialog.Builder(requireContext())
                .setMessage("Would you like to go to ${result.contents}?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent(Intent.ACTION_WEB_SEARCH)
                    intent.putExtra(SearchManager.QUERY, result.contents)
                    startActivity(intent)
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i -> })
                .create()
                .show()

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