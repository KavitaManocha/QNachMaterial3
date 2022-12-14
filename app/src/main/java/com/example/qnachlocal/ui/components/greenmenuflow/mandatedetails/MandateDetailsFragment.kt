package com.example.qnachlocal.ui.components.greenmenuflow.mandatedetails

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.chola.app.data.local.SessionManager
import com.example.qnachlocal.*
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.Usser
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.databinding.FragmentMandateDetailsBinding
import com.example.qnachlocal.ui.base.BaseFragment
import com.example.qnachlocal.utils.observe
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.showAlignBottom
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MandateDetailsFragment : BaseFragment<FragmentMandateDetailsBinding, SharedViewModel>(){
    override fun getViewModelClass() = SharedViewModel::class.java
    override fun getViewBinding() = FragmentMandateDetailsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        observeViewModel()
        inIt()
    }

    private fun observeViewModel() {
        observe(viewModel.userLoginLiveData, ::onLoginResult)
    }

    private fun inIt() {

        val date_n = SimpleDateFormat("dd MM, yyyy", Locale.getDefault()).format(Date())

        binding.edtMandateDate.setText(date_n)

        binding.edtMandateDate.setOnClickListener {


            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                requireActivity(),
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.edtMandateDate.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        binding.edtStartDate.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                requireActivity(),
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.edtStartDate.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        binding.edtEndDate.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                requireActivity(),
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.edtEndDate.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.datePicker.minDate = c.getTimeInMillis()
            datePickerDialog.show()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_mandateDetailsFragment_to_accountDetailsFragment)
        }

        binding.btnGen.setOnClickListener {

            val loan_id = getArguments()?.getString(LOAN_ID)
            val benef_name = getArguments()?.getString(BENEFICIARY_NAME)
            val cust_mob = getArguments()?.getString(CUSTOMER_MOBILE)
            val cust_email = getArguments()?.getString(CUSTOMER_EMAIL)
            val ifsc_code = getArguments()?.getString(IFSC_CODE)
            val cust_bank = getArguments()?.getString(CUSTOMER_BANK)
            val cust_bank_add = getArguments()?.getString(BANK_ADDRESS)
            val cust_acc_no = getArguments()?.getString(CUSTOMER_ACC_NO)
            val acc_type = getArguments()?.getString(ACCOUNT_TYPE)
            val category = getArguments()?.getString(CATEGORY)
            val freq = getArguments()?.getString(FREQUENCY)

            if (binding.edtAchAmount.text?.trim().toString() == "") {
//                binding.edtAchAmount.error = getString(R.string.error_ach_amount)
                binding.edtAchAmount.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_ach_amount))
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
            else if (binding.edtMandateDate.text?.trim().toString() == "") {
//                binding.edtMandateDate.error = getString(R.string.error_mandate_date)
                binding.edtMandateDate.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_mandate_date))
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
            else if (binding.edtStartDate.text?.trim().toString() == "") {
//                binding.edtStartDate.error = getString(R.string.error_start_date)
                binding.edtStartDate.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_start_date))
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
            else if (binding.edtEndDate.text?.trim().toString() == "") {
//                binding.edtEndDate.error = getString(R.string.error_end_date)
                binding.edtEndDate.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_end_date))
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

            else if (binding.edtReferenceNo.text?.trim().toString() == "") {
//                binding.edtReferenceNo.error = getString(R.string.error_ref_no)
                binding.edtReferenceNo.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_ref_no))
                        setTextColorResource(R.color.white)
                        setBackgroundColorResource(R.color.red)
                        setTextSize(16f)
                        onBalloonClickListener?.let { setOnBalloonClickListener(it) }
                        setBalloonAnimation(BalloonAnimation.FADE)
                        setLifecycleOwner(lifecycleOwner)
                        dismissWhenClicked = true
                    }
                )

            }else{
                val loginRequest= Usser(
                    benef_name.toString(),
                    cust_acc_no,
                    "1",// acc type drawer id
                    "2",
                    binding.edtAchAmount.text?.trim().toString(),
                    "6",  // freq type id value
                    "2",  //1 for fixed, 2 for var
                    cust_bank,
                    loan_id,
                    "2",
                    cust_email,
                    binding.edtEndDate.text?.trim().toString(),
                    "",
                    ifsc_code,
                    "1",
                    loan_id,
                    binding.edtMandateDate.text?.trim().toString(),
                    "1",
                    binding.edtAchAmount.text?.trim().toString(),
                    cust_mob,
                    "",
                    "2",
                    binding.edtReferenceNo.text?.trim().toString(),
                    "",
                    "4",
                    binding.edtStartDate.text?.trim().toString(),
                    "1",
                    "440")
                    //Usser(loan_id,benef_name,
//                    cust_mob.toString(),cust_email.toString(),
//                    ifsc_code,cust_bank,cust_bank_add,cust_acc_no,acc_type,
//                    category,freq,binding.edtAchAmount.text?.trim().toString(),
//                    binding.edtMandateDate.text?.trim().toString(),
//                    binding.edtStartDate.text?.trim().toString(),
//                    binding.edtEndDate.text?.trim().toString(),
//                    binding.edtReferenceNo.text?.trim().toString() )
                viewModel.genpdf(loginRequest)
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

    fun getCurrentDate(): String? {
        val c = Calendar.getInstance()
        val df = SimpleDateFormat("dd-MMM-yyyy")
        return df.format(c.time)
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