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
import java.util.regex.Pattern

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>() {

    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

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

            if (binding.edtMail.text.toString() == "") {
                Toast.makeText(requireContext(), "Please Enter Email id", Toast.LENGTH_LONG).show()
            } else if (binding.edtPhoneNo.text.toString() == "") {
                Toast.makeText(requireContext(), "Please Enter Mobile Number", Toast.LENGTH_LONG).show()
            }else if (binding.edtPhoneNo.text.length<10 || binding.edtPhoneNo.text.length>10){
                Toast.makeText(requireContext(), "Please Enter a Valid Mobile Number of 10 digits", Toast.LENGTH_LONG).show()
            } else if (!EMAIL_ADDRESS_PATTERN.matcher(binding.edtMail.text?.trim().toString()).matches()){
                Toast.makeText(requireContext(), "Please Enter a Valid Email Id", Toast.LENGTH_LONG).show()
            }
            else {
                val loginRequest = ForgotPasswordRequest(
                    binding.edtMail.text.toString(),
                    binding.edtPhoneNo.text.toString()
                )
                viewModel.forgotPassword(loginRequest)



            }

            // checkPhoneValidation()
        }


    }

    private fun checkPhoneValidation() {
        val userId = binding.edtMail.text?.trim().toString()
        val password = binding.edtPhoneNo.text?.trim().toString()
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
            bundle.putString("email_id", binding.edtMail.text.toString())
            bundle.putString("phone_no", binding.edtPhoneNo.text.toString())
           findNavController().navigate(
               R.id.action_forgotPasswordFragment_to_verifyOtpFragment)
        }else{
            showAlertMessage(it.StatusDesc)
        }
        // showAlertMessage(it.ciphertext + it.aesCipher_nonce + it.authTag)
    }
}
