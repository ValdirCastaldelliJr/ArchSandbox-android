package com.castaldelli.archsandbox

import android.app.Application
import androidx.room.Room
import com.castaldelli.archsandbox.repository.database.AppDB


class App : Application() {

    companion object {
        lateinit var db : AppDB
        lateinit var instance: App

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(this, AppDB::class.java, "app.db")
            .fallbackToDestructiveMigration()
            .build()


    }
}