package com.example.accountmanager.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface AccountDao {

    @Query("SELECT * FROM Account")
    fun getAllAccount() : List<Account>

    @Query("SELECT * FROM Account")
    fun getAllAccountLiveData() : LiveData<List<Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg  account: Account)

    @Delete
    fun delete(account: Account)

    @Delete
    fun deleteAll(list: List<Account>)

    @Query("SELECT * FROM Account WHERE number = :n LIMIT 1")
    fun getAccountLiveData( n : Int?) : LiveData<Account>?


    @Query("SELECT * FROM Account WHERE number = :n LIMIT 1")
    fun getAccount( n : Int) : Account

    @Query("SELECT * FROM Account WHERE cartNumber = :cartNumber LIMIT 1")
    fun getAccountByCartNumber(cartNumber :String) :Account

//    @Update
//    fun update(account: Account)
}