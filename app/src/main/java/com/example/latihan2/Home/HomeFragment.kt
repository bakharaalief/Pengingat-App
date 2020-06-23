package com.example.latihan2.Home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.latihan2.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel : HomeVM

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

        viewModel = ViewModelProvider(this).get(HomeVM::class.java)

        /*recylerview trigger*/
        viewModel.listTask.observe(viewLifecycleOwner, Observer { newTask ->

            if(newTask.size == 0){

                list_task.visibility = View.GONE
                empty_task.visibility = View.VISIBLE

            }

            else{

                Log.i("wadaw", "ada perubahan nih")
                empty_task.visibility = View.GONE
                list_task.visibility = View.VISIBLE

                //val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
                val adapter = TaskAdapter(newTask, viewModel)
                list_task.layoutManager = LinearLayoutManager(requireContext())
                //list_task.addItemDecoration(divider)
                list_task.adapter = adapter


            }

        })

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
