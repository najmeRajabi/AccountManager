package com.example.accountmanager.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.accountmanager.R
import com.example.accountmanager.databinding.FragmentEditProfileBinding
const val NAME = "name"
const val FADERNAME = "faderName"
const val POSTCODE = "postcode"
const val PHONE = "phoneNumber"

class EditProfileFragment : Fragment() {
    val viewModel: ProfileViewModel by activityViewModels()
    lateinit var binding: FragmentEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         binding.registerBtn.setOnClickListener {
             if (checkFields()) {
                 saveInSharedPref()
                 findNavController().navigate(R.id.action_editProfileFragment_to_showProfileFragment)
             }
         }
    }

    private fun saveInSharedPref() {
//        viewModel.saveInSharedPref(NAME,binding.nameEdt.text.toString())
//        viewModel.saveInSharedPref(FADERNAME, binding.fatherNameEdt.text.toString())
//        viewModel.saveInSharedPref(POSTCODE, binding.postcodeEdt.text.toString())
//        viewModel.saveInSharedPref(PHONE, binding.phoneNumberEdt.text.toString())
        val shardPref = activity?.getSharedPreferences("HW13Profile", Context.MODE_PRIVATE)
        val editor = shardPref?.edit()?.apply {
            putString(NAME , binding.nameEdt.text.toString())
            putString(FADERNAME, binding.fatherNameEdt.text.toString())
            putString(POSTCODE, binding.postcodeEdt.text.toString())
            putString(PHONE, binding.phoneNumberEdt.text.toString())
            apply()
        }
    }

    private fun checkFields(): Boolean {
        var result = false
        if (binding.nameEdt.text.isNullOrBlank()){
            binding.nameEdt.error = getString(R.string.messageError)
        }
        else if (binding.fatherNameEdt.text.isNullOrBlank()){
            binding.fatherNameEdt.error = getString(R.string.messageError)
        }
        else if (binding.postcodeEdt.text.isNullOrBlank()) {
            binding.postcodeEdt.error = getString(R.string.messageError)
        }else if (!binding.postcodeEdt.text?.isDigitsOnly()!!
            || binding.postcodeEdt.text!!.length != 9){
            binding.postcodeEdt.error = getString(R.string.incorrectMessageError)
        }
        else if (binding.phoneNumberEdt.text.isNullOrBlank()){
            binding.phoneNumberEdt.error = getString(R.string.messageError)
        }else if (!binding.phoneNumberEdt.text?.isDigitsOnly()!!
            || binding.phoneNumberEdt.text!!.length != 11){
            binding.phoneNumberEdt.error = getString(R.string.incorrectMessageError)
        }
        else{
            result = true
        }
        return result
    }
}