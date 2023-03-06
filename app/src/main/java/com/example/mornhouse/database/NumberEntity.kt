package com.example.mornhouse.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("numbers_table")
data class NumberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val number: String,
    val description: String,
)
