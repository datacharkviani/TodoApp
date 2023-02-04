package com.example.todoapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityUserPlansBinding
import com.example.todoapp.fragments.add.AddPlan
import com.example.todoapp.fragments.list.ToDoList

const val USER_PLANS_ACTIVITY = "com.example.todoapp.activities.message"

class UserPlansActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserPlansBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_user_plans)
        binding = ActivityUserPlansBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ToDoList())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(ToDoList())
                R.id.ic_add -> replaceFragment(AddPlan())
                else ->{
                }
            }
            true
        }

    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.ic_home, fragment)
        fragmentTransaction.commit()

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}