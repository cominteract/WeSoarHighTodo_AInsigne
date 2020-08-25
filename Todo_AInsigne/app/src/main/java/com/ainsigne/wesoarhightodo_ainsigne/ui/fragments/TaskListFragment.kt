package com.ainsigne.wesoarhightodo_ainsigne.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.ainsigne.wesoarhightodo_ainsigne.BuildConfig
import com.ainsigne.wesoarhightodo_ainsigne.MainActivity
import com.ainsigne.wesoarhightodo_ainsigne.R
import com.ainsigne.wesoarhightodo_ainsigne.TodoAdapter
import com.ainsigne.wesoarhightodo_ainsigne.api.TodoAPI
import com.ainsigne.wesoarhightodo_ainsigne.databinding.FragmentTaskListBinding
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.Task
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.TasksRepository
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.TaskViewModelFactory
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.TasksViewModel
import com.ainsigne.wesoarhightodo_ainsigne.utils.FAKE_BUILD
import com.ainsigne.wesoarhightodo_ainsigne.utils.fakeTasks
import kotlinx.android.synthetic.main.fragment_task_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {

    @Inject
    lateinit var viewModel : TasksViewModel

    @Inject
    lateinit var repo : TasksRepository

    @Inject
    lateinit var todoApi : TodoAPI

    lateinit var adapter : TodoAdapter

    private var startedApi = false

    var listTask : List<Task> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tasks.observe(viewLifecycleOwner){
            items ->
            if(!startedApi)
            startApi()
            else if (!items.isNullOrEmpty()) {
                listTask = items
                updateAdapter()
            }
        }
        iv_add.setOnClickListener {
            val direction =
                TaskListFragmentDirections.actionTasksFragmentToTaskDetailFragment(listTask.size + 1, false)
            it.findNavController().navigate(direction)
        }
        btn_toggle_id.setOnClickListener {
            updateAdapter()
        }
    }

    private fun updateAdapter(){
        if(btn_toggle_id.isChecked){
            adapter.submitList(listTask.sortedBy { t -> t.title }.filter { !it.completed })
        }else{
            adapter.submitList(listTask.sortedBy { it.title})
        }
    }

    private fun startApi(){

        if(BuildConfig.FLAVOR == FAKE_BUILD){
            repo.insertAll(fakeTasks)
            startedApi = true
        }else{
            CoroutineScope(Dispatchers.IO).launch {
                val response = todoApi.webservice.getTasks()
                if(response.isSuccessful){
                    response.body()?.let {
                        repo.insertAll(it)
                        startedApi = true
                    }
                }else{
                    startedApi = true
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTaskListBinding.inflate(inflater,container,false)
        context ?: return binding.root
        (context as MainActivity).activityComponent.inject(this)
        adapter = TodoAdapter()
        adapter.repo = repo
        adapter.totoApi = todoApi
        binding.taskList.adapter = adapter
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param taskId id of selected task.
         * @return A new instance of fragment TaskListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            TaskListFragment()
    }
}