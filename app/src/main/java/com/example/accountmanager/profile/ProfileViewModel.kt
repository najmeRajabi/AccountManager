package com.example.accountmanager.profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel


class ProfileViewModel(app:Application):AndroidViewModel(app) {

    fun saveInSharedPref(name:String , value:String){
        val sharedPref = Application()
            .getSharedPreferences("HW13Profile", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()?.apply {
            putString(name,value)
            apply()
        }
    }
}