package com.ihsan.roomdb_practice.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ihsan.roomdb_practice.R
import com.ihsan.roomdb_practice.model.Todo
import com.ihsan.roomdb_practice.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {

    private lateinit var viewModel: TodoViewModel
    private val args: UpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = 0
        if (args.todo != null) {
            val todo = args.todo
            id = todo!!.id
            val date = todo.dueTime!!.split("-")
            title_et_edit.setText(todo.title)
            desc_et_edit.setText(todo.desc)
            //date_dp.updateDate(date[0].toInt(), date[1].toInt() - 1, date[2].toInt())
            Log.d("TAG", "onViewCreated: ${date[0].toInt()}, ${date[1].toInt()- 1} , ${date[2].toInt()}")

            viewModel = ViewModelProvider(this)[TodoViewModel::class.java]
            update_button.setOnClickListener {
                val title = title_et_edit.text.toString()
                val desc = desc_et_edit.text.toString()
                updateTodo(id, title, desc)
                findNavController().navigate(R.id.action_updateFragment_to_viewTodoFragment)
            }
        }
    }

    private fun updateTodo(id: Int, title: String, desc: String) {
        val todo = Todo(
            id,
            title,
            desc,
            args.todo!!.dueTime,
            args.todo!!.completedDate,
            args.todo!!.imagesId,
            args.todo!!.status
        )
        viewModel.updateTodo(todo)
    }
}