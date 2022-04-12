package com.example.accountmanager.database

import androidx.room.Entity

@Entity
data class Account(
    val AccountType:String,
    val cartNumber: String,
    val stock: String //موجودی
) {
}