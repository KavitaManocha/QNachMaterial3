package com.example.qnachlocal.ui.components.bluemenuflow.bankdetails

import android.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.chola.app.data.local.SessionManager
import com.example.qnachlocal.*
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.Usser
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.databinding.FragmentBankDetailsBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.utils.observe
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.showAlignBottom
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class BankDetailsFragment : BaseFragment<FragmentBankDetailsBinding, SharedViewModel>() {

    override fun getViewModelClass() = SharedViewModel::class.java
    override fun getViewBinding() = FragmentBankDetailsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        observeViewModel()
        inIt()
    }

    private fun observeViewModel() {
        observe(viewModel.userLoginLiveData, ::onLoginResult)
    }

    private fun inIt() {

        val date_n = SimpleDateFormat("dd MM, yyyy", Locale.getDefault()).format(Date())

        val frequency = resources.getStringArray(R.array.frequency)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,frequency)
        binding.spnFrequency.setAdapter(arrayAdapter)

        val category = resources.getStringArray(R.array.category)
        val arrayAdapterCategory = ArrayAdapter(requireContext(),R.layout.dropdown_item,category)
        binding.spnCategoryy.setAdapter(arrayAdapterCategory)

        val bank = resources.getStringArray(R.array.bank)
        val arrayAdapterAccType = ArrayAdapter(requireContext(),R.layout.dropdown_item,bank)
        binding.spnSelectBank.setAdapter(arrayAdapterAccType)


        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_bankDetailsFragment_to_customerDetailsFragment)
        }

        binding.buttonMandate.setOnClickListener {

            val loan_id = getArguments()?.getString(LOAN_ID)
            val cust_mob = getArguments()?.getString(CUSTOMER_MOBILE)
            val cust_email = getArguments()?.getString(CUSTOMER_EMAIL)
            val ach_amt = getArguments()?.getString(ACH_AMT)
            val first_coll_date = getArguments()?.getString(FIRST_COLLECTION_DATE)
            val final_coll_date = getArguments()?.getString(FINAL_COLLECTION_DATE)
            val date_n = SimpleDateFormat("dd MM, yyyy", Locale.getDefault()).format(Date())

            if (binding.edtAccHolderName.text?.trim().toString() ==""){
//                binding.edtAccHolderName.error="Enter Account Holder Name"
                binding.edtAccHolderName.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_acc_holder))
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
            else if (binding.edtCustAccNo.text?.trim().toString() == ""){
//                binding.edtCustAccNo.error="Enter Account Number"
                binding.edtCustAccNo.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.enter_acc_no))
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
            else if (binding.edtConfirmAccNo.text?.trim().toString() == ""){
//                binding.edtConfirmAccNo.error="Re enter Account Number"
                binding.edtConfirmAccNo.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_reenter_acc_no))
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
            else if (binding.edtCustAccNo.text?.trim().toString()!= binding.edtConfirmAccNo.text?.trim().toString()){
//                binding.edtConfirmAccNo.error="Account Number Mismatch"
                binding.edtConfirmAccNo.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.acc_no_mismatch))
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
            else if (binding.spnSelectBank.text?.trim().toString() == ""){
//                binding.spnSelectBank.error="Select a Bank"
                binding.spnSelectBank.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.select_a_bank))
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
            else if (binding.spnCategoryy.text?.trim().toString() == ""){
//                binding.spnCategoryy.error="Select a Category"
                binding.spnCategoryy.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.select_a_category))
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
            else if (binding.spnFrequency.text?.trim().toString() == ""){
//                binding.spnFrequency.error="Select Frequency"
                binding.spnFrequency.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.select_frequency))
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
                val loginRequest= Usser(binding.edtAccHolderName.text?.trim().toString(),binding.edtCustAccNo.text?.trim().toString(),"1","2",ach_amt,"6","2",
                    binding.spnSelectBank.text?.trim().toString(),loan_id,"2",cust_email,final_coll_date,"","SBIN0002772","1",loan_id,date_n,
                    "1",ach_amt,cust_mob,"","2","",
                    "","4",first_coll_date,"1","440")
                viewModel.genpdf(loginRequest)
            }

        }

    }

    private fun showAlertMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
    }

    private fun onLoginResult(status: Resource<PDFResponse>) {
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

    private fun checkResponse(it: PDFResponse) {
        if(it.StatusCode == "NP001"){

            val builder= AlertDialog.Builder(requireContext(), androidx.constraintlayout.widget.R.style.AlertDialog_AppCompat).create()
            val view = layoutInflater.inflate(R.layout.dialog_pdf_generated,null)

            builder.setView(view)

            val sessionManager = SessionManager(requireContext())
            val pdf_url = sessionManager.getPdfDetails()?.pdf
            var url = view.findViewById<TextView>(R.id.tv_link_to_pdf)
            url.text = pdf_url

            val home= view.findViewById<AppCompatButton>(R.id.btn_home)
            home.setOnClickListener {

            }

            val download = view.findViewById<AppCompatButton>(R.id.btn_download)
            download.setOnClickListener {

            }

            builder.setCanceledOnTouchOutside(false)
            builder.show()
        }
        else{

            showAlertMessage(it.StatusDesc.toString())
        }
        // showAlertMessage(it.ciphertext + it.aesCipher_nonce + it.authTag)
    }

}