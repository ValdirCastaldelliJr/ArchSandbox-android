package com.castaldelli.archsandbox.viewmodel

import android.os.AsyncTask.execute
import androidx.lifecycle.LiveData
import com.castaldelli.archsandbox.App
import com.castaldelli.archsandbox.core.CoreViewModel
import com.castaldelli.archsandbox.repository.database.dao.TaskDAO
import com.castaldelli.archsandbox.repository.database.entity.Task

class TodoListViewModel : CoreViewModel() {

    private val dao : TaskDAO by lazy { App.db.taskDAO() }

    fun getAllTasks(): LiveData<List<Task>> = dao.allTasks()

    fun addTask(task: String) {
        if (task.isNotEmpty()) execute { dao.add(Task(description = task)) }
    }

    fun delTask(task: Task) { execute { dao.del(task) } }

    fun updateTask(task: Task) { execute { dao.updateTask(task) } }

}

