package com.example.todoapp.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.PlanDao
import com.example.todoapp.model.Plan

class PlanRepository(private val planDao: PlanDao) {

    val readAllData: LiveData<List<Plan>> = planDao.readAllData()

    suspend fun addPlan(plan: Plan) {
        planDao.addProduct(plan)
    }

    suspend fun updatePlan(plan: Plan) {
        planDao.updateProduct(plan)
    }

    suspend fun deletePlan(plan: Plan){
        planDao.deleteProduct(plan)
    }

    suspend fun deleteAllPlan(){
        planDao.deleteAllProduct()
    }
}