package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.model.Plan


@Dao
interface PlanDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(plan: Plan)

    @Query("SELECT * from plan_table ORDER BY planDone DESC")
    fun readAllData(): LiveData<List<Plan>>

    @Delete
    suspend fun deleteProduct(plan: Plan)

    @Query("DELETE FROM plan_table")
    suspend fun deleteAllProduct()

    @Update
    suspend fun updateProduct(plan: Plan)

}