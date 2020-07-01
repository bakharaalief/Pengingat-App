package com.example.latihan2.Create

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.core.view.forEach
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.latihan2.R
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.item_task.*
import java.text.SimpleDateFormat
import java.util.*


class CreateFragment : Fragment() {

    private lateinit var viewModel : CreateVM
    private lateinit var titleInput : String
    private lateinit var detailInput : String
    private lateinit var categoryInput: String
    private lateinit var dateReminderInput : Date
    private var colorInput : Int = 0
    private var setReminder = false

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
        val items = listOf("Olahraga", "Liburan", "Pekerjaan")
        val adapter = ArrayAdapter(requireContext(),
            R.layout.list_item, items)
        (category_task_input.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        viewModel = ViewModelProvider(this).get(CreateVM::class.java)
        dateReminderInput = Date()

        //button color
        color_task_group.addOnButtonCheckedListener { color_task_group, checkedId, isChecked ->

            //click singgle button
            if(isChecked){

                when(checkedId){

                    R.id.color_task_input1 -> {
                        colorInput = 1
                    }

                    R.id.color_task_input2 -> {
                        colorInput = 2
                    }

                    R.id.color_task_input3 -> {
                        colorInput = 3
                    }
                }

            }

//            Log.i("wadaw", "${colorInput}")
        }

        //click  tambahkan
        add_task.setOnClickListener { v ->

            //new Data from edit text
            titleInput = title_task_input.editText!!.text.toString().capitalize()
            detailInput = detail_task_input.editText!!.text.toString().capitalize()
            categoryInput = ( category_task_input.editText as? AutoCompleteTextView)!!.text.toString().capitalize()


//            //solve error if not insert title
//            if(titleInput.length < 0){
//                titleInput = " "
//            }

            viewModel.insertData(titleInput,detailInput,categoryInput, colorInput, setReminder, dateReminderInput)

            /*for navigation*/
            val action = CreateFragmentDirections.actionCreateFragmentToHomeFragment()
            v.findNavController().navigate(action)

            //Toast data to muncul
            Toast.makeText(context, "Data berhasil dibuat", Toast.LENGTH_SHORT).show()
        }

        val builderDatePicker = MaterialDatePicker.Builder.datePicker()
        builderDatePicker.setTitleText("Select Date")
        val materialDatePicker = builderDatePicker.build()
        reminder_task_input.setOnClickListener {
            materialDatePicker.show(parentFragmentManager, materialDatePicker.toString())
        }

        edit_reminder_task_input.setOnClickListener {
            materialDatePicker.show(parentFragmentManager, materialDatePicker.toString())
        }

        materialDatePicker.addOnPositiveButtonClickListener{ selection ->
            dateReminderInput = Date(selection) //change long typdata to date
            Toast.makeText(context, "${dateReminderInput}", Toast.LENGTH_SHORT).show()

            val format = SimpleDateFormat("EEE, dd/MM/yy")
            val dateFormat = format.format(dateReminderInput)

            setReminder = true
            reminder_task_input.visibility = View.GONE
            edit_reminder_task_input.visibility = View.VISIBLE
            reminder_task_input_2.visibility = View.VISIBLE
            reminder_task_input_2.setText(dateFormat)
            close_reminder_task_input.visibility = View.VISIBLE
        }

        close_reminder_task_input.setOnClickListener {
            setReminder = false
            reminder_task_input_2.visibility = View.GONE
            close_reminder_task_input.visibility = View.GONE
            reminder_task_input.visibility = View.VISIBLE
            edit_reminder_task_input.visibility = View.GONE
        }
    }

}