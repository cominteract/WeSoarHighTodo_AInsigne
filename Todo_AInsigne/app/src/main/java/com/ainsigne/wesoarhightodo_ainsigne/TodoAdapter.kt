package com.ainsigne.wesoarhightodo_ainsigne

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ainsigne.wesoarhightodo_ainsigne.api.TodoAPI
import com.ainsigne.wesoarhightodo_ainsigne.databinding.ItemTodoBinding

import com.ainsigne.wesoarhightodo_ainsigne.ui.data.Task
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.TasksRepository
import com.ainsigne.wesoarhightodo_ainsigne.ui.fragments.TaskListFragmentDirections
import com.ainsigne.wesoarhightodo_ainsigne.utils.FAKE_BUILD
import kotlinx.android.synthetic.main.fragment_task_entry.view.*
import kotlinx.android.synthetic.main.item_todo.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Adapter for the [RecyclerView] in [TaskListFragment].
 * * responsible for displaying all the tasks retrieved
 */
class TodoAdapter : ListAdapter<Task, TodoAdapter.ViewHolder>(TodoDiffCallback()) {

    lateinit var repo : TasksRepository
    lateinit var totoApi : TodoAPI

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.apply {
            bind(createOnClickListener(task), task)
            bindDelete(createDeleteClickListener(task, position), task)
            itemView.ck_complete.setOnClickListener(createOnCheckListener(task, position))
            itemView.tag = task

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createDeleteClickListener(task : Task, position: Int): View.OnClickListener {
        return View.OnClickListener {
            //repo.deleteTask(task)
            if(BuildConfig.FLAVOR == FAKE_BUILD){
                CoroutineScope(Dispatchers.IO).launch {
                    repo.deleteTask(task)
                    CoroutineScope(Dispatchers.Main).launch {
                        notifyItemChanged(position)
                    }
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    val response = totoApi.webservice.deleteTask(task.id.toString())
                    repo.deleteTask(task)
                    if(response.isSuccessful){
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(it.context, " Success " + response.message(), Toast.LENGTH_LONG ).show()
                            notifyItemChanged(position)
                        }
                    }else{
                        CoroutineScope(Dispatchers.Main).launch {
                            notifyItemChanged(position)
                        }
                    }
                }
            }
        }
    }

    private fun createOnClickListener(task : Task): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                TaskListFragmentDirections.actionTasksFragmentToTaskDetailFragment(task.id, true)
            it.findNavController().navigate(direction)
        }
    }

    private fun createOnCheckListener(task: Task, position: Int) : View.OnClickListener {
        return View.OnClickListener {
            task.completed = !task.completed
            //repo.updateTask(task)
            if(BuildConfig.FLAVOR == FAKE_BUILD){
                CoroutineScope(Dispatchers.IO).launch {
                    repo.updateTask(task)
                    CoroutineScope(Dispatchers.Main).launch {
                        notifyItemChanged(position)
                    }
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    val response = totoApi.webservice.updateTask(task.id.toString(),task)
                    repo.updateTask(task)
                    if(response.isSuccessful){
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(it.context, " Success " + response.message(), Toast.LENGTH_LONG ).show()
                            notifyItemChanged(position)
                        }
                    }else{
                        CoroutineScope(Dispatchers.Main).launch {
                            notifyItemChanged(position)
                        }
                    }
                }
            }

        }
    }


    class ViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {



        fun bindDelete(listener: View.OnClickListener, item: Task) {
            binding.apply {
                deleteClickListener = listener
                task = item
                executePendingBindings()
            }
        }

        fun bind(listener: View.OnClickListener, item: Task) {
            binding.apply {
                clickListener = listener
                task = item
                executePendingBindings()
            }
        }
    }
}


private class TodoDiffCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem:Task, newItem: Task): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}
