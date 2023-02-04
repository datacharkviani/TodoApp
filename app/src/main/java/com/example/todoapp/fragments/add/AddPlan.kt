package com.example.todoapp.fragments.add

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.activities.RegisterActivity
import com.example.todoapp.model.Plan
import com.example.todoapp.viewModel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_add_plan.*
import kotlinx.android.synthetic.main.fragment_add_plan.view.*
import java.lang.Exception


class AddPlan : Fragment() {

    private lateinit var planViewModel: PlanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_plan, container, false)
        planViewModel = ViewModelProvider(this).get(PlanViewModel::class.java)

        view.addPlanButton.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val planTitle = titleEditText.text.toString()
        val planDescription = descriptionEditText.text.toString()


        try {
            if (planTitle.isNotEmpty() && planDescription.isNotEmpty() &&
                inputCheck(planTitle, planDescription) && planStatusSwitch.isChecked == true) {
                val plan = Plan(0, planTitle, planDescription, planDone = true)
                planViewModel.addPlan(plan)
                Toast.makeText(requireContext(), "Plan Added", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.toDoList)

            } else if (planTitle.isNotEmpty() && planDescription.isNotEmpty() &&
                inputCheck(planTitle, planDescription) && planStatusSwitch.isChecked == false) {
                val plan = Plan(0, planTitle, planDescription, planDone = false)
                planViewModel.addPlan(plan)
                Toast.makeText(requireContext(), "Plan Added", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.toDoList)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please fill out all required fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(),"Please Correct the value - ${e.message.toString()}",Toast.LENGTH_SHORT).show()
        }



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.log_out_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuLogOut){
            val intent = Intent(this.context,RegisterActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun inputCheck(planTitle: String, planDescription: String): Boolean{
        return !(TextUtils.isEmpty(planTitle) && planDescription.isEmpty())
    }


}