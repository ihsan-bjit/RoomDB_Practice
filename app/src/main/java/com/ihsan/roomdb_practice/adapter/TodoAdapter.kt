package com.ihsan.roomdb_practice.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ihsan.roomdb_practice.R
import com.ihsan.roomdb_practice.model.Todo
import com.ihsan.roomdb_practice.ui.ViewTodoFragmentDirections
import com.ihsan.roomdb_practice.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.list_task.view.*
import java.time.LocalDateTime

class TodoAdapter(val context: Context, private val viewModel: TodoViewModel) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todoList: List<Todo>? = viewModel.readAllTodo.value

    class TodoViewHolder(view: View) : ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_task, parent, false)
        return TodoViewHolder(root)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentData = todoList?.get(position)
        holder.itemView.title_tv.text = "${currentData?.title.toString()}"
        holder.itemView.desc_tv.text = "${currentData?.desc.toString()}"
        val date= "${currentData?.dueTime.toString()}"
        val splitDate=date.split("-").reversed()
        holder.itemView.due_time_tv.text ="${splitDate.joinToString("-")}"
        holder.itemView.setOnLongClickListener{

            val action= currentData?.let { it1 ->
                ViewTodoFragmentDirections.actionViewTodoFragmentToUpdateFragment(
                    it1
                )
            }
            if (action != null) {
                holder.itemView.findNavController().navigate(action)
            }
            true
        }
        holder.itemView.update_button.setOnClickListener {
            val action= currentData?.let { it1 ->
                ViewTodoFragmentDirections.actionViewTodoFragmentToAttachmentFragment(
                    it1
                )
            }
            if (action != null) {
                holder.itemView.findNavController().navigate(action)
            }
        }
        holder.itemView.complete_button.setOnClickListener {
            if (currentData != null) {
                currentData.status="right-todo"
                viewModel.updateTodo(currentData)
            }
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return todoList?.size ?: 0
    }

    fun setData(todo: List<Todo>) {
        this.todoList = todo
        notifyDataSetChanged()
    }

    private fun deleteTodo(todo: Todo) {
        if (todo.id != null) {
            viewModel.deleteTodo(todo)
        }
    }
}


