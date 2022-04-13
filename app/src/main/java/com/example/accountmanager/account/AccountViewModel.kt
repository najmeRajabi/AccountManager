package com.example.accountmanager.account

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.accountmanager.database.Account
import com.example.accountmanager.database.AccountRepository

class AccountViewModel(app:Application):AndroidViewModel(app) {


    private lateinit var  accountList : List<Account>
    lateinit var account  : LiveData<Account>
    init{
        AccountRepository.initDB(app.applicationContext)
        accountList = AccountRepository.getAccounts()
        account= AccountRepository.getAccountLiveData()
//        account.value = accountList[0]
    }

    fun insert(account: Account){
        AccountRepository.insertAccount(account)
    }

    fun delete(account: Account){
        AccountRepository.delete(account)
    }

    fun deleteAll(){
        AccountRepository.deleteAll(accountList)
    }

//    fun update(account: Account){
//        AccountRepository.updateAccount(account)
//    }

}