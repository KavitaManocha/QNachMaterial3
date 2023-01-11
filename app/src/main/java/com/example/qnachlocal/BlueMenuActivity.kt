package com.example.qnachlocal

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.leandroferreira.wizard_forms.contract.WizardNavigator
import com.example.qnachlocal.data.Usser
import com.example.qnachlocal.databinding.ActivityBlueMenuBinding
import com.example.qnachlocal.databinding.ActivityMainBinding
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.showAlignBottom
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.regex.Pattern

@AndroidEntryPoint
class BlueMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlueMenuBinding
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
        binding = ActivityBlueMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.getChildAt(0).setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(applicationContext,DashBoardActivity::class.java)
                startActivity(intent)
            }

        })

        binding.stepView.done(false)

        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

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
                this,
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
                this,
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

        val frequency = resources.getStringArray(R.array.frequency)
        val arrayAdapter = ArrayAdapter(applicationContext,R.layout.dropdown_item,frequency)
        binding.spnFrequency.setAdapter(arrayAdapter)

        val category = resources.getStringArray(R.array.category)
        val arrayAdapterCategory = ArrayAdapter(applicationContext,R.layout.dropdown_item,category)
        binding.spnCategoryy.setAdapter(arrayAdapterCategory)

        val bank = resources.getStringArray(R.array.bank)
        val arrayAdapterAccType = ArrayAdapter(applicationContext,R.layout.dropdown_item,bank)
        binding.spnSelectBank.setAdapter(arrayAdapterAccType)

        binding.btnNext.setOnClickListener {
            when (position) {
                0 -> {
                    if (binding.edtCustomerId.text?.trim().toString() ==""){
//                binding.edtCustomerId.error="Enter Loan Id"
                        binding.edtCustomerId.showAlignBottom(
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
                    else if (binding.edtMobileNumber.text?.trim().toString() == ""){
//                binding.edtMobileNumber.error="Enter Mobile Number"
                        binding.edtMobileNumber.showAlignBottom(
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
                    else if (binding.edtMobileNumber.text?.trim().toString().length<10 || binding.edtMobileNumber.text?.trim().toString().length>10){
//                binding.edtMobileNumber.error="Enter Valid Mobile Number"
                        binding.edtMobileNumber.showAlignBottom(
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
                    else if (binding.edtEmail.text?.trim().toString() == ""){
//                binding.edtEmail.error="Enter Email Id"
                        binding.edtEmail.showAlignBottom(
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
                    else if (!EMAIL_ADDRESS_PATTERN.matcher(binding.edtEmail.text?.trim().toString()).matches()){
//                binding.edtEmail.error="Enter Valid Email Id"
                        binding.edtEmail.showAlignBottom(
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
                    else if (binding.edtAchAmount.text?.trim().toString() == ""){
//                binding.edtAchAmount.error="Enter Ach Amount"
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
                    else if (binding.edtFirstCollDate.text?.trim().toString() == ""){
//                binding.edtFirstCollDate.error="Select First Collection Date"
                        binding.edtFirstCollDate.showAlignBottom(
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                        binding.customerDetails.visibility = View.GONE
                        binding.bankDetail.visibility = View.VISIBLE
                        position = 1
                        binding.stepView.done(false)
                        binding.stepView.go(position, true)
                        binding.btnNext.text = "NEXT"
                        binding.btnBack.visibility = View.VISIBLE
                        binding.btnBack.text = "BACK"
                    }
                }
                else -> {
                    if (binding.edtAccHolderName.text?.trim().toString() ==""){
//                binding.edtAccHolderName.error="Enter Account Holder Name"
                        binding.edtAccHolderName.showAlignBottom(
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                            createBalloon(applicationContext) {
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
                        binding.customerDetails.visibility = View.VISIBLE
                        binding.bankDetail.visibility = View.GONE
                        position = 0
                        binding.stepView.done(false)
                        binding.stepView.go(position, true)
                        binding.btnNext.text = "NEXT"
                        binding.btnBack.visibility = View.GONE
                        binding.btnBack.text = "BACK"
//                        val loginRequest=
//                            Usser(
//                                binding.edtAccHolderName.text?.trim().toString(),
//                                binding.edtCustAccNo.text?.trim().toString(),
//                                arrayAdapterAccType.getItem(position).toString(),
//                                "2",
//                                binding.edtAchAmount.text?.trim().toString(),
//                                arrayAdapter.getItem(position).toString(),
//                                "2",
//                                binding.spnSelectBank.text?.trim().toString(),
//                                binding.edtCustomerId.text?.trim().toString(),
//                                "2",
//                                binding.edtEmail.text?.trim().toString(),
//                                binding.edtFinalCollDate.text?.trim().toString(),
//                                "",
//                                "SBIN0002772",
//                                "1",
//                                loan_id,
//                                ,
//                                "1",
//                                ach_amt,
//                                cust_mob,
//                                "",
//                                "2",
//                                "",
//                                "",
//                                "4",
//                                first_coll_date,
//                                "1",
//                                "440"
//                            )
//                            binding.edtBenefName.text.toString(),
//                            binding.edtCustAccNo.text.toString(),
//                            arrayAdapterAccType.getItem(position).toString(),// acc type drawer id
//                            "2",
//                            binding.edtAchAmount.text?.trim().toString(),
//                            arrayAdapter.getItem(position).toString(),  // freq type id value
//                            "2",  //1 for fixed, 2 for var
//                            binding.edtCustBank.text.toString(),
//                            binding.edtCustId.text.toString(),
//                            "2",
//                            binding.edtCustEmail.text.toString(),
//                            binding.edtEndDate.text?.trim().toString(),
//                            "",
//                            binding.edtIfscCode.text.toString(),
//                            "1",
//                            binding.edtCustId.text.toString(),
//                            binding.edtMandateDate.text?.trim().toString(),
//                            "1",
//                            binding.edtAchAmount.text?.trim().toString(),
//                            binding.edtCustMob.text.toString(),
//                            "",
//                            "2",
//                            binding.edtReferenceNo.text?.trim().toString(),
//                            "",
//                            "4",
//                            binding.edtStartDate.text?.trim().toString(),
//                            "1",
//                            "440"
//                        )
                        //Usser(loan_id,benef_name,
//                    cust_mob.toString(),cust_email.toString(),
//                    ifsc_code,cust_bank,cust_bank_add,cust_acc_no,acc_type,
//                    category,freq,binding.edtAchAmount.text?.trim().toString(),
//                    binding.edtMandateDate.text?.trim().toString(),
//                    binding.edtStartDate.text?.trim().toString(),
//                    binding.edtEndDate.text?.trim().toString(),
//                    binding.edtReferenceNo.text?.trim().toString() )
//                        viewModel.genpdf(loginRequest)
//                        var intent = Intent(this,DashBoardActivity::class.java)
//                        startActivity(intent)
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
                    binding.customerDetails.visibility = View.VISIBLE
                    binding.bankDetail.visibility = View.GONE
                    position = 0
                    binding.stepView.done(false)
                    binding.stepView.go(position, true)
                    binding.btnNext.text = "Next"
                    binding.btnBack.visibility = View.GONE
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
                binding.customerDetails.visibility = View.VISIBLE
                binding.bankDetail.visibility = View.GONE
                position = 0
                binding.stepView.done(false)
                binding.stepView.go(position, true)
                binding.btnNext.text = "Next"
                binding.btnBack.visibility = View.GONE

            }
        }
    }
}