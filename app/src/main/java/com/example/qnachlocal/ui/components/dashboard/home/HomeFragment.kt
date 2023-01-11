package com.example.qnachlocal.ui.components.dashboard.home

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.*
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.RecyclerviewItem
import com.example.qnachlocal.databinding.FragmentHomeBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.utils.observe
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),CustomAdapterr.ItemListener {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterrr: RecyclerView.Adapter<CustomAdapterGreen.ViewHolder>? = null
    private var adapterr: RecyclerView.Adapter<CustomAdapterBlue.ViewHolder>? = null
    private lateinit var menuu: ArrayList<RecyclerviewItem>
    private lateinit var menuBlue: ArrayList<RecyclerviewItem>

    override fun getViewModelClass() = HomeViewModel::class.java
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpViews() {
        observeViewModel()
        inIt()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun inIt() {
        if ((activity as AppCompatActivity).supportActionBar != null) {
            (activity as AppCompatActivity?)?.getSupportActionBar()?.setTitle("qNach")
            (activity as AppCompatActivity).supportActionBar!!.hide()
        }

        binding.ivScanQr.setOnClickListener {
            val intentIntegrator =
                IntentIntegrator.forSupportFragment(this@HomeFragment)//IntentIntegrator(requireActivity())
            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
            intentIntegrator.initiateScan()
        }

        menuu = ArrayList()
        menuu.add(
            RecyclerviewItem(
                Color.parseColor("#EBFFB6"),
                R.drawable.ic_qnach_generate_pdf_green,
                getString(R.string.generate_pdf)
            )
        )
        menuu.add(
            RecyclerviewItem(
                Color.parseColor("#C6F2F6"),
                R.drawable.ic_qnach_register_emandate,
                getString(R.string.reg_emandate)
            )
        )
        menuu.add(
            RecyclerviewItem(
                Color.parseColor("#C6F2F6"),
                R.drawable.ic_qnach_generate_link,
                getString(R.string.gen_link_for_emandate)
            )
        )
        menuu.add(
            RecyclerviewItem(
                Color.parseColor("#EBFFB6"),
                R.drawable.ic_qnach_scannach_mandate,
                getString(R.string.scan_nach_mandate)
            )
        )
        binding.rvGreen.apply {
            layoutManager = GridLayoutManager(activity, 2)
            var adaptter = CustomAdapterr(requireContext(), menuu, this@HomeFragment)
            adapter = adaptter
//            adaptter.onItemClick = {
////                if (menuu.get(0).task.equals("Generate PDF")){
////                    val intent = Intent(requireContext(), GreenMenuActivity::class.java)
////                    startActivity(intent)
////                    requireActivity().finishAffinity()
////                }
////                else {
////                    val intent = Intent(requireContext(), BlueMenuActivity::class.java)
////                    startActivity(intent)
////                    requireActivity().finishAffinity()
////                }
////                Toast.makeText(requireActivity(),"Green Menu", Toast.LENGTH_SHORT).show()
//                val intent = Intent(requireContext(), GreenMenuActivity::class.java)
//                startActivity(intent)
//                requireActivity().finishAffinity()
//            }
        }

        menuBlue = ArrayList()
//        menuBlue.add(MenuItems(R.color.light_blue,R.drawable.ic_qnach_generate_link,getString(R.string.gen_link_for_emandate)))
//        menuBlue.add(MenuItems(R.color.light_blue,R.drawable.ic_qnach_register_emandate,getString(R.string.reg_emandate)))
//        binding.rvBlue.apply {
//            layoutManager = GridLayoutManager(activity,2)
//            var blueAdapter = AdapterBlueMenu(menuBlue)
//            adapter = blueAdapter
//            blueAdapter.onItemClick = {
//                Toast.makeText(requireActivity(),"Blue Menu", Toast.LENGTH_SHORT).show()
//            }
//        }

//          menuu= ArrayList()
//        menuu.add(RecyclerviewItem(R.color.light_green,R.drawable.ic_qnach_generate_pdf_green,getString(R.string.generate_pdf),R.drawable.ic_qnach_rightarrow_green))
//        menuu.add(RecyclerviewItem(R.color.light_green,R.drawable.ic_qnach_scannach_mandate,getString(R.string.scan_nach_mandate),R.drawable.ic_qnach_rightarrow_green))
////        menuu.add(RecyclerviewItem(R.color.light_blue,R.drawable.ic_qnach_generate_link,"Generate Link For eMandate",R.drawable.ic_qnach_rightarrow_green))
////        menuu.add(RecyclerviewItem(R.color.light_blue,R.drawable.ic_qnach_scannach_mandate,"Register eMandate",R.drawable.ic_qnach_rightarrow_green))
//            binding.rvGreenMenu.apply {
//                layoutManager = GridLayoutManager(activity,2)
//                var adaptter = CustomAdapterGreen(menuu)
//                adapter = adaptter
//                adaptter.onItemClick = {
//                    val intent = Intent(requireContext(), GreenMenuActivity::class.java)
//                        startActivity(intent)
//                        requireActivity().finishAffinity()
//                }
//            }
//
//        menuBlue= ArrayList()
//        menuBlue.add(RecyclerviewItem(R.color.light_blue,R.drawable.ic_qnach_generate_link,getString(R.string.gen_link_for_emandate),R.drawable.ic_qnach_rightarrow_3))
//        menuBlue.add(RecyclerviewItem(R.color.light_blue,R.drawable.ic_qnach_register_emandate,getString(R.string.reg_emandate),R.drawable.ic_qnach_rightarrow_3))
//        binding.rvBlueMenu.apply {
//            layoutManager = GridLayoutManager(activity,2)
//            var blueAdapter = CustomAdapterBlue(menuBlue)
//            adapter = blueAdapter
//blueAdapter.onItemClick = {
//    val intent = Intent(requireContext(), BlueMenuActivity::class.java)
//    startActivity(intent)
//    requireActivity().finishAffinity()
//}
//        }

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
                    intent.putExtra(SearchManager.QUERY, result.contents)
                    startActivity(intent)
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i -> })
                .create()
                .show()

        }
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                val intent = Intent(requireContext(), GreenMenuActivity::class.java)
                startActivity(intent)
            }
            1 -> {
                val intent = Intent(requireContext(), BlueMenuActivity::class.java)
                startActivity(intent)
            }
            2 -> {
                val intent = Intent(requireContext(), BlueMenuActivity::class.java)
                startActivity(intent)
            }
            3 -> {
                val intent = Intent(requireContext(), GreenMenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
}