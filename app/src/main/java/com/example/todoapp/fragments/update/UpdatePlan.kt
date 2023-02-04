package com.example.todoapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.model.Plan
import com.example.todoapp.viewModel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_update_plan.*
import kotlinx.android.synthetic.main.fragment_update_plan.view.*


class UpdatePlan : Fragment() {
    private lateinit var planViewModel: PlanViewModel
    private val args by navArgs<UpdatePlanArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_plan, container, false)

        planViewModel = ViewModelProvider(this)[PlanViewModel::class.java]

        view.updateTitleEditText.setText(args.currentPlan.planTitle)
        view.updateDescriptionEditText.setText(args.currentPlan.planDescription)
        view.planStatusSwitch.isChecked = args.currentPlan.planDone


        // Add Delete menu
        view.updatePlansButton.setOnClickListener{
            updatePlan()

        }
        setHasOptionsMenu(true)

        return view
    }

    private fun updatePlan() {
        val planTitle = updateTitleEditText.text.toString()
        val planDescription = updateDescriptionEditText.text.toString()


        try {

            if (inputCheek(planTitle, planDescription) && planStatusSwitch.isChecked) {
                val updatedPlan = Plan(args.currentPlan.planId, planTitle, planDescription, planDone = true)
                //update current plan
                planViewModel.updatePlan(updatedPlan)
                Toast.makeText(requireContext(), "Todo list Updated successfully", Toast.LENGTH_SHORT).show()
                // Navigate Back
                findNavController().navigate(R.id.action_updatePlan_to_toDoList)


            }
            else if(inputCheek(planTitle, planDescription) && planStatusSwitch.isChecked == false){
                val updatedPlan = Plan(args.currentPlan.planId, planTitle, planDescription, planDone = false)
                //update current plan
                planViewModel.updatePlan(updatedPlan)
                Toast.makeText(requireContext(), "Todo list Updated successfully", Toast.LENGTH_SHORT).show()
                // Navigate Back
                findNavController().navigate(R.id.action_updatePlan_to_toDoList)
               }
            else {
            Toast.makeText(requireContext(), "Please fill out all required fields", Toast.LENGTH_SHORT)
                .show()
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Please Correct the value - ${e.message.toString()}",
                Toast.LENGTH_SHORT).show()

        }
    }

    private fun inputCheek(planTitle: String, planDescription: String): Boolean {
        return !(TextUtils.isEmpty(planTitle) && TextUtils.isEmpty(planDescription))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuDelete){
            deletePlan()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePlan() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            planViewModel.deletePlan(args.currentPlan)
            Toast.makeText(requireContext(),
                "Successfully removed:${args.currentPlan.planTitle}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updatePlan_to_toDoList)
        }
        builder.setNegativeButton("No"){_, _ -> }
        builder.setTitle("Delete ${args.currentPlan.planTitle}?")
        builder.setMessage("Are you sure you want to delete ${args.currentPlan.planTitle}")
        builder.create().show()

    }



}