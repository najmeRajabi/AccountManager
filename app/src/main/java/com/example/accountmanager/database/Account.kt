package com.example.accountmanager.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    val AccountType:String,
    @PrimaryKey val cartNumber: String,
    val stock: String //موجودی
) {
}