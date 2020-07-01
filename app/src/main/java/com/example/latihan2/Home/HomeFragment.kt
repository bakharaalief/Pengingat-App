package com.example.latihan2.Home

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.*
import android.widget.LinearLayout
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
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import kotlinx.android.synthetic.main.category_item.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel : HomeVM
    private val listTask = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeVM::class.java)

        /*recylerview trigger*/
        viewModel.listTask.observe(viewLifecycleOwner, Observer { newTask ->

            if(newTask.size == 0){

                list_task.visibility = View.GONE
                empty_task.visibility = View.VISIBLE
                chip_category_group.visibility = View.GONE

            }

            else{

                list_task.visibility = View.VISIBLE
                empty_task.visibility = View.GONE
                chip_category_group.visibility = View.VISIBLE

                /*recylerview task*/
                val adapter = TaskAdapter(newTask, viewModel, requireContext())
                list_task.layoutManager = LinearLayoutManager(requireContext())
                list_task.adapter = adapter

                //viewModel.categoryTask(newTask)
            }

            viewModel.listCategory.observe(viewLifecycleOwner, Observer { newCategory ->

                chip_category_group.removeAllViews()

                /*chip category*/
                for(data in newCategory){
                    val chip = Chip(requireContext())
                    val drawable = ChipDrawable.createFromResource(requireActivity(), R.xml.chip_category)
                    chip.setChipDrawable(drawable)
                    chip.text = data
                    chip.setOnClickListener{
                        Toast.makeText(context, "${data}", Toast.LENGTH_SHORT).show()
                    }

                    chip_category_group.addView(chip)
                }

            })

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
                true
            }

            else -> false

        }

        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) ||
                super.onOptionsItemSelected(item)
    }



}
