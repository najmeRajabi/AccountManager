package com.example.accountmanager.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object AccountRepository {

    lateinit var db : AppDatabase
    var accountDao :AccountDao? = null

    fun initDB(context : Context){
        db = AppDatabase.getAppDatabase(context)!!

        accountDao = db?.accountDao()

        addTestData()
    }

    private fun addTestData() {
        accountDao?.insertAll(
            Account(1,"11111","13333","122222"),
            Account(2,"2111111","233333","222222"),
            Account(3,"3","3","3"),
            Account(4,"4","4","4")

            )
    }

    fun getAccounts() : List<Account>{
        Log.d("TAG", db!!.accountDao().getAllAccount().size.toString())
        return db!!.accountDao().getAllAccount()
    }

    fun getAccountLiveData(number: MutableLiveData<Int>) : LiveData<Account>?
    {
        if (number.value?.let { accountDao?.getAccountLiveData(3) } == null){
            Log.d("TAG", "getAccountLiveData: ${number.value?.let { accountDao?.getAccountLiveData(it)?.value?.AccountType }} ")
        }else{
            Log.d("TAG", "getAccountLiveData:2 ${ getAccount(3)?.cartNumber } ")
        }
        return number.value?.let { accountDao?.getAccountLiveData(it) }
    }

    fun getAccount(number:Int) : Account?
    {
        return db.accountDao().getAccount(number)
    }

    fun deleteAll(list: List<Account>){
        db?.accountDao().deleteAll(list)
    }

    fun delete(account: Account){
        db?.accountDao()?.delete(account)
    }

    fun insertAccount(account: Account){
        db?.accountDao()?.insertAll(account)
    }

    fun getAllAccountLiveData(): LiveData<List<Account>> {
        return db.accountDao().getAllAccountLiveData()
    }

//    fun updateAccount(account: Account){
//        db?.accountDao()?.update(account)
//    }
}