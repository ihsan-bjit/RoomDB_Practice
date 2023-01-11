package com.ihsan.roomdb_practice.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ihsan.roomdb_practice.R
import com.ihsan.roomdb_practice.model.Todo
import com.ihsan.roomdb_practice.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*

class AddTaskFragment : Fragment() {

    private lateinit var viewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        add_button.setOnClickListener {
            addTodo()
        }
    }

    private fun addTodo() {
        val title = title_et.text.toString()
        val desc = description_et.text.toString()
        val datePicker = date_dp
        var dueDate = "${datePicker.year}-${datePicker.month + 1}-${datePicker.dayOfMonth}"

        if (!TextUtils.isEmpty(title)) {
            viewModel.addTodo(Todo(0, title, desc, dueDate, "","","todo"))
            findNavController().navigate(R.id.action_addTaskFragment_to_viewTodoFragment)
        }
    }

}