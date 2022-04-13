package com.example.accountmanager.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.accountmanager.R
import com.example.accountmanager.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : Fragment() {
    lateinit var binding: FragmentCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf("قرض الحسنه", "جاری", "بلند مدت", "کوتاه مدت")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_account_type, items)
        (binding.accountTypeEdt as? AutoCompleteTextView)?.setAdapter(adapter)
    }

}