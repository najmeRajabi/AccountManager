package com.example.accountmanager.account

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.accountmanager.database.Account
import com.example.accountmanager.database.AccountRepository

class AccountViewModel(app:Application):AndroidViewModel(app) {


    var counter = 1
    val context = app.applicationContext
    val numberAccount = MutableLiveData<Int>(1)
//    val size = MutableLiveData<Int>(1)

    private lateinit var  accountList : List<Account>
    lateinit var account  : LiveData<Account>
    var enableNextBtn = MutableLiveData(true)
    var enableBackBtn = MutableLiveData(false)

    lateinit var allAccountLiveData:LiveData<List<Account>>

    init{
        AccountRepository.initDB(context)
        accountList = AccountRepository.getAccounts()
//        size.value= accountList.size
        account= numberAccount.value?.let { AccountRepository.getAccountLiveData(it) }!!
        Log.d("TAG", "vm: ${account.value?.stock}")
//        account.value = accountList[0]

        allAccountLiveData = AccountRepository.getAllAccountLiveData()
//        account = numberAccount.value?.let { allAccountLiveData.value?.get(it) }
    }

    fun insert(account: Account){
        AccountRepository.insertAccount(account)
    }

    fun delete(account: Account){
        AccountRepository.delete(account)
    }

    fun deleteAll(){

            AccountRepository.deleteAll(AccountRepository.getAccounts())

    }

    fun nextAccount() {
        counter++
        allAccountLiveData = AccountRepository.getAllAccountLiveData()
        enableBackBtn.value=true
        numberAccount.value = numberAccount.value?.plus(1)
        accountList = AccountRepository.getAccounts()
        account = numberAccount.value?.let { AccountRepository.getAccountLiveData(it) }!!
        Log.d("TAG", "vm: ${account.value?.stock}")
        Toast.makeText(context,"next ${numberAccount.value}",Toast.LENGTH_SHORT).show()
        if (numberAccount.value == accountList.size){
            enableNextBtn.value = false
        }

    }

    fun prevAccount() {
        counter--
        enableNextBtn.value = true
        numberAccount.value = numberAccount.value?.minus(1)
        accountList = AccountRepository.getAccounts()
        account = numberAccount.value?.let { AccountRepository.getAccountLiveData(it) }!!
        Log.d("TAG", "vm: ${account.value?.stock}")
        Toast.makeText(context,"back",Toast.LENGTH_SHORT).show()
        if (numberAccount.value == 1){
            enableBackBtn.value = false
        }
    }

//    fun update(account: Account){
//        AccountRepository.updateAccount(account)
//    }

}