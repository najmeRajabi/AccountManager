package com.example.accountmanager.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object AccountRepository {

    var db : AppDatabase? = null
    var accountDao :AccountDao? = null
    lateinit var account : LiveData<Account>

    fun initDB(context : Context){
        db = AppDatabase.getAppDataBase(context)!!

        accountDao = db?.accountDao()

        account = accountDao?.getAccountLiveData(1)!!

        addTestData()
    }

    private fun addTestData() {
        accountDao?.insertAll(
            Account(1,"11111","13333","122222"),
            Account(2,"2111111","233333","222222"),
            Account(3,"3","3","3"),
            Account(4,"4","4444444444444","4")

            )
    }

    fun getAccounts() : List<Account>{
        Log.d("TAG", db!!.accountDao().getAllAccount().size.toString())
        return db!!.accountDao().getAllAccount()
    }

//    fun getAccountLiveData(number: MutableLiveData<Int>) : LiveData<Account>?
//    {
//        if (number.value?.let { accountDao?.getAccountLiveData(number.value!!) } == null){
//            Log.d("TAG", "getAccountLiveData: ${number.value?.let { accountDao?.getAccountLiveData(it)?.value?.AccountType }} ")
//        }else{
//            Log.d("TAG", "getAccountLiveData:2 ${ accountDao?.getAccountLiveData(number.value!!)?.value } ")
//        }
//        return number.value?.let { accountDao?.getAccountLiveData(it) }
//    }
    fun getAccountLiveData(number: Int): LiveData<Account>? {
        Log.d("TAG", "account type ${account.value?.AccountType}")
        return accountDao!!.getAccountLiveData(number)
    }

    fun getAccount(number:Int) : Account?
    {
        return db!!.accountDao().getAccount(number)
    }

    fun deleteAll(list: List<Account>){
        db?.accountDao()!!.deleteAll(list)
    }

    fun delete(account: Account){
        db?.accountDao()?.delete(account)
    }

    fun insertAccount(account: Account){
        db?.accountDao()?.insertAll(account)
    }

    fun getAllAccountLiveData(): LiveData<List<Account>> {
        return db!!.accountDao().getAllAccountLiveData()
    }

    fun getAccountByCartNumber(cartNumber: String): Account? {
        return accountDao?.getAccountByCartNumber(cartNumber)
    }

//    fun updateAccount(account: Account){
//        db?.accountDao()?.update(account)
//    }
}