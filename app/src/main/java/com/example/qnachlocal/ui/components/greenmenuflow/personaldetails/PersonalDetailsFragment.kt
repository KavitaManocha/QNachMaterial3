package com.example.qnachlocal.ui.components.greenmenuflow.personaldetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qnachlocal.*
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.data.dto.UserReq
import com.example.qnachlocal.databinding.FragmentPersonalDetailsBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.utils.observe
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.showAlignBottom
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
//                binding.edtCustId.error= getString(R.string.error_loan_id)
                binding.edtCustId.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_loan_id))
                        setTextColorResource(R.color.white)
                        setBackgroundColorResource(R.color.red)
                        setTextSize(16f)
                        onBalloonClickListener?.let { setOnBalloonClickListener(it) }
                        setBalloonAnimation(BalloonAnimation.FADE)
                        setLifecycleOwner(lifecycleOwner)
                        dismissWhenClicked = true
                    }
                )
            }
            else if (binding.edtBenefName.text?.trim().toString() == ""){
//                binding.edtBenefName.error=getString(R.string.error_beneficiary_name)
                binding.edtBenefName.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_beneficiary_name))
                        setTextColorResource(R.color.white)
                        setBackgroundColorResource(R.color.red)
                        setTextSize(16f)
                        onBalloonClickListener?.let { setOnBalloonClickListener(it) }
                        setBalloonAnimation(BalloonAnimation.FADE)
                        setLifecycleOwner(lifecycleOwner)
                        dismissWhenClicked = true
                    }
                )
            }
            else if (binding.edtCustMob.text?.trim().toString() == ""){
//                binding.edtCustMob.error= getString(R.string.error_mobile_no)
                binding.edtCustMob.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_mobile_no))
                        setTextColorResource(R.color.white)
                        setBackgroundColorResource(R.color.red)
                        setTextSize(16f)
                        onBalloonClickListener?.let { setOnBalloonClickListener(it) }
                        setBalloonAnimation(BalloonAnimation.FADE)
                        setLifecycleOwner(lifecycleOwner)
                        dismissWhenClicked = true
                    }
                )
            }
            else if (binding.edtCustMob.text?.trim().toString().length<10 || binding.edtCustMob.text?.trim().toString().length>10){
//                binding.edtCustMob.error= getString(R.string.error_mobile_validation)
                binding.edtCustMob.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_mobile_validation))
                        setTextColorResource(R.color.white)
                        setBackgroundColorResource(R.color.red)
                        setTextSize(16f)
                        onBalloonClickListener?.let { setOnBalloonClickListener(it) }
                        setBalloonAnimation(BalloonAnimation.FADE)
                        setLifecycleOwner(lifecycleOwner)
                        dismissWhenClicked = true
                    }
                )
            }
            else if (binding.edtCustEmail.text?.trim().toString() == ""){
//                binding.edtCustEmail.error= getString(R.string.error_mail_id)
                binding.edtCustEmail.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_mail_id))
                        setTextColorResource(R.color.white)
                        setBackgroundColorResource(R.color.red)
                        setTextSize(16f)
                        onBalloonClickListener?.let { setOnBalloonClickListener(it) }
                        setBalloonAnimation(BalloonAnimation.FADE)
                        setLifecycleOwner(lifecycleOwner)
                        dismissWhenClicked = true
                    }
                )
            }
            else if (!EMAIL_ADDRESS_PATTERN.matcher(binding.edtCustEmail.text?.trim().toString()).matches()){
//                binding.edtCustEmail.error= getString(R.string.error_mail_validation)
                binding.edtCustEmail.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_mail_validation))
                        setTextColorResource(R.color.white)
                        setBackgroundColorResource(R.color.red)
                        setTextSize(16f)
                        onBalloonClickListener?.let { setOnBalloonClickListener(it) }
                        setBalloonAnimation(BalloonAnimation.FADE)
                        setLifecycleOwner(lifecycleOwner)
                        dismissWhenClicked = true
                    }
                )
            }
            else{
                val bundle = Bundle()
                bundle.putString(LOAN_ID,binding.edtCustId.text?.trim().toString())
                bundle.putString(BENEFICIARY_NAME,binding.edtBenefName.text?.trim().toString())
                bundle.putString(CUSTOMER_MOBILE,binding.edtCustMob.text?.trim().toString())
                bundle.putString(CUSTOMER_EMAIL,binding.edtCustEmail.text?.trim().toString())
                findNavController().navigate(R.id.action_personalDetailsFragment_to_accountDetailsFragment)
            }
        }
        return binding.root
    }


}