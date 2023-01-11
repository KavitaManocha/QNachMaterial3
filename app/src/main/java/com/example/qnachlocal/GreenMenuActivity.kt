package com.example.qnachlocal

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.qnachlocal.data.Usser
import com.example.qnachlocal.databinding.ActivityGreenMenuBinding
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.showAlignBottom
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

@AndroidEntryPoint
class GreenMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGreenMenuBinding
    private var position = 0
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    val IFSC_CODE_PATTERN = Pattern.compile(
        "^[A-Z]{4}0[A-Z0-9]{6}$"
    )
    private lateinit var viewModel : SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGreenMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.getChildAt(0).setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(applicationContext,DashBoardActivity::class.java)
                startActivity(intent)
            }

        })

        binding.stepView.done(false)

        val frequency = resources.getStringArray(R.array.frequency)
        val arrayAdapter = ArrayAdapter(applicationContext,R.layout.dropdown_item,frequency)
        binding.spnFreq.setAdapter(arrayAdapter)

        val category = resources.getStringArray(R.array.category)
        val arrayAdapterCategory = ArrayAdapter(applicationContext,R.layout.dropdown_item,category)
        binding.spnCategory.setAdapter(arrayAdapterCategory)

        val accType = resources.getStringArray(R.array.accType)
        val arrayAdapterAccType = ArrayAdapter(applicationContext,R.layout.dropdown_item,accType)
        binding.spnAccType.setAdapter(arrayAdapterAccType)

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
                applicationContext,
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
                this,
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
                this,
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

        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        binding.btnNext.setOnClickListener {
            when (position) {
                0 -> {
                    if (binding.edtCustId.text?.trim().toString() ==""){
//                binding.edtCustId.error= getString(R.string.error_loan_id)
                        binding.edtCustId.showAlignBottom(
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                        binding.personalDetails.visibility = View.GONE
                        binding.accountDetails.visibility = View.VISIBLE
                        position = 1
                        binding.stepView.done(false)
                        binding.stepView.go(position, true)
                        binding.btnNext.text = "NEXT"
                        binding.btnBack.visibility = View.VISIBLE
                        binding.btnBack.text = "BACK"
                    }
                }
                1 -> {
                    if (binding.edtIfscCode.text?.trim().toString() ==""){
//                binding.edtIfscCode.error=getString(R.string.error_ifsc_code)
                        binding.edtIfscCode.showAlignBottom(
                            createBalloon(applicationContext) {
                                setArrowSize(7)
                                setWidthRatio(0.9f)
                                setHeight(45)
                                setArrowPosition(0.03f)
                                setCornerRadius(4f)
                                setAlpha(0.9f)
                                setText(getString(R.string.error_ifsc_code))
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
                    else if (binding.edtIfscCode.text?.trim().toString().length >11|| binding.edtIfscCode.text?.trim().toString().length <11
                        || !IFSC_CODE_PATTERN.matcher(binding.edtIfscCode.text?.trim().toString()).matches()){
//                binding.edtIfscCode.error=getString(R.string.error_ifsc_code_validation)
                        binding.edtIfscCode.showAlignBottom(
                            createBalloon(applicationContext) {
                                setArrowSize(7)
                                setWidthRatio(0.9f)
                                setHeight(45)
                                setArrowPosition(0.03f)
                                setCornerRadius(4f)
                                setAlpha(0.9f)
                                setText(getString(R.string.error_ifsc_code_validation))
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
                    else if (binding.edtCustBank.text?.trim().toString() == ""){
//                binding.edtCustBank.error=getString(R.string.error_customer_bank)
                        binding.edtCustBank.showAlignBottom(
                            createBalloon(applicationContext) {
                                setArrowSize(7)
                                setWidthRatio(0.9f)
                                setHeight(45)
                                setArrowPosition(0.03f)
                                setCornerRadius(4f)
                                setAlpha(0.9f)
                                setText(getString(R.string.error_customer_bank))
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
                    else if (binding.edtBankAddress.text?.trim().toString() == ""){
//                binding.edtBankAddress.error=getString(R.string.error_bank_address)
                        binding.edtBankAddress.showAlignBottom(
                            createBalloon(applicationContext) {
                                setArrowSize(7)
                                setWidthRatio(0.9f)
                                setHeight(45)
                                setArrowPosition(0.03f)
                                setCornerRadius(4f)
                                setAlpha(0.9f)
                                setText(getString(R.string.error_bank_address))
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
//                binding.edtCustAccNo.error=getString(R.string.error_customer_account_number)
                        binding.edtCustAccNo.showAlignBottom(
                            createBalloon(applicationContext) {
                                setArrowSize(7)
                                setWidthRatio(0.9f)
                                setHeight(45)
                                setArrowPosition(0.03f)
                                setCornerRadius(4f)
                                setAlpha(0.9f)
                                setText(getString(R.string.error_customer_account_number))
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
                    else if (binding.edtCustAccNo.text?.trim().toString().length>18 || binding.edtCustAccNo.text?.trim().toString().length<9){
//                binding.edtCustAccNo.error=getString(R.string.error_customer_account_number_validation)
                        binding.edtCustAccNo.showAlignBottom(
                            createBalloon(applicationContext) {
                                setArrowSize(7)
                                setWidthRatio(0.9f)
                                setHeight(45)
                                setArrowPosition(0.03f)
                                setCornerRadius(4f)
                                setAlpha(0.9f)
                                setText(getString(R.string.error_customer_account_number_validation))
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
                    else if (binding.spnAccType.text?.trim().toString() == ""){
//                binding.spnAccType.error=getString(R.string.error_account_type)
                        binding.spnAccType.showAlignBottom(
                            createBalloon(applicationContext) {
                                setArrowSize(7)
                                setWidthRatio(0.9f)
                                setHeight(45)
                                setArrowPosition(0.03f)
                                setCornerRadius(4f)
                                setAlpha(0.9f)
                                setText(getString(R.string.error_account_type))
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
                    else if (binding.spnCategory.text?.trim().toString() == ""){
                        binding.spnCategory.error=getString(R.string.error_category)
                    }
                    else if (binding.spnFreq.text?.trim().toString() == ""){
                        binding.spnFreq.error=getString(R.string.error_frequency)
                    }
                    else {
                        binding.accountDetails.visibility = View.GONE
                        binding.mandateDetails.visibility = View.VISIBLE
                        position = 2
                        binding.stepView.done(false)
                        binding.stepView.go(position, true)
                        binding.btnNext.text = "NACH GENERATE"
                        binding.btnBack.visibility = View.VISIBLE
                        binding.btnBack.text = "BACK"
                    }

                }
                else -> {
                    if (binding.edtAchAmount.text?.trim().toString() == "") {
//                binding.edtAchAmount.error = getString(R.string.error_ach_amount)
                        binding.edtAchAmount.showAlignBottom(
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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

                    }
                    else{
                        val loginRequest= Usser(
                            binding.edtBenefName.text.toString(),
                            binding.edtCustAccNo.text.toString(),
                            arrayAdapterAccType.getItem(position).toString(),// acc type drawer id
                            "2",
                            binding.edtAchAmount.text?.trim().toString(),
                            arrayAdapter.getItem(position).toString(),  // freq type id value
                            "2",  //1 for fixed, 2 for var
                            binding.edtCustBank.text.toString(),
                            binding.edtCustId.text.toString(),
                            "2",
                            binding.edtCustEmail.text.toString(),
                            binding.edtEndDate.text?.trim().toString(),
                            "",
                            binding.edtIfscCode.text.toString(),
                            "1",
                            binding.edtCustId.text.toString(),
                            binding.edtMandateDate.text?.trim().toString(),
                            "1",
                            binding.edtAchAmount.text?.trim().toString(),
                            binding.edtCustMob.text.toString(),
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
                        var intent = Intent(this,DashBoardActivity::class.java)
                        startActivity(intent)
//                    binding.mandateDetails.visibility = View.GONE
//                    binding.personalDetails.visibility = View.VISIBLE
//                    position = 0
//                    binding.stepView.done(false)
//                    binding.stepView.go(position, true)
//                    binding.btnNext.text = "NEXT"
//                    binding.btnBack.visibility = View.GONE

                    }
                }
            }
        }

        binding.btnBack.setOnClickListener{
            when(position){
                1->{
                    binding.personalDetails.visibility = View.VISIBLE
                    binding.accountDetails.visibility = View.GONE
                    position = 0
                    binding.stepView.done(false)
                    binding.stepView.go(position, true)
                    binding.btnNext.text = "Next"
                    binding.btnBack.visibility = View.GONE
                }
                2 -> {
                    binding.accountDetails.visibility = View.VISIBLE
                    binding.mandateDetails.visibility = View.GONE
                    position = 1
                    binding.stepView.done(false)
                    binding.stepView.go(position, true)
                    binding.btnNext.text = "Next"
                    binding.btnBack.text = "Back"
                }
            }
        }
    }


    override fun onBackPressed() {
        when (position) {
            0 -> {
                val builder = AlertDialog.Builder(this)
                //set title for alert dialog
                builder.setTitle(R.string.dialogTitle)
                //set message for alert dialog
                builder.setMessage(R.string.message)

                //performing positive action
                builder.setPositiveButton("Yes") { dialogInterface, which ->
                    val intent = Intent(this, DashBoardActivity::class.java)
                    startActivity(intent)
                }
                //performing negative action
                builder.setNegativeButton("Cancel") { dialogInterface, which ->
                    val intent = Intent(this, GreenMenuActivity::class.java)
                    startActivity(intent)
                }
                // Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                // Set other dialog properties
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
            1 -> {
                binding.personalDetails.visibility = View.VISIBLE
                binding.accountDetails.visibility = View.GONE
                position = 0
                binding.stepView.done(false)
                binding.stepView.go(position, true)
                binding.btnNext.text = "Next"
                binding.btnBack.visibility = View.GONE

            }
            2 -> {
                binding.accountDetails.visibility = View.VISIBLE
                binding.mandateDetails.visibility = View.GONE
                position = 1
                binding.stepView.done(false)
                binding.stepView.go(position, true)
                binding.btnNext.text = "Next"
                binding.btnBack.text = "Back"
            }
        }
    }
}


//    override fun nextPage() {
//        if (binding.viewPager.currentItem < binding.viewPager.adapter!!.count) {
//            binding.viewPager.currentItem = binding.viewPager.currentItem + 1
//        }
//    }
//
//    override fun previousPage() {
//        if (binding.viewPager.currentItem > 0) {
//            binding.viewPager.currentItem = binding.viewPager.currentItem - 1
//        }
//    }
//
//    override fun navigateToPage(page: Int) {
//        binding.viewPager.currentItem = page
//    }
//
//    override fun cancelNavigation() {
//        finish()
//    }
//
//    override fun currentPage(): Int = binding.viewPager.currentItem
//}
