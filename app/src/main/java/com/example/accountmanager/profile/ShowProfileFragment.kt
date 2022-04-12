package com.example.accountmanager.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.accountmanager.R
import com.example.accountmanager.databinding.FragmentShowProfileBinding

class ShowProfileFragment : Fragment() {

    lateinit var binding: FragmentShowProfileBinding
    var name =""
    var faderName = ""
    var postcode = ""
    var phone = ""

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowProfileBinding.inflate(layoutInflater, container, false)


        initViews()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editProfileBtn.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initViews() {

        getFromSharedPref()
        setToViews()
    }

    private fun setToViews() {
        binding.txvName.text = name
        binding.txvFaderName.text = faderName
        binding.txvPostcode.text = postcode
        binding.txvPhone.text = phone
    }

    private fun getFromSharedPref() {
        val shardPref = activity?.getSharedPreferences("HW13Profile", Context.MODE_PRIVATE)

        name = shardPref?.getString(NAME,"name").toString()
        faderName = shardPref?.getString(FADERNAME,"fader").toString()
        postcode = shardPref?.getString(POSTCODE,"postcode").toString()
        phone = shardPref?.getString(PHONE,"phoneNumber").toString()
    }
}