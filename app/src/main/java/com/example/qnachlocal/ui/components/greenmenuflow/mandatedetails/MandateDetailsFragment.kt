package com.example.qnachlocal.ui.components.greenmenuflow.mandatedetails

import android.content.Intent
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.DashBoardActivity
import com.example.qnachlocal.R
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.local.SessionManager
import com.example.qnachlocal.databinding.FragmentMandateDetailsBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MandateDetailsFragment : BaseFragment<FragmentMandateDetailsBinding, MandateDetailsViewModel>() {

    override fun getViewModelClass() = MandateDetailsViewModel::class.java
    override fun getViewBinding() = FragmentMandateDetailsBinding.inflate(layoutInflater)
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

        binding.btnGen.setOnClickListener {
            val achAmt = binding.edtAchAmount.text?.trim().toString()
            val mandateDate = binding.edtMandateDate.text?.trim().toString()
            val startDate = binding.edtStartDate.text?.trim().toString()
            val endDate = binding.edtEndDate.text?.trim().toString()
            val untilChecked = binding.chkbxCheck.isChecked.toString()
            val referenceNo = binding.edtReferenceNo.text?.trim().toString()
            if(achAmt == ""){
                Toast.makeText(requireContext(),"Please enter Amount", Toast.LENGTH_LONG).show()
            }
            else if(mandateDate==""){
                Toast.makeText(requireContext(),"Please enter Mandate Details", Toast.LENGTH_LONG).show()
            }
            else if (startDate == ""){
                Toast.makeText(requireContext(),"Please enter Start Date", Toast.LENGTH_LONG).show()
            }
            else if (endDate == "" && untilChecked == ""){
                Toast.makeText(requireContext(),"Please enter End Date or select Checkbox ", Toast.LENGTH_LONG).show()
            }
            else if (referenceNo == ""){
                Toast.makeText(requireContext(),"The length of password must be of 6 digits", Toast.LENGTH_LONG).show()
            }
            else{
//                val loginRequest=LoginRequest(userId,password)
//                viewModel.loginUser(loginRequest)
                /*   val bundle = Bundle()
                   bundle.putString(CONTACT_NO, userId)
                   bundle.putString(AUTH_FLAG, "signIn")
                   findNavController().navigate(R.id.action_logIn_to_otp, bundle)*/
//                findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)

            }
        }


    }

    private fun showAlertMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
    }

    private fun onLoginResult(status: Resource<LoginResponse>) {
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

    private fun checkResponse(it: LoginResponse) {
        if(it.StatusCode == "NP001"){
            val sessionManager= SessionManager(requireContext())
            sessionManager.storeUserDetail(it)
            // println("===========${sessionManager.getUserDetail()}")
            showAlertMessage(it.StatusDesc)
            val intent= Intent(requireContext(), DashBoardActivity::class.java)
            startActivity(intent)
            requireActivity().finishAffinity()
        }else{
            showAlertMessage(it.StatusDesc)
        }
        // showAlertMessage(it.ciphertext + it.aesCipher_nonce + it.authTag)
    }
}
