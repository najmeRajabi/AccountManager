package com.example.accountmanager.database

import androidx.room.*

@Dao
interface AccountDao {

    @Query("SELECT * FROM Account")
    fun getAllAccount() : List<Account>

    @Insert
    fun insertAll(vararg  account: Account)

    @Delete
    fun delete(account: Account)

    @Delete
    fun deleteAll()

    @Update
    fun update(account: Account)
}