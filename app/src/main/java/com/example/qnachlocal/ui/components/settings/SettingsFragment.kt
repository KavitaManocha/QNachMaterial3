package com.example.qnachlocal.ui.components.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qnachlocal.ItemsViewModel
import com.example.qnachlocal.MainActivity
import com.example.qnachlocal.R
import com.example.qnachlocal.data.local.SessionManager
import com.example.qnachlocal.databinding.FragmentSettingsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mAdapter: SettingAdapter
    private lateinit var lManager: LinearLayoutManager
    var mList: ArrayList<ItemsViewModel>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).visibility=View.VISIBLE

        mList?.clear()

        lManager = LinearLayoutManager(requireContext())
        binding.recyclerviewSetting.layoutManager = lManager
        mList?.add(ItemsViewModel(R.drawable.ic_baseline_account_circle_24, "My Account"))
        mList?.add(ItemsViewModel(R.drawable.ic_baseline_info_24, "About Us"))
        mList?.add(ItemsViewModel(R.drawable.ic_baseline_contacts_24, "Contact Us"))
        mList?.add(ItemsViewModel(R.drawable.ic_baseline_help_24, "HelpDesk"))

        mAdapter = SettingAdapter(mList!!)
        binding.recyclerviewSetting.adapter = mAdapter

        mAdapter.setOnItemClickListener(object: SettingAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@SettingsFragment.requireActivity(), "You clicked $position", Toast.LENGTH_SHORT).show()
                if(mList?.get(position)?.text=="My Account"){
                    findNavController().navigate(R.id.action_settingsFragment2_to_myAccountFragment2)
                }
                if(mList?.get(position)?.text=="About Us"){
                    findNavController().navigate(R.id.action_settingsFragment2_to_aboutUsFragment2)
                }
                if(mList?.get(position)?.text=="Contact Us"){
                    findNavController().navigate(R.id.action_settingsFragment2_to_contactUsFragment2)
                }
                if(mList?.get(position)?.text=="HelpDesk"){
                    findNavController().navigate(R.id.action_settingsFragment2_to_helpDeskFragment2)
                }
            }

        })

//        val recyclerview = root.findViewById<RecyclerView>(R.id.recyclerview_setting)
//
//        val data = ArrayList<ItemsViewModel>()
//
//
//        data.add(ItemsViewModel(R.drawable.ic_gray_account, "My Account"))
//        data.add(ItemsViewModel(R.drawable.ic_gray_about_us, "About Us"))
//        data.add(ItemsViewModel(R.drawable.ic_contact_us_gray_mail, "Contact Us"))
//        data.add(ItemsViewModel(R.drawable.ic_help_round_button_gray, "HelpDesk"))
//
//
//        // This will pass the ArrayList to our Adapter
//        val adapter = SettingAdapter(data)
//
//        // Setting the Adapter with the recyclerview
//        recyclerview.adapter = adapter


//        val textView: TextView = binding.textNotifications
//        settingsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        binding.tvSignOut.setOnClickListener {
            val builder = AlertDialog.Builder(this@SettingsFragment.requireActivity())
            builder.setTitle("Sign Out")
            builder.setMessage("Do you really want to Sign Out?")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                // Toast.makeText(this@NotificationsFragment.requireActivity(),
                //     android.R.string.yes, Toast.LENGTH_SHORT).show()
                val sessionManager = SessionManager(requireContext())
                sessionManager.logOut()
                val intent= Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finishAffinity()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                //Toast.makeText(this@NotificationsFragment.requireActivity(),
                //    android.R.string.no, Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}