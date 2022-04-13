package com.example.accountmanager.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.accountmanager.R
import com.example.accountmanager.databinding.FragmentShowAccountBinding

class ShowAccountFragment : Fragment() {

    lateinit var binding: FragmentShowAccountBinding
    val vModel : AccountViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        binding.nextAccountBtn.setOnClickListener {
            vModel.nextAccount()
        }
    }

    private fun initViews() {

        if (vModel.account == null){
            //todo show empty
        }else {

            vModel.account.observe(requireActivity()) {
                binding.txvAccountNumber.text = it.cartNumber
                binding.txvAccountStock.text = it.stock
                binding.txvAccountType.text = it.AccountType
            }
        }
    }

}