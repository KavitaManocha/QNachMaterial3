package com.example.qnachlocal.ui.components.greenmenuflow.personaldetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qnachlocal.CONTACT_NO
import com.example.qnachlocal.R
import com.example.qnachlocal.SharedViewModel
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.data.dto.UserReq
import com.example.qnachlocal.databinding.FragmentPersonalDetailsBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class PersonalDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPersonalDetailsBinding
    private lateinit var viewModel: SharedViewModel
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
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
        val binding: FragmentPersonalDetailsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_personal_details, container, false)
        binding.viewModel = viewModel//attach your viewModel to xml

        binding.buttonNext.setOnClickListener {
            if (binding.edtCustId.text?.trim().toString() ==""){
                binding.edtCustId.error="Enter Loan Id"
            }
            else if (binding.edtBenefName.text?.trim().toString() == ""){
                binding.edtBenefName.error="Enter Beneficiary Name"
            }
            else if (binding.edtCustMob.text?.trim().toString() == ""){
                binding.edtCustMob.error="Enter Mobile Number"
            }
            else if (binding.edtCustMob.text?.trim().toString().length<10 || binding.edtCustMob.text?.trim().toString().length>10){
                binding.edtCustMob.error="Enter Valid Mobile Number"
            }
            else if (binding.edtCustEmail.text?.trim().toString() == ""){
                binding.edtCustEmail.error="Enter Email Id"
            }
            else if (!EMAIL_ADDRESS_PATTERN.matcher(binding.edtCustEmail.text?.trim().toString()).matches()){
                binding.edtCustEmail.error="Enter Valid Email Id"
            }
            else{
                findNavController().navigate(R.id.action_personalDetailsFragment_to_accountDetailsFragment)
            }
        }
        return binding.root
    }


}