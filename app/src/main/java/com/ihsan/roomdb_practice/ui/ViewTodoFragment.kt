package com.ihsan.roomdb_practice.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihsan.roomdb_practice.R
import com.ihsan.roomdb_practice.adapter.TodoAdapter
import com.ihsan.roomdb_practice.adapter.TodoAdapterRight
import com.ihsan.roomdb_practice.model.Todo
import com.ihsan.roomdb_practice.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_view_todo.*
import java.time.LocalDateTime


class ViewTodoFragment : Fragment() {

    private lateinit var viewModel: TodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // swapping all todoTask
        swipTodo()

        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        val adapter = TodoAdapter(requireContext(), viewModel)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.readAllTodo.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        val adapterRight = TodoAdapterRight(requireContext(), viewModel)
        recyclerRight.adapter = adapterRight
        recyclerRight.layoutManager = LinearLayoutManager(requireContext())
        viewModel.readAllTodoRight.observe(viewLifecycleOwner, Observer {
            adapterRight.setData(it)
        })

        floating_button.setOnClickListener {
            findNavController().navigate(R.id.action_viewTodoFragment_to_addTaskFragment)
        }
        floating_delete_button.setOnClickListener {
            viewModel.deleteAllTodo()
            viewModel.deleteAllTodoRight()
        }
    }

    private fun swipTodo() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val todo: Todo = viewModel.readAllTodo.value!![viewHolder.adapterPosition]

                if(direction==8)
                {
                    todo.status="right-todo"
                    viewModel.updateTodo(todo)
                }
                else if(direction==4){
                    viewModel.deleteTodo(todo)
                }
            }
        }).attachToRecyclerView(recycler)

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val todoRight: Todo = viewModel.readAllTodoRight.value!![viewHolder.adapterPosition]

                if (direction==8)
                {
                    viewModel.deleteTodo(todoRight)
                }
                else if (direction==4)
                {
                    todoRight.status="todo"
                    viewModel.updateTodo(todoRight)
                }

            }
        }).attachToRecyclerView(recyclerRight)
    }
}