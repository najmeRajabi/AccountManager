package com.example.accountmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Account::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun accountDao():AccountDao


    companion object{
        var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context):AppDatabase?{
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "myDB")
                            .allowMainThreadQueries()
                            .build()
                }
            }

            return INSTANCE
        }

        fun destroyDatabase(){
            INSTANCE = null
        }
    }
}