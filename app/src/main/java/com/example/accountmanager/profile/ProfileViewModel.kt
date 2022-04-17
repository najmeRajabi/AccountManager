package com.example.accountmanager.profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel


class ProfileViewModel(app:Application):AndroidViewModel(app) {

    val context = app.applicationContext

    fun saveInSharedPref(name:String , value:String){
        val sharedPref = context
            .getSharedPreferences("HW13Profile", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()?.apply {
            putString(name,value)
            apply()
        }
    }
    fun getFromSharedPref(name: String): String? {
        val sharedPref = context
            .getSharedPreferences("HW13Profile", Context.MODE_PRIVATE)
        return sharedPref.getString(name,"empty")
    }

    fun checkRegistered(): Boolean {
        val sharedPref = context
            .getSharedPreferences("HW13Profile", Context.MODE_PRIVATE)
        return sharedPref.getString(NAME,"empty") != "empty"
    }
}