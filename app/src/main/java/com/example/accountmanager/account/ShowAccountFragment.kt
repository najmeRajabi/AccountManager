package com.example.accountmanager.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.accountmanager.R
import com.example.accountmanager.databinding.FragmentShowAccountBinding
import com.google.android.material.snackbar.Snackbar

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
        binding.backAccountBtn.setOnClickListener {
            vModel.prevAccount()
        }
    }

    private fun initViews() {
//
//        vModel.AllAccountLiveData.observe(requireActivity()){
//            for (item in it){
//                Log.d("TAG", "initViews: ${item.cartNumber}+${vModel.counter}")
//
//            }
//           val accountFilter=it.find { it.number == vModel.counter  }
//            binding.txvAccountNumber.text = accountFilter?.cartNumber
//            binding.txvAccountStock.text = accountFilter?.stock
//            binding.txvAccountType.text = accountFilter?.AccountType
//        }


        if (vModel.account == null){

            Toast.makeText(activity,"no data yet",Toast.LENGTH_SHORT).show()

        }else {

            activity?.let { activity ->
                vModel.account!!.observe(activity) {
                    binding.txvAccountNumber.text = it.cartNumber
                    binding.txvAccountStock.text = it.stock
                    binding.txvAccountType.text = it.AccountType
                }
            }

            vModel.enableNextBtn.observe(requireActivity()){
                binding.nextAccountBtn.isEnabled = it
            }
            vModel.enableBackBtn.observe(requireActivity()){
                binding.backAccountBtn.isEnabled = it
            }
        }
    }

}