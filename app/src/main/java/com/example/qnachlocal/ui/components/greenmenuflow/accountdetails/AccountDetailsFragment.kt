package com.example.qnachlocal.ui.components.greenmenuflow.accountdetails

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.*
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.local.SessionManager
import com.example.qnachlocal.databinding.FragmentAccountDetailsBinding
import com.example.qnachlocal.databinding.FragmentPersonalDetailsBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

//
@AndroidEntryPoint
class AccountDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAccountDetailsBinding
    private lateinit var viewModel: SharedViewModel
    val IFSC_CODE_PATTERN = Pattern.compile(
         "^[A-Z]{4}0[A-Z0-9]{6}$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAccountDetailsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_account_details, container, false)
        binding.viewModel = viewModel//attach your viewModel to xml

        val frequency = resources.getStringArray(R.array.frequency)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,frequency)
        binding.spnFreq.setAdapter(arrayAdapter)

        val category = resources.getStringArray(R.array.category)
        val arrayAdapterCategory = ArrayAdapter(requireContext(),R.layout.dropdown_item,category)
        binding.spnCategory.setAdapter(arrayAdapterCategory)

        val accType = resources.getStringArray(R.array.accType)
        val arrayAdapterAccType = ArrayAdapter(requireContext(),R.layout.dropdown_item,accType)
        binding.spnAccType.setAdapter(arrayAdapterAccType)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_accountDetailsFragment_to_personalDetailsFragment)
        }

        binding.btnNext.setOnClickListener {

            val loan_id = getArguments()?.getString(LOAN_ID)
            val benef_name = getArguments()?.getString(BENEFICIARY_NAME)
            val cust_mob = getArguments()?.getString(CUSTOMER_MOBILE)
            val cust_email = getArguments()?.getString(CUSTOMER_EMAIL)

            if (binding.edtIfscCode.text?.trim().toString() ==""){
                binding.edtIfscCode.error=getString(R.string.error_ifsc_code)
            }
            else if (binding.edtIfscCode.text?.trim().toString().length >11|| binding.edtIfscCode.text?.trim().toString().length <11
                || !IFSC_CODE_PATTERN.matcher(binding.edtIfscCode.text?.trim().toString()).matches()){
                binding.edtIfscCode.error=getString(R.string.error_ifsc_code_validation)
            }
            else if (binding.edtCustBank.text?.trim().toString() == ""){
                binding.edtCustBank.error=getString(R.string.error_customer_bank)
            }
            else if (binding.edtBankAddress.text?.trim().toString() == ""){
                binding.edtBankAddress.error=getString(R.string.error_bank_address)
            }
            else if (binding.edtCustAccNo.text?.trim().toString() == ""){
                binding.edtCustAccNo.error=getString(R.string.error_customer_account_number)
            }
            else if (binding.edtCustAccNo.text?.trim().toString().length>18 || binding.edtCustAccNo.text?.trim().toString().length<18){
                binding.edtCustAccNo.error=getString(R.string.error_customer_account_number_validation)
            }
            else if (binding.spnAccType.text?.trim().toString() == ""){
                binding.spnAccType.error=getString(R.string.error_account_type)
            }
            else if (binding.spnCategory.text?.trim().toString() == ""){
                binding.spnCategory.error=getString(R.string.error_category)
            }
            else if (binding.spnFreq.text?.trim().toString() == ""){
                binding.spnFreq.error=getString(R.string.error_frequency)
            }
            else{
                val bundle = Bundle()
                bundle.putString(LOAN_ID,loan_id)
                bundle.putString(BENEFICIARY_NAME,benef_name)
                bundle.putString(CUSTOMER_MOBILE,cust_mob)
                bundle.putString(CUSTOMER_EMAIL,cust_email)
                bundle.putString(IFSC_CODE,binding.edtIfscCode.text?.trim().toString())
                bundle.putString(CUSTOMER_BANK,binding.edtCustBank.text?.trim().toString())
                bundle.putString(BANK_ADDRESS,binding.edtBankAddress.text?.trim().toString())
                bundle.putString(CUSTOMER_ACC_NO,binding.edtCustAccNo.text?.trim().toString())
                bundle.putString(ACCOUNT_TYPE,binding.spnAccType.text?.trim().toString())
                bundle.putString(CATEGORY,binding.spnCategory.text?.trim().toString())
                bundle.putString(FREQUENCY,binding.spnFreq.text?.trim().toString())
                findNavController().navigate(R.id.action_accountDetailsFragment_to_mandateDetailsFragment)
            }
        }
        return binding.root
    }


}