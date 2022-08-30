package com.example.qnachlocal.ui.components.password.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.example.qnachlocal.MainActivity
import com.example.qnachlocal.R
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.forgotpassword.ForgotPasswordRequest
import com.example.qnachlocal.data.local.SessionManager
import com.example.qnachlocal.databinding.FragmentForgotPasswordBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>() {

    override fun getViewModelClass() = ForgotPasswordViewModel::class.java
    override fun getViewBinding() = FragmentForgotPasswordBinding.inflate(layoutInflater)
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

        binding.edtResetPassword.setOnClickListener {
            val userId = binding.edtEmailId.text?.trim().toString()
            val mobileNo = binding.edtPassword.text?.trim().toString()
            if(userId == ""){
                Toast.makeText(requireContext(),"Please input UserId", Toast.LENGTH_LONG).show()
            }
            else if(mobileNo==""){
                Toast.makeText(requireContext(),"Please input Password", Toast.LENGTH_LONG).show()
            }
            else if (mobileNo.length<10 || mobileNo.length>10){
                Toast.makeText(requireContext(),"Enter a valid phone number", Toast.LENGTH_LONG).show()
            }
            else{
                val loginRequest= ForgotPasswordRequest(userId,mobileNo)
                viewModel.forgotPassword(loginRequest)
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

    private fun checkPhoneValidation() {
        val userId = binding.edtEmailId.text?.trim().toString()
        val password = binding.edtPassword.text?.trim().toString()
        if(userId == ""){
            Toast.makeText(requireContext(),"Please input UserId", Toast.LENGTH_LONG).show()
        }
        else if(password==""){
            Toast.makeText(requireContext(),"Please input Password", Toast.LENGTH_LONG).show()
        }
        else{
            val loginRequest= ForgotPasswordRequest(userId,password)
            viewModel.forgotPassword(loginRequest)
            /*   val bundle = Bundle()
               bundle.putString(CONTACT_NO, userId)
               bundle.putString(AUTH_FLAG, "signIn")
               findNavController().navigate(R.id.action_logIn_to_otp, bundle)*/
        }

    }

    private fun showAlertMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
    }

    private fun onLoginResult(status: Resource<ResetPasswordResponse>) {
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

    private fun checkResponse(it: ResetPasswordResponse) {
        if(it.StatusCode == "NP000"){
            showAlertMessage(it.StatusDesc)
            val bundle = Bundle()
            bundle.putString("email_id", binding.edtEmailId.text.toString())
            bundle.putString("mobile_no",binding.edtPassword.text?.trim().toString() )
           findNavController().navigate(
               R.id.action_forgotPasswordFragment_to_verifyOtpFragment)
        }else{
            showAlertMessage(it.StatusDesc)
        }
        // showAlertMessage(it.ciphertext + it.aesCipher_nonce + it.authTag)
    }
}
