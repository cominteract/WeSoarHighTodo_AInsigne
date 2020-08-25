package com.ainsigne.wesoarhightodo_ainsigne.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.ainsigne.wesoarhightodo_ainsigne.BuildConfig
import com.ainsigne.wesoarhightodo_ainsigne.MainActivity

import com.ainsigne.wesoarhightodo_ainsigne.R
import com.ainsigne.wesoarhightodo_ainsigne.api.TodoAPI
import com.ainsigne.wesoarhightodo_ainsigne.databinding.FragmentTaskEntryBinding
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.Task
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.TasksRepository
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.DetailTaskViewModel
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.DetailTaskViewModelFactory
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.TasksViewModel
import com.ainsigne.wesoarhightodo_ainsigne.utils.FAKE_BUILD
import kotlinx.android.synthetic.main.fragment_task_entry.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [TaskEntryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskEntryFragment : Fragment() {
    private val args: TaskEntryFragmentArgs by navArgs()

    @Inject
    lateinit var repo : TasksRepository

    lateinit var detailTaskViewModel: DetailTaskViewModel

    @Inject
    lateinit var todoApi : TodoAPI

    var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun updateDate(){

        detailTaskViewModel.task.observe(viewLifecycleOwner){
            val parts = it.date.split("/")
            val month = Integer.parseInt(parts[0]) - 1
            val day = Integer.parseInt(parts[1])
            val year = Integer.parseInt(parts[2])
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            cal_task_date.setDate (calendar.timeInMillis, true, true);
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(args.taskUpdate){
            updateDate()
        }
        cal_task_date.setOnDateChangeListener { _, y, m, d ->
            date = String.format("%s/%s/%s",m + 1,d,y)
            tv_task_date.text = date
        }
        tv_task_id.text = args.taskId.toString()
        val task = Task(args.taskId, et_task_title.text.toString(), ck_task_complete.isChecked, date)
        btn_submit.setOnClickListener {
            if(BuildConfig.FLAVOR == FAKE_BUILD){
                CoroutineScope(Dispatchers.IO).launch {
                    if(args.taskUpdate){
                        repo.updateTask(task)
                    }else{
                        repo.insertTask(task)
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        activity?.supportFragmentManager?.popBackStack()
                    }
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    if(args.taskUpdate){
                        todoApi.webservice.updateTask(task.id.toString(), task)
                        repo.updateTask(task)
                    }else{
                        todoApi.webservice.submitTask(task)
                        repo.insertTask(task)
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        activity?.supportFragmentManager?.popBackStack()
                    }
                }
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTaskEntryBinding>(
            inflater, R.layout.fragment_task_entry, container, false).apply {
            (context as MainActivity).activityComponent.inject(this@TaskEntryFragment)
            detailTaskViewModel = DetailTaskViewModelFactory(
                repo,
                args.taskId
            ).create(DetailTaskViewModel::class.java)

            viewModel = detailTaskViewModel
            lifecycleOwner = this@TaskEntryFragment

        }
        setHasOptionsMenu(true)
        return binding.root

    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            TaskEntryFragment()
    }
}