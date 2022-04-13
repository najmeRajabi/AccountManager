package com.example.accountmanager.database

import android.content.Context
import androidx.lifecycle.LiveData

object AccountRepository {

    var db : AppDatabase? = null
    var accountDao :AccountDao? = null

    fun initDB(context : Context){
        db = AppDatabase.getAppDatabase(context)

        accountDao = db?.accountDao()

        addTestData()
    }

    private fun addTestData() {
        accountDao?.insertAll(
            Account("1","1","1"),
            Account("2","2","2"),

            )
    }

    fun getAccounts() : List<Account>{
        return db!!.accountDao().getAllAccount()
    }

    fun getAccountLiveData() : LiveData<Account>
    {
        return db!!.accountDao().getAccountLiveData(1)
    }

    fun getAccount() : Account?
    {
        return db!!.accountDao().getAccount(1)
    }

    fun deleteAll(list: List<Account>){
        db?.accountDao()?.deleteAll(list)
    }

    fun delete(account: Account){
        db?.accountDao()?.delete(account)
    }

    fun insertAccount(account: Account){
        db?.accountDao()?.insertAll(account)
    }

//    fun updateAccount(account: Account){
//        db?.accountDao()?.update(account)
//    }
}