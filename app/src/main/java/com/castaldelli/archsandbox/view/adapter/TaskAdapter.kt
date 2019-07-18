package com.castaldelli.archsandbox.view.adapter

import android.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.castaldelli.archsandbox.R
import com.castaldelli.archsandbox.repository.database.entity.Task
import com.castaldelli.archsandbox.view.MainActivity
import kotlinx.android.synthetic.main.task_adapter.view.*

class TaskAdapter(private val lista : List<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private lateinit var activity: MainActivity

    override fun getItemCount(): Int = lista.size
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) : ViewHolder {
        if (parent.context is MainActivity) {
            activity = parent.context as MainActivity
        }
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_adapter, parent, false))

    }

    override fun onBindViewHolder(vh: ViewHolder, p: Int) {

        val item = lista[p]
        vh.v.txt_task.text = item.toString()

        if (item.selected) {
            selectItem(vh)
        } else {
            cleanSelection(vh)
        }

        vh.v.container.setOnClickListener {
            val selected = item.selected
            lista.forEach { i -> i.selected = false }
            item.selected = !selected
            activity.enableControls(item.selected)
            notifyDataSetChanged()
        }

        if (item.selected) {
            vh.v.container.setOnLongClickListener {

                val editText = EditText(activity)
                editText.setText(item.description)
                editText.maxLines = 1
                editText.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark))
                editText.gravity = Gravity.CENTER
                editText.requestFocus()

                AlertDialog.Builder(activity, R.style.AlertDialogCustom)
                    .setTitle("Exemplo de Update")
                    .setMessage("Altere o nome do item selecionada: ")
                    .setView(editText)
                    .setPositiveButton("Salvar") { _, _->
                        activity.update(item.apply { description =
                            Regex("[^A-Za-z0-9 ]")
                                .replace(editText.text.toString(), "")
                                .replace("\n", "")
                            }
                        )
                    }
                    .setCancelable(true)
                    .create()
                    .show()

                true
            }
        }
    }

    private fun selectItem(vh: ViewHolder) {
        vh.v.txt_task.setTextColor(ContextCompat.getColor(activity, R.color.white))
        vh.v.container.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary))
    }

    private fun cleanSelection(vh: ViewHolder) {
        vh.v.txt_task.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark))
        vh.v.container.setBackgroundColor(ContextCompat.getColor(activity, R.color.white))
    }

    inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v)

}