package com.example.latihan2.Home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihan2.*
import com.example.latihan2.TaskData.Task
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var viewModelFactory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel
    private var listTask: ArrayList<Task> = ArrayList()
    private val args : HomeFragmentArgs by navArgs()
    private var id: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val data = if (args.taskData == null) null else args.taskData!!
//        val data = null
//        Log.i("wadaw", "${data}")

        viewModelFactory = HomeViewModelFactory(data)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

//        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
//        Log.i("wadaw", "${data}")

        /*mentriger secara langsung jika terdapat perubahan pada viewmodel*/
        viewModel.listTask.observe(viewLifecycleOwner, androidx.lifecycle.Observer { newTask ->

            listTask = newTask

            if(listTask.size == 0){

                list_task.visibility = View.GONE
                empty_task.visibility = View.VISIBLE

            }

            else{

                Log.i("wadaw", "ada perubahan nih")
                empty_task.visibility = View.GONE
                list_task.visibility = View.VISIBLE

                val adapter = TaskAdapter(listTask)
                list_task.layoutManager = LinearLayoutManager(requireContext())
                list_task.adapter = adapter


            }

        })

        if(listTask.size == 0){

            list_task.visibility = View.GONE
            empty_task.visibility = View.VISIBLE

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            //if button click
            R.id.createFragment -> {
                Toast.makeText(context, "wadawwwww", Toast.LENGTH_SHORT).show()
                true
            }

            else -> false

        }

        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) ||
                super.onOptionsItemSelected(item)
    }

}
