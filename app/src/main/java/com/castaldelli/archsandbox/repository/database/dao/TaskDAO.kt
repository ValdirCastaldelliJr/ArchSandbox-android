package com.castaldelli.archsandbox.repository.database.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.castaldelli.archsandbox.repository.database.entity.Task


@Dao
interface TaskDAO {

    @Query("SELECT * FROM task")
    fun allTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg task: Task)

    @Delete
    fun del(task: Task)

    @Update
    fun updateTask(task: Task)

}