package com.example.bai1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bai1.databinding.FragmentHomeBinding

class HomeFragment:Fragment() {
    private lateinit var binding :FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.btnB1.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_bt1Fragment) }
        binding.btnB2.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_bt2Fragment) }
        binding.btnB3.setOnClickListener {
            if (Utils.storagePermissionGrant(requireContext())) {
                setupWhenStoragePermissionGranted()
            } else {
                requestStoragePermission()
            }
        }
        return binding.root
    }
    private fun requestStoragePermission() {
        resultLauncher.launch(
            Utils.getStoragePermissions()
        )
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (Utils.storagePermissionGrant(requireContext())
            ) {
                setupWhenStoragePermissionGranted()
            } else {
                Utils.showAlertPermissionNotGrant(binding.root, requireActivity())
            }
        }

    private fun setupWhenStoragePermissionGranted() {
        findNavController().navigate(R.id.action_homeFragment_to_bt3Fragment)
    }
}