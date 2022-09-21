package com.example.qnachlocal.ui.components.greenmenuflow.mandatedetails

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qnachlocal.R
import com.example.qnachlocal.SharedViewModel
import com.example.qnachlocal.databinding.FragmentMandateDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

@AndroidEntryPoint
class MandateDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMandateDetailsBinding
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
        val binding: FragmentMandateDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_mandate_details, container, false
        )
        binding.viewModel = viewModel//attach your viewModel to xml

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
            datePickerDialog.show()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_mandateDetailsFragment_to_accountDetailsFragment)
        }

        binding.btnGen.setOnClickListener {
            if (binding.edtAchAmount.text?.trim().toString() == "") {
                binding.edtAchAmount.error = "Enter Ach Amount"
            } else if (binding.edtMandateDate.text?.trim().toString() == "") {
                binding.edtMandateDate.error = "Enter Mandate Date"
            } else if (binding.edtStartDate.text?.trim().toString() == "") {
                binding.edtStartDate.error = "Enter Start Date"
            } else if (binding.edtEndDate.text?.trim().toString() == "") {
                binding.edtEndDate.error = "Enter End Date"
            } else if (binding.edtReferenceNo.text?.trim().toString() == "") {
                binding.edtReferenceNo.error = "Enter Reference Number"
            } else {
                findNavController().navigate(R.id.action_mandateDetailsFragment_to_accountDetailsFragment)
            }
        }
        return binding.root
    }


}