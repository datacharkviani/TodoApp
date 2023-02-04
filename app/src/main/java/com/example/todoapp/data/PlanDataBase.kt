package com.example.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.model.Plan

@Database(entities = [Plan::class], version = 1, exportSchema = false)
abstract class PlanDataBase: RoomDatabase() {

    abstract fun planDao(): PlanDao

    companion object {
        @Volatile
        private var INSTANCE: PlanDataBase? = null

        fun getDataBase(context: Context): PlanDataBase {
            val temInstance = INSTANCE
            if (temInstance != null) {
                return temInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, PlanDataBase::class.java,
                    "user_product_database").build()
                INSTANCE = instance
                return  instance
            }


        }
    }

}