package com.example.qnachlocal.ui.components.bluemenuflow.bankdetails

//import android.content.Intent
//import android.widget.Toast
//import com.chola.app.data.dto.login.LoginResponse
//import com.example.qnachlocal.DashBoardActivity
//import com.example.qnachlocal.data.Resource
//import com.example.qnachlocal.data.local.SessionManager
//import com.example.qnachlocal.databinding.FragmentBankDetailsBinding
//import com.example.qnachlocal.ui.base.BaseFragment
//import com.example.qnachlocal.utils.observe
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class BankDetailsFragment : BaseFragment<FragmentBankDetailsBinding, BankDetailsViewModel>() {
//
//    override fun getViewModelClass() = BankDetailsViewModel::class.java
//    override fun getViewBinding() = FragmentBankDetailsBinding.inflate(layoutInflater)
//    val regex = "^[A-Z]{5}[-][A-Z]{2}[-][0-9]{2}[-][0-9]{7}\$".toRegex()
//    //val regex1 = "^[A-Z]{2}[-][0-9]{2}[-][0-9]{7}\$".toRegex()
//    override fun setUpViews() {
//        observeViewModel()
//        inIt()
//    }
//
//    private fun observeViewModel() {
//        observe(viewModel.userLoginLiveData, ::onLoginResult)
//    }
//
//    private fun inIt() {
//
//        binding.buttonMandate.setOnClickListener {
//            val acc_holder_name = binding.edtAccHolderName.text?.trim().toString()
//            val cust_acc_no = binding.edtCustAccNo.text?.trim().toString()
//            val confirm_account_no = binding.edtConfirmAccNo.text?.trim().toString()
//            val select_bank = binding.spnSelectBank.text?.trim().toString()
//            val category = binding.spnCategoryy.text?.trim().toString()
//            val frequency = binding.spnFrequency.text?.trim().toString()
//            if(acc_holder_name == ""){
//                Toast.makeText(requireContext(),"Please Enter Account Holder Name", Toast.LENGTH_LONG).show()
//            }
//            else if(cust_acc_no==""){
//                Toast.makeText(requireContext(),"Please Enter Customer Account Number", Toast.LENGTH_LONG).show()
//            }
//            else if (cust_acc_no.length< 9){
//                Toast.makeText(requireContext(),"The Length of Customer Account Number must be of 6 digits", Toast.LENGTH_LONG).show()
//            }
//            else if (confirm_account_no == ""){
//                Toast.makeText(requireContext(),"Confirm Customer Account Number", Toast.LENGTH_LONG).show()
//            }
//            else if(cust_acc_no != confirm_account_no){
//                Toast.makeText(requireContext(),"Account Number Mismatch", Toast.LENGTH_LONG).show()
//            }
//            else if(select_bank==""){
//                Toast.makeText(requireContext(),"Kindly Select a Bank", Toast.LENGTH_LONG).show()
//            }
//            else if(category==""){
//                Toast.makeText(requireContext(),"Kindly Select Category", Toast.LENGTH_LONG).show()
//            }
//            else if(frequency==""){
//                Toast.makeText(requireContext(),"Kindly Select Frequency", Toast.LENGTH_LONG).show()
//            }
//            else{
////                val loginRequest= LoginRequest(userId,password)
////                viewModel.loginUser(loginRequest)
//                /*   val bundle = Bundle()
//                   bundle.putString(CONTACT_NO, userId)
//                   bundle.putString(AUTH_FLAG, "signIn")
//                   findNavController().navigate(R.id.action_logIn_to_otp, bundle)*/
////                findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
//
//            }
//        }
//
//
//    }
//
//    private fun showAlertMessage(s: String) {
//        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
//    }
//
//    private fun onLoginResult(status: Resource<LoginResponse>) {
////        binding.buttonLogin.startAnimation()
//        when (status) {
//            is Resource.Success -> status.data?.let {
//                checkResponse(it)
////                binding.buttonLogin.revertAnimation()
//            }
//            is Resource.DataError -> {
////                binding.buttonLogin.revertAnimation()
//                //status.errorCode?.let { viewModel.showToastMessage(it) }
//            }
//            else -> {}
//        }
//    }
//
//    private fun checkResponse(it: LoginResponse) {
//        if(it.StatusCode == "NP001"){
//            val sessionManager= SessionManager(requireContext())
//            sessionManager.storeUserDetail(it)
//            // println("===========${sessionManager.getUserDetail()}")
//            showAlertMessage(it.StatusDesc)
//            val intent= Intent(requireContext(), DashBoardActivity::class.java)
//            startActivity(intent)
//            requireActivity().finishAffinity()
//        }else{
//            showAlertMessage(it.StatusDesc)
//        }
//        // showAlertMessage(it.ciphertext + it.aesCipher_nonce + it.authTag)
//    }
//}
