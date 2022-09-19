package com.example.qnachlocal.ui.components.greenmenuflow.personaldetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qnachlocal.R
import com.example.qnachlocal.SharedViewModel
import com.example.qnachlocal.databinding.FragmentPersonalDetailsBinding



class PersonalDetailsFragment: Fragment(){

    private var _binding: FragmentPersonalDetailsBinding? = null
    //    lateinit var et_loan_id: TextInputEditText
//    lateinit var et_benef_name: TextInputEditText
//    lateinit var et_cust_mob: TextInputEditText
//    lateinit var et_cust_email: TextInputEditText
    private val binding get() = _binding!!
    private lateinit var personalDetailsViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalDetailsBinding.inflate(inflater, container, false)
        return binding.root

        personalDetailsViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        binding.buttonNext.setOnClickListener{
            if (binding.edtCustId.text.toString() == ""){
                Toast.makeText(requireActivity(),"Enter Loan Id",Toast.LENGTH_SHORT).show()
            }
            else if (binding.edtBenefName.text.toString() == ""){
                Toast.makeText(requireActivity(),"Enter Beneficiary Name",Toast.LENGTH_SHORT).show()
            }
            else if (binding.edtCustMob.text.toString() == ""){
                Toast.makeText(requireActivity(),"Enter Mobile Number",Toast.LENGTH_SHORT).show()
            }
            else if (binding.edtCustMob.text.toString().length<10||binding.edtCustMob.text.toString().length>10){
                Toast.makeText(requireActivity(),"Enter Valid Mobile Number",Toast.LENGTH_SHORT).show()
            }
            else if (binding.edtCustEmail.text.toString() == ""){
                Toast.makeText(requireActivity(),"Enter Loan Id",Toast.LENGTH_SHORT).show()
            }
            else{
                personalDetailsViewModel?.setLoanId(binding.edtCustId.text?.toString())
                personalDetailsViewModel?.setBenefName(binding.edtBenefName.text?.toString())
                personalDetailsViewModel?.setCustMob(binding.edtCustMob.text?.toString())
                personalDetailsViewModel?.setCustEmail(binding.edtCustEmail.text?.toString())
                findNavController().navigate(R.id.action_personalDetailsFragment_to_accountDetailsFragment)
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        personalDetailsViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        personalDetailsViewModel.getLoanId()?.observe(viewLifecycleOwner,object :
            Observer<String?> {
            override fun onChanged(charSequence: String?) {
                binding.edtCustId.setText(charSequence)
            }
        })

        personalDetailsViewModel.getBenefName()?.observe(viewLifecycleOwner,object :
            Observer<String?> {
            override fun onChanged(charSequence: String?) {
                binding.edtBenefName.setText(charSequence)
            }
        })

        personalDetailsViewModel.getCustMob()?.observe(viewLifecycleOwner,object :
            Observer<String?> {
            override fun onChanged(charSequence: String?) {
                binding.edtCustMob.setText(charSequence)
            }
        })

        personalDetailsViewModel.getCustEmail()?.observe(viewLifecycleOwner,object :
            Observer<String?> {
            override fun onChanged(charSequence: String?) {
                binding.edtCustEmail.setText(charSequence)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

//import android.content.Intent
//import android.widget.Toast
//import androidx.navigation.fragment.findNavController
//import com.chola.app.data.dto.login.LoginRequest
//import com.chola.app.data.dto.login.LoginResponse
//import com.example.qnachlocal.DashBoardActivity
//import com.example.qnachlocal.R
//import com.example.qnachlocal.data.Resource
//import com.example.qnachlocal.data.local.SessionManager
//import com.example.qnachlocal.databinding.FragmentPersonalDetailsBinding
//import com.example.qnachlocal.ui.base.BaseFragment
//import com.example.qnachlocal.utils.observe
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class PersonalDetailsFragment : BaseFragment<FragmentPersonalDetailsBinding, PersonalDetailsViewModel>() {
//
//    override fun getViewModelClass() = PersonalDetailsViewModel::class.java
//    override fun getViewBinding() = FragmentPersonalDetailsBinding.inflate(layoutInflater)
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
//        binding.buttonNext.setOnClickListener {
//            val custId = binding.edtCustId.text?.trim().toString()
//            val benefName = binding.edtBenefName.text?.trim().toString()
//            val custMob = binding.edtCustMob.text?.trim().toString()
//            val custEmail = binding.edtCustEmail.text?.trim().toString()
//            if(custId == ""){
//                Toast.makeText(requireContext(),"Please input LoanId", Toast.LENGTH_LONG).show()
//            }
//            else if(benefName==""){
//                Toast.makeText(requireContext(),"Please enter Beneficiary Name", Toast.LENGTH_LONG).show()
//            }
//            else if(custMob==""){
//                Toast.makeText(requireContext(),"Please enter Customer Mobile Number", Toast.LENGTH_LONG).show()
//            }
//            else if(custEmail==""){
//                Toast.makeText(requireContext(),"Please enter Customer Email Id", Toast.LENGTH_LONG).show()
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
