package com.example.todoapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.PlanDataBase
import com.example.todoapp.model.Plan
import com.example.todoapp.repository.PlanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


    class PlanViewModel(application: Application): AndroidViewModel(application) {
        val readAllData: LiveData<List<Plan>>
        private val repository: PlanRepository

        init {
            val planDao = PlanDataBase.getDataBase(application).planDao()

            repository = PlanRepository(planDao)
            readAllData = repository.readAllData
        }

        fun addPlan(plan: Plan) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addPlan(plan)
            }
        }

        fun updatePlan(plan: Plan) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.updatePlan(plan)
            }
        }

        fun deletePlan(plan: Plan) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deletePlan(plan)
            }
        }

        fun deleteAllPlans() {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteAllPlan()
            }

        }
    }

