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
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qnachlocal.BlueMenuViewModel
import com.example.qnachlocal.R
import com.example.qnachlocal.databinding.FragmentBankDetailsBinding
import com.example.qnachlocal.databinding.FragmentCustomerDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BankDetailsFragment : Fragment(){

    private lateinit var binding: FragmentBankDetailsBinding
    private lateinit var viewModel: BlueMenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this)[BlueMenuViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBankDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bank_details, container, false
        )
        binding.viewModel = viewModel//attach your viewModel to xml

        val frequency = resources.getStringArray(R.array.frequency)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,frequency)
        binding.spnFrequency.setAdapter(arrayAdapter)

        val category = resources.getStringArray(R.array.category)
        val arrayAdapterCategory = ArrayAdapter(requireContext(),R.layout.dropdown_item,category)
        binding.spnCategoryy.setAdapter(arrayAdapterCategory)

        val bank = resources.getStringArray(R.array.bank)
        val arrayAdapterAccType = ArrayAdapter(requireContext(),R.layout.dropdown_item,bank)
        binding.spnSelectBank.setAdapter(arrayAdapterAccType)

        binding.buttonMandate.setOnClickListener {
            if (binding.edtAccHolderName.text?.trim().toString() ==""){
                binding.edtAccHolderName.error="Enter Account Holder Name"
            }
            else if (binding.edtCustAccNo.text?.trim().toString() == ""){
                binding.edtCustAccNo.error="Enter Account Number"
            }
            else if (binding.edtConfirmAccNo.text?.trim().toString() == ""){
                binding.edtConfirmAccNo.error="Re enter Account Number"
            }
            else if (binding.edtCustAccNo.text?.trim().toString()!= binding.edtConfirmAccNo.text?.trim().toString()){
                binding.edtConfirmAccNo.error="Account Number Mismatch"
            }
            else if (binding.spnSelectBank.text?.trim().toString() == ""){
                binding.spnSelectBank.error="Select a Bank"
            }
            else if (binding.spnCategoryy.text?.trim().toString() == ""){
                binding.spnCategoryy.error="Select a Category"
            }
            else if (binding.spnFrequency.text?.trim().toString() == ""){
                binding.spnFrequency.error="Select Frequency"
            }
            else{
                findNavController().navigate(R.id.action_bankDetailsFragment_to_customerDetailsFragment)
            }
        }

        return binding.root
    }
}
//
// BaseFragment<FragmentBankDetailsBinding, BankDetailsViewModel>() {
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
