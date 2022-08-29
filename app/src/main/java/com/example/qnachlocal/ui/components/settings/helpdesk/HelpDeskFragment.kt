package com.example.qnachlocal.ui.components.settings.helpdesk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.qnachlocal.R
import com.example.qnachlocal.databinding.FragmentHelpdeskBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HelpDeskFragment : Fragment() {

    private var _binding: FragmentHelpdeskBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHelpdeskBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)?.getSupportActionBar()?.setTitle("HelpDesk")
//        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
//        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.GONE
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}