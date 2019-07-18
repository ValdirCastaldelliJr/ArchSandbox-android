package com.castaldelli.archsandbox.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.castaldelli.archsandbox.repository.database.dao.TaskDAO
import com.castaldelli.archsandbox.repository.database.entity.Task

@Database(entities = [Task::class], version = 4, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun taskDAO() : TaskDAO
}