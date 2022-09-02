package com.example.qnachlocal.ui.components.greenmenuflow.accountdetails

import android.content.Intent
import android.widget.Toast
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.DashBoardActivity
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.local.SessionManager
import com.example.qnachlocal.databinding.FragmentAccountDetailsBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountDetailsFragment : BaseFragment<FragmentAccountDetailsBinding, AccountDetailsViewModel>() {

    override fun getViewModelClass() = AccountDetailsViewModel::class.java
    override fun getViewBinding() = FragmentAccountDetailsBinding.inflate(layoutInflater)
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


        binding.btnNext.setOnClickListener {
            val ifscCode = binding.edtIfscCode.text?.trim().toString()
            val custBank = binding.edtCustBank.text?.trim().toString()
            val bankAdd = binding.edtBankAddress.text?.trim().toString()
            val custAccNo = binding.edtCustAccNo.text?.trim().toString()
            val accType = binding.spnAccType.text?.trim().toString()
            val category = binding.spnCategory.text?.trim().toString()
            val frequency = binding.spnFreq.text?.trim().toString()
            if(ifscCode == ""){
                Toast.makeText(requireContext(),"Enter IFSC Code", Toast.LENGTH_LONG).show()
            }
            else if(custBank==""){
                Toast.makeText(requireContext(),"Kindly Input Customer Bank", Toast.LENGTH_LONG).show()
            }
            else if(bankAdd==""){
                Toast.makeText(requireContext(),"Enter Bank Address", Toast.LENGTH_LONG).show()
            }
            else if(custAccNo==""){
                Toast.makeText(requireContext(),"Kindly Enter Customer Account Number", Toast.LENGTH_LONG).show()
            }
            else if(accType==""){
                Toast.makeText(requireContext(),"Select an Account Type", Toast.LENGTH_LONG).show()
            }
            else if(category==""){
                Toast.makeText(requireContext(),"Select a Category", Toast.LENGTH_LONG).show()
            }
            else if(frequency==""){
                Toast.makeText(requireContext(),"Select a Frequency", Toast.LENGTH_LONG).show()
            }
            else{
//                val loginRequest= LoginRequest(userId,password)
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
