package com.example.accountmanager.account

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.accountmanager.database.Account
import com.example.accountmanager.database.AccountRepository

class AccountViewModel(app:Application):AndroidViewModel(app) {


    var counter = 1
    val context = app.applicationContext
    val numberAccount = MutableLiveData<Int>(0)
//    val size = MutableLiveData<Int>(1)

    private lateinit var  accountList : List<Account>
    var account  : LiveData<Account>?
    var enableNextBtn = MutableLiveData(true)
    var enableBackBtn = MutableLiveData(false)

    lateinit var AllAccountLiveData:LiveData<List<Account>>

    init{
        AccountRepository.initDB(app.applicationContext)
        accountList = AccountRepository.getAccounts()
//        size.value= accountList.size
        account= AccountRepository.getAccountLiveData(numberAccount)
//        account.value = accountList[0]

        AllAccountLiveData = AccountRepository.getAllAccountLiveData()
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
        AllAccountLiveData = AccountRepository.getAllAccountLiveData()
        enableBackBtn.value=true
        numberAccount.value = numberAccount.value?.plus(1)
        account = AccountRepository.getAccountLiveData(numberAccount)
        Toast.makeText(context,"next",Toast.LENGTH_SHORT).show()
        if (numberAccount.value == accountList.size){
            enableNextBtn.value = false
        }

    }

    fun prevAccount() {
        counter--
        enableNextBtn.value = true
        numberAccount.value = numberAccount.value?.minus(1)
        account = AccountRepository.getAccountLiveData(numberAccount)
        Toast.makeText(context,"back",Toast.LENGTH_SHORT).show()
        if (numberAccount.value == 1){
            enableBackBtn.value = false
        }
    }

//    fun update(account: Account){
//        AccountRepository.updateAccount(account)
//    }

}