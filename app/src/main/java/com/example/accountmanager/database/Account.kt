package com.example.accountmanager.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true)val number :Int,
    val AccountType:String,
    val cartNumber: String,
    val stock: String //موجودی
) {
}