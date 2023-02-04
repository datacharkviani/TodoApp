package com.example.todoapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "plan_table")
data class Plan (
    @PrimaryKey(autoGenerate = true)
    val planId: Int,
    val planTitle: String,
    val planDescription: String,
    val planDone: Boolean

): Parcelable