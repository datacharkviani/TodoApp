package com.example.todoapp.fragments.list

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.activities.RegisterActivity
import com.example.todoapp.viewModel.PlanViewModel
//import kotlinx.android.synthetic.main.fragment_to_do_list.view.*


private var Any.layoutManager: LinearLayoutManager
    get() {
        TODO("Not yet implemented")
    }
    set(layputManager) {}
private var Any.adapter: ListAdapter
    get() {
        TODO("Not yet implemented")
    }
    set(adapter) {}
private val View.floatingActionButton: Any
    get() {
        TODO("Not yet implemented")
    }
private val View.recyclerView: Any
    get() {
        TODO("Not yet implemented")
    }
// es yvela bolos davamate!!!!!
class ToDoList : Fragment() {

    private lateinit var planViewModel: PlanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do_list, container, false)
        
        // RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // PlanViewModel
        planViewModel = ViewModelProvider(this)[PlanViewModel::class.java]
        planViewModel.readAllData.observe(viewLifecycleOwner, Observer { plan ->
            adapter.setData(plan)
        })


        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_toDoList_to_addPlan)
        }

        setHasOptionsMenu(true)

        return view

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.log_out_menu, menu)
        inflater.inflate(R.menu.delete_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuLogOut){
            val intent = Intent(this.context, RegisterActivity::class.java)
            startActivity(intent)
        }
        else if (item.itemId == R.id.menuDelete){
            deleteAllProduct()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllProduct() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            planViewModel.deleteAllPlans()
            Toast.makeText(requireContext(),
                "Successfully removed everything!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}

private fun Any.setOnClickListener(function: () -> Unit) {
    TODO("Not yet implemented")
}
//esec bolos davamatee!!!!!--------------