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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chola.app.data.dto.login.LoginResponse
import com.example.qnachlocal.DashBoardActivity
import com.example.qnachlocal.R
import com.example.qnachlocal.SharedViewModel
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
            if (binding.edtIfscCode.text?.trim().toString() ==""){
                binding.edtIfscCode.error="Enter IFSC Code"
            }
            else if (binding.edtIfscCode.text?.trim().toString().length >11|| binding.edtIfscCode.text?.trim().toString().length <11
                || !IFSC_CODE_PATTERN.matcher(binding.edtIfscCode.text?.trim().toString()).matches()){
                binding.edtIfscCode.error="Enter Valid IFSC Code"
            }
            else if (binding.edtCustBank.text?.trim().toString() == ""){
                binding.edtCustBank.error="Enter Customer Bank"
            }
            else if (binding.edtBankAddress.text?.trim().toString() == ""){
                binding.edtBankAddress.error="Enter Bank Address"
            }
            else if (binding.edtCustAccNo.text?.trim().toString() == ""){
                binding.edtCustAccNo.error="Enter Customer Account Number"
            }
            else if (binding.edtCustAccNo.text?.trim().toString().length>18 || binding.edtCustAccNo.text?.trim().toString().length<18){
                binding.edtCustAccNo.error="Enter valid Customer Account Number"
            }
            else if (binding.spnAccType.text?.trim().toString() == ""){
                binding.spnAccType.error="Select Account Type"
            }
            else if (binding.spnCategory.text?.trim().toString() == ""){
                binding.spnCategory.error="Select Category"
            }
            else if (binding.spnFreq.text?.trim().toString() == ""){
                binding.spnFreq.error="Select Frequency"
            }
            else{
                findNavController().navigate(R.id.action_accountDetailsFragment_to_mandateDetailsFragment)
            }
        }
        return binding.root
    }


}