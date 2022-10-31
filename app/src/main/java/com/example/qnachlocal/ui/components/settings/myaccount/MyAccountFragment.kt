package com.example.qnachlocal.ui.components.settings.myaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.qnachlocal.R
import com.example.qnachlocal.data.local.SessionManager
import com.example.qnachlocal.databinding.FragmentMyAccountBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAccountFragment : Fragment() {

    private var _binding: FragmentMyAccountBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyAccountBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)?.getSupportActionBar()?.setTitle("My Account")
//        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.GONE

        binding.toolbar.getChildAt(0).setOnClickListener {
            fragmentManager?.popBackStack()
        }

        val sessionManager = SessionManager(requireContext())
//        val loginResponse= sessionManager.getUserDetail()?.data
//
//        binding.tvNupayDemo.text = loginResponse?.name
//
//        binding.tvLocation.text = loginResponse?.location
//
//        binding.noOfMobNo.text =loginResponse?.mobile
//
//        binding.tvCompany.text = loginResponse?.org_name
//
//        binding.tvMail.text = loginResponse?.user_email
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}