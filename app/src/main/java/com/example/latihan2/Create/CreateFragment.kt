package com.example.latihan2.Create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.latihan2.R
import com.example.latihan2.TaskData.TaskInput
import kotlinx.android.synthetic.main.fragment_create.*


class CreateFragment : Fragment() {

    private lateinit var viewModel: CreateViewModel
    private lateinit var viewModel2 : CreateVM
    private lateinit var titleInput : String
    private lateinit var detailInput : String
    private lateinit var categoryInput: String
    private lateinit var data : TaskInput

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //drapdown category
        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(),
            R.layout.list_item, items)
        (category_task_input.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        //view model
        //viewModel = ViewModelProvider(this).get(CreateViewModel::class.java)

        viewModel2 = ViewModelProvider(this).get(CreateVM::class.java)

        //click tambahkan
        add_task.setOnClickListener { view ->

            //new Data from edit text
            titleInput = title_task_input.editText!!.text.toString()
            detailInput = detail_task_input.editText!!.text.toString()
            categoryInput = ( category_task_input.editText as? AutoCompleteTextView)!!.text.toString()

//            viewModel.inputData(titleInput, detailInput, categoryInput)
//
//            data = viewModel.taskInput.value!!

            viewModel2.insertData(titleInput,detailInput,categoryInput)



            /*for navigation*/
//            val action =
//                CreateFragmentDirections.actionCreateFragmentToHomeFragment(
//                    data
//                )
////                CreateFragmentDirections.actionCreateFragmentToHomeFragment()
//            NavHostFragment.findNavController(this).navigate(action)

            //Toast data to muncul
            Toast.makeText(context, "Data berhasil dibuat", Toast.LENGTH_SHORT).show()
        }

    }

//    private fun insertTask(){
//        class
//    }

}