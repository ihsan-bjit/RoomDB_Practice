package com.ihsan.roomdb_practice.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.roomdb_practice.R
import com.ihsan.roomdb_practice.model.Todo
import com.ihsan.roomdb_practice.ui.ViewTodoFragmentDirections
import com.ihsan.roomdb_practice.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.list_task.view.*

class TodoAdapterRight(val context: Context, private val viewModel: TodoViewModel) :
    RecyclerView.Adapter<TodoAdapterRight.TodoViewHolderRight>() {

    private var todoListRight: List<Todo>? = viewModel.readAllTodoRight.value

    class TodoViewHolderRight(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolderRight {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_task, parent, false)
        return TodoViewHolderRight(root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TodoViewHolderRight, position: Int) {
        val currentData = todoListRight?.get(position)
        holder.itemView.title_tv.text = currentData?.title.toString()
        holder.itemView.desc_tv.text = currentData?.desc.toString()
        holder.itemView.due_time_tv.text = "${currentData?.dueTime.toString()}"
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
                deleteTodoRight(currentData)
            }
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return todoListRight?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(todo: List<Todo>) {
        this.todoListRight = todo
        notifyDataSetChanged()
    }

    private fun deleteTodoRight(todo: Todo) {
        viewModel.deleteTodo(todo)
    }
}