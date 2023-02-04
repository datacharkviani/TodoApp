package com.example.todoapp.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.model.Plan
import kotlinx.android.synthetic.main.fragment_add_plan.view.*
import kotlinx.android.synthetic.main.list_item.view.*


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var planList = emptyList<Plan>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false))
    }

    override fun getItemCount(): Int {
        return planList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPlan = planList[position]


        holder.itemView.titleTextView.text = currentPlan.planTitle
        holder.itemView.descriptionTextView.text = currentPlan.planDescription

        if (currentPlan.planDone){
            holder.itemView.planStatusTextView.text = "Done"
        }else{
            holder.itemView.planStatusTextView.text = "Is not done"
        }

        holder.itemView.rowLayout.setOnClickListener {
            val action = ToDoListDirections.actionToDoListToUpdatePlan(currentPlan)
            holder.itemView.findNavController().navigate(action)
        }


    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(plan: List<Plan>){
        this.planList = plan
        notifyDataSetChanged()
    }





}