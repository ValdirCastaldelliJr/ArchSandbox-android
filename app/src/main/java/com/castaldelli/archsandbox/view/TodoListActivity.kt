package com.castaldelli.archsandbox.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.castaldelli.archsandbox.R


import com.castaldelli.archsandbox.core.CoreActivity
import com.castaldelli.archsandbox.databinding.ActivityTodoBinding
import com.castaldelli.archsandbox.repository.database.entity.Task
import com.castaldelli.archsandbox.view.adapter.TaskAdapter
import com.castaldelli.archsandbox.viewmodel.TodoListViewModel
import kotlinx.android.synthetic.main.activity_todo.*


class TodoListActivity : CoreActivity() {

    private lateinit var viewModel: TodoListViewModel
    private lateinit var taskList: List<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TodoListViewModel::class.java)

        DataBindingUtil.setContentView<ActivityTodoBinding>(this@TodoListActivity, R.layout.activity_todo).apply {
            this.lifecycleOwner = this@TodoListActivity
            this.vm = viewModel
        }

        viewModel.getAllTasks().observe(this, Observer {
            if (it != null && it.isNotEmpty()) {

                Toast.makeText(this, "To Do List updated.", Toast.LENGTH_SHORT).show()

                taskList = it
                task_list.layoutManager = LinearLayoutManager(this)
                task_list.adapter = TaskAdapter(it)
                task_list.smoothScrollToPosition(it.size-1)
                if (it.none { f -> f.selected }) del_task.isEnabled = false
            }
        })

        add_task.setOnClickListener { showAddDialog() }

        del_task.isEnabled = false
        del_task.setOnClickListener {
            val task = taskList.firstOrNull { task -> task.selected }
            if (task != null) {
                viewModel.delTask(task)
            } else {
                Toast.makeText(this, "Ops", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showAddDialog() {
        val editText = EditText(this)
        editText.setTextColor(ContextCompat.getColor(this, R.color.cerulean))
        editText.gravity = Gravity.CENTER
        editText.maxLines = 1
        editText.requestFocus()

        AlertDialog.Builder(this, R.style.AlertDialogCustom)
            .setTitle("Exemplo de Insert")
            .setMessage("Insira um nome para a fruta a ser adicionada a cesta: ")
            .setView(editText)
            .setPositiveButton("Salvar") { _, _ -> viewModel.addTask(editText.text.toString()) }
            .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .create()
            .show()
    }

    fun update(task : Task) { viewModel.updateTask(task) }
    fun enableControls(disable : Boolean) { del_task.isEnabled = disable }
}