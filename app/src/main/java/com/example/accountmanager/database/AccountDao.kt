package com.example.accountmanager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccountDao {

    @Query("SELECT * FROM Account")
    fun getAllAccount() : List<Account>

    @Insert
    fun insertAll(vararg  account: Account)

    @Delete
    fun delete(account: Account)
}