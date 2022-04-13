package com.example.accountmanager.database

import android.content.Context

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

    fun getQuestions() : List<Account>{
        return db!!.accountDao().getAllAccount()
    }

    fun deleteAll(){
        db?.accountDao()?.deleteAll()
    }

    fun delete(account: Account){
        db?.accountDao()?.delete(account)
    }

    fun insertAccount(account: Account){
        db?.accountDao()?.insertAll(account)
    }

    fun updateAccount(account: Account){
        db?.accountDao()?.update(account)
    }
}