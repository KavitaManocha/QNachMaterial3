package com.example.qnachlocal.ui.components.bluemenuflow.customerdetails

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qnachlocal.*
import com.example.qnachlocal.databinding.FragmentCustomerDetailsBinding
import com.example.qnachlocal.databinding.FragmentPersonalDetailsBinding
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.showAlignBottom
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.regex.Pattern

@AndroidEntryPoint
class CustomerDetailsFragment : Fragment(){

    private lateinit var binding: FragmentCustomerDetailsBinding
    private lateinit var viewModel: BlueMenuViewModel
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
            ViewModelProvider(this)[BlueMenuViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCustomerDetailsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_customer_details, container, false)
        binding.viewModel = viewModel//attach your viewModel to xml

        binding.edtFirstCollDate.setOnClickListener {
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
                    binding.edtFirstCollDate.setText(dat)
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

        binding.edtFinalCollDate.setOnClickListener {
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
                    binding.edtFinalCollDate.setText(dat)
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

        binding.buttonNext.setOnClickListener {
            if (binding.edtCustomerId.text?.trim().toString() ==""){
//                binding.edtCustomerId.error="Enter Loan Id"
                binding.edtCustomerId.showAlignBottom(
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
            else if (binding.edtMobileNumber.text?.trim().toString() == ""){
//                binding.edtMobileNumber.error="Enter Mobile Number"
                binding.edtMobileNumber.showAlignBottom(
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
            else if (binding.edtMobileNumber.text?.trim().toString().length<10 || binding.edtMobileNumber.text?.trim().toString().length>10){
//                binding.edtMobileNumber.error="Enter Valid Mobile Number"
                binding.edtMobileNumber.showAlignBottom(
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
            else if (binding.edtEmail.text?.trim().toString() == ""){
//                binding.edtEmail.error="Enter Email Id"
                binding.edtEmail.showAlignBottom(
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
            else if (!EMAIL_ADDRESS_PATTERN.matcher(binding.edtEmail.text?.trim().toString()).matches()){
//                binding.edtEmail.error="Enter Valid Email Id"
                binding.edtEmail.showAlignBottom(
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
            else if (binding.edtAchAmount.text?.trim().toString() == ""){
//                binding.edtAchAmount.error="Enter Ach Amount"
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
            else if (binding.edtFirstCollDate.text?.trim().toString() == ""){
//                binding.edtFirstCollDate.error="Select First Collection Date"
                binding.edtFirstCollDate.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_first_coll_date))
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
            else if (binding.edtFinalCollDate.text?.trim().toString() == ""){
//                binding.edtFinalCollDate.error="Select Final Collection Date"
                binding.edtFinalCollDate.showAlignBottom(
                    createBalloon(requireContext()) {
                        setArrowSize(7)
                        setWidthRatio(0.9f)
                        setHeight(45)
                        setArrowPosition(0.03f)
                        setCornerRadius(4f)
                        setAlpha(0.9f)
                        setText(getString(R.string.error_final_coll_date))
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
                bundle.putString(LOAN_ID,binding.edtCustomerId.text?.trim().toString())
                bundle.putString(CUSTOMER_MOBILE,binding.edtMobileNumber.text?.trim().toString())
                bundle.putString(CUSTOMER_EMAIL,binding.edtEmail.text?.trim().toString())
                bundle.putString(ACH_AMT,binding.edtAchAmount.text?.trim().toString())
                bundle.putString(FIRST_COLLECTION_DATE,binding.edtFirstCollDate.text?.trim().toString())
                bundle.putString(FINAL_COLLECTION_DATE,binding.edtFinalCollDate.text?.trim().toString())
                findNavController().navigate(R.id.action_customerDetailsFragment_to_bankDetailsFragment)
            }
        }
        return binding.root
    }

}
//
//
// BaseFragment<FragmentCustomerDetailsBinding, CustomerDetailsViewModel>() {
//
//    override fun getViewModelClass() = CustomerDetailsViewModel::class.java
//    override fun getViewBinding() = FragmentCustomerDetailsBinding.inflate(layoutInflater)
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
//            val custId = binding.edtCustomerId.text?.trim().toString()
//            val mobileNo = binding.edtMobileNumber.text?.trim().toString()
//            val email = binding.edtEmail.text?.trim().toString()
//            val achAmt = binding.edtAchAmount.text?.trim().toString()
//            val firstCollDate = binding.edtFirstCollDate.text?.trim().toString()
//            val lastCollDate = binding.edtFinalCollDate.text?.trim().toString()
//            val EMAIL_ADDRESS_PATTERN = Pattern.compile(
//                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//                        "\\@" +
//                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                        "(" +
//                        "\\." +
//                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//                        ")+"
//            )
//            val untilChecked = binding.chkbxCheck.isChecked.toString()
//            if(custId == ""){
//                Toast.makeText(requireContext(),"Please input UserId", Toast.LENGTH_LONG).show()
//            }
//            else if(mobileNo==""){
//                Toast.makeText(requireContext(),"Please input Password", Toast.LENGTH_LONG).show()
//            }
//            else if (mobileNo.length< 6){
//                Toast.makeText(requireContext(),"The length of password must be of 6 digits", Toast.LENGTH_LONG).show()
//            }
//            else if (email == ""){
//                Toast.makeText(requireContext(),"Enter Email Id", Toast.LENGTH_LONG).show()
//            }
//            else if (!EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
//                Toast.makeText(requireContext(),"Enter a valid email address", Toast.LENGTH_LONG).show()
//            }
//            else if (achAmt == ""){
//                Toast.makeText(requireContext(),"Kindly enter the amount", Toast.LENGTH_LONG).show()
//            }
//            else if (firstCollDate == ""){
//                Toast.makeText(requireContext(),"Select the first Collection Date", Toast.LENGTH_LONG).show()
//            }
//            else if (lastCollDate == "" && untilChecked == ""){
//                Toast.makeText(requireContext(),"Either Specify Final Collection Date or Select Checkbox", Toast.LENGTH_LONG).show()
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
////    private fun checkPhoneValidation() {
////        val userId = binding.edtUsername.text?.trim().toString()
////        val password = binding.edtPassword.text?.trim().toString()
////        if(userId == ""){
////            Toast.makeText(requireContext(),"Please input UserId", Toast.LENGTH_LONG).show()
////        }
////        else if(password==""){
////            Toast.makeText(requireContext(),"Please input Password", Toast.LENGTH_LONG).show()
////        }
////        else{
////            val loginRequest= LoginRequest(userId,password)
////            viewModel.loginUser(loginRequest)
////            /*   val bundle = Bundle()
////               bundle.putString(CONTACT_NO, userId)
////               bundle.putString(AUTH_FLAG, "signIn")
////               findNavController().navigate(R.id.action_logIn_to_otp, bundle)*/
////        }
////
////    }
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
