package com.example.qnachlocal.ui.components.verification.verifyotp

import android.content.Intent
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.example.qnachlocal.MainActivity
import com.example.qnachlocal.R
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.verifyotp.VerifyOtpRequest
import com.example.qnachlocal.data.local.SessionManager
import com.example.qnachlocal.databinding.FragmentLoginBinding
import com.example.qnachlocal.databinding.FragmentVerifyOtpBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.ui.components.login.LoginViewModel
import com.example.qnachlocal.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyOtpFragment : BaseFragment<FragmentVerifyOtpBinding, VerifyOtpViewModel>() {

    override fun getViewModelClass() = VerifyOtpViewModel::class.java
    override fun getViewBinding() = FragmentVerifyOtpBinding.inflate(layoutInflater)
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

        binding.edtVerifyOtp.setOnClickListener {
            val otp = binding.edtOtp.text?.trim().toString()
            val email_id: String? = arguments?.getString("email_id")
            val mobile_no: String? = arguments?.getString("mobile_no")
            if(otp == ""){
                Toast.makeText(requireContext(),"Please Enter Otp Send to You", Toast.LENGTH_LONG).show()
            }
            else if (binding.edtNewPswd.text.toString() == ""){
                Toast.makeText(requireContext(),"Please Enter New Password", Toast.LENGTH_LONG).show()
            }
            else if (binding.edtConfirmPassword.text.toString() == ""){
                Toast.makeText(requireContext(),"Confirm Password", Toast.LENGTH_LONG).show()
            }
            else if (binding.edtNewPswd.text.toString() != binding.edtConfirmPassword.text.toString()){
                Toast.makeText(requireContext(),"Password Mismatch", Toast.LENGTH_LONG).show()
            }
            else{
                val loginRequest=VerifyOtpRequest(email_id.toString(),mobile_no.toString(),otp,
                    binding.edtNewPswd.text.toString(),binding.edtConfirmPassword.text.toString())
                viewModel.verifyOtp(loginRequest)
                /*   val bundle = Bundle()
                   bundle.putString(CONTACT_NO, userId)
                   bundle.putString(AUTH_FLAG, "signIn")
                   findNavController().navigate(R.id.action_logIn_to_otp, bundle)*/
//                findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)

            }
        }


    }

    private fun checkPhoneValidation() {
        val userId = binding.edtOtp.text?.trim().toString()
         if(userId==""){
            Toast.makeText(requireContext(),"Please input Password", Toast.LENGTH_LONG).show()
        }
        else{
//            val loginRequest= LoginRequest(userId,password)
//            viewModel.loginUser(loginRequest)
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
            findNavController().navigate(R.id.action_verifyOtpFragment_to_loginFragment)
        }else{
            showAlertMessage(it.StatusDesc)
        }
        // showAlertMessage(it.ciphertext + it.aesCipher_nonce + it.authTag)
    }
}
