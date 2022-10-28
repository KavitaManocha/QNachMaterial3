package com.example.qnachlocal.ui.components.dashboard.reports

import android.content.Intent
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.CustomAdapterGreen
import com.example.qnachlocal.DashBoardActivity
import com.example.qnachlocal.R
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.data.data.dto.REquestReport
import com.example.qnachlocal.data.local.SessionManager
import com.example.qnachlocal.databinding.FragmentLoginBinding
import com.example.qnachlocal.databinding.FragmentReportsBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.ui.components.login.LoginViewModel
import com.example.qnachlocal.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ReportsFragment : BaseFragment<FragmentReportsBinding, ReportsViewModel>(), SortingListener {

    override fun getViewModelClass() = ReportsViewModel::class.java
    override fun getViewBinding() = FragmentReportsBinding.inflate(layoutInflater)
    private var callback: SortingListener? = null
    val regex = "^[A-Z]{5}[-][A-Z]{2}[-][0-9]{2}[-][0-9]{7}\$".toRegex()
    //val regex1 = "^[A-Z]{2}[-][0-9]{2}[-][0-9]{7}\$".toRegex()
    override fun setUpViews() {
        observeViewModel()
        inIt()
    }

    private fun observeViewModel() {
        observe(viewModel.userLoginLiveData, ::onLoginResult)
    }

    private fun inIt() {
        val date_n = SimpleDateFormat("dd MM, yyyy", Locale.getDefault()).format(Date())

        val sessionManager = SessionManager(requireContext())
        val loginResponse = sessionManager.getUserDetail()

        val rEquestReport = REquestReport(loginResponse?.data!!.org_id,loginResponse?.data!!.user_id,date_n, date_n)
viewModel.loginUser(rEquestReport)



        binding.reportsRecyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ReportsAdapter()
        }

        callback = this@ReportsFragment
        binding.ivCalendar.setOnClickListener {
            val sort = SortBottomDialogFragment(callback)
            sort.show(childFragmentManager, "SortDialogFragment")
        }

//        binding.btnLogin.setOnClickListener {
//            val userId = binding.edtUsername.text?.trim().toString()
//            val password = binding.edtPassword.text?.trim().toString()
//            if(userId == ""){
//                Toast.makeText(requireContext(),"Please input UserId", Toast.LENGTH_LONG).show()
//            }
//            else if(password==""){
//                Toast.makeText(requireContext(),"Please input Password", Toast.LENGTH_LONG).show()
//            }
//            else if (password.length< 6){
//                Toast.makeText(requireContext(),"The length of password must be of 6 digits", Toast.LENGTH_LONG).show()
//            }
//            else{
//                val loginRequest= LoginRequest(userId,password)
//                viewModel.loginUser(loginRequest)
//                /*   val bundle = Bundle()
//                   bundle.putString(CONTACT_NO, userId)
//                   bundle.putString(AUTH_FLAG, "signIn")
//                   findNavController().navigate(R.id.action_logIn_to_otp, bundle)*/
////                findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
//
//            }
//        }


    }

//    private fun checkPhoneValidation() {
//        val userId = binding.edtUsername.text?.trim().toString()
//        val password = binding.edtPassword.text?.trim().toString()
//        if(userId == ""){
//            Toast.makeText(requireContext(),"Please input UserId", Toast.LENGTH_LONG).show()
//        }
//        else if(password==""){
//            Toast.makeText(requireContext(),"Please input Password", Toast.LENGTH_LONG).show()
//        }
//        else{
//            val loginRequest= LoginRequest(userId,password)
//            viewModel.loginUser(loginRequest)
//            /*   val bundle = Bundle()
//               bundle.putString(CONTACT_NO, userId)
//               bundle.putString(AUTH_FLAG, "signIn")
//               findNavController().navigate(R.id.action_logIn_to_otp, bundle)*/
//        }
//
//    }

    private fun showAlertMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
    }

    private fun onLoginResult(status: Resource<PDFResponse>) {
//        binding.buttonLogin.startAnimation()
        when (status) {
            is Resource.Success -> status.data?.let {
                checkResponse(it)
//                binding.buttonLogin.revertAnimation()
            }
            is Resource.DataError -> {
//                binding.buttonLogin.revertAnimation()
                //status.errorCode?.let { viewModel.showToastMessage(it) }
            }
            else -> {}
        }
    }

    private fun checkResponse(it: PDFResponse) {
        if(it.StatusCode == "NP001"){

            showAlertMessage(it.StatusDesc.toString())

        }else{
            showAlertMessage(it.StatusDesc.toString())
        }
        // showAlertMessage(it.ciphertext + it.aesCipher_nonce + it.authTag)
    }

    override fun onSorting(startdate: String?, endDate: String?) {

    }
}
