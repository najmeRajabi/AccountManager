package com.example.accountmanager.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.accountmanager.R
import com.example.accountmanager.databinding.FragmentSelectAccountBinding

class SelectAccountFragment : Fragment() {

    lateinit var binding: FragmentSelectAccountBinding
    val vModel :AccountViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chooseAccountBtn.setOnClickListener {
            if (checkCartNumber()){
                fillField()

            }
        }
    }

    private fun fillField() {
        val account = vModel.getAccountByCartNumber(binding.accountNumberEdtChoose.text.toString())
        if (account == null){
            Toast.makeText(requireContext(),"چنین حسابی پیدا نشد!",Toast.LENGTH_SHORT).show()
        }else{
            binding.llChooseAccountShow.visibility = View.VISIBLE
            binding.txvAccountNumberChoose.text = account.cartNumber
            binding.txvAccountStockChoose.text = account.stock
            binding.txvAccountTypeChoose.text = account.AccountType
        }
    }

    private fun checkCartNumber(): Boolean {
        return if (binding.accountNumberEdtChoose.text.isNullOrBlank()
            || binding.accountNumberEdtChoose.text!!.length != 13){
            binding.accountNumberEdtChoose.error = "لطفا یک شماره حساب معتبر وارد کنید"
            false
        }else{
            true
        }
    }
}