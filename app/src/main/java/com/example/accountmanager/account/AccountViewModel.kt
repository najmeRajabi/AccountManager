package com.example.accountmanager.account

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.accountmanager.database.Account
import com.example.accountmanager.database.AccountDao
import com.example.accountmanager.database.AccountRepository
import com.example.accountmanager.database.AppDatabase

class AccountViewModel(app:Application):AndroidViewModel(app) {


    private lateinit var  accountList : List<Account>
    var account  = MutableLiveData<Account> ()
    init{
        AccountRepository.initDB(app.applicationContext)
        accountList = AccountRepository.getQuestions()
        account.value = accountList[0]
    }

    fun insert(account: Account){
        // if account is valid & not exist
        AccountRepository.insertAccount(account)
    }

    fun delete(account: Account){
        AccountRepository.delete(account)
    }

    fun deleteAll(){
        AccountRepository.deleteAll()
    }

    fun update(account: Account){
        AccountRepository.updateAccount(account)
    }

}