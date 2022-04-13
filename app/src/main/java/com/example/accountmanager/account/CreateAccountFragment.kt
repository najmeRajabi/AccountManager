package com.example.accountmanager.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.accountmanager.R
import com.example.accountmanager.database.Account
import com.example.accountmanager.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : Fragment() {
    lateinit var binding: FragmentCreateAccountBinding
    val vModel :AccountViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        binding.createAccountBtn.setOnClickListener {
            if (checkField()){
                saveToDatabase()
            }
        }

    }

    private fun saveToDatabase() {
        var account = Account(
            binding.accountTypeEdt.text.toString(),
            binding.accountNumberEdt.text.toString(),
            binding.stockEdt.text.toString()
        )
        vModel.insert(account)
    }

    private fun checkField(): Boolean {
        var result = false
        if (binding.accountNumberEdt.text.isNullOrBlank()
            || binding.accountNumberEdt.text!!.length != 13) {
            binding.accountNumberEdt.setError(getString(R.string.enter_correct))
        }else if (binding.stockEdt.text.isNullOrBlank()){
            binding.stockEdt.setError(getString(R.string.enter_correct))
        }else if (binding.accountTypeEdt.text.isNullOrBlank()){
            binding.accountTypeEdt.setError(getString(R.string.choose))
        } else{
            result = true
        }
        return result
    }

    private fun initViews() {
        val items = listOf("قرض الحسنه", "جاری", "بلند مدت", "کوتاه مدت")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_account_type, items)
        val txvAccountType = activity?.findViewById<AutoCompleteTextView>(R.id.accountType_edt)
        txvAccountType?.setAdapter(adapter)
    }

}