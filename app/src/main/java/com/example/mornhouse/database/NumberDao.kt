package com.example.mornhouse.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NumberDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewNumber(number: NumberEntity)

    @Query(value = "SELECT * FROM numbers_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<NumberEntity>>

//    @Query(value = "SELECT id FROM numbers_table ORDER BY id ASC")
//    fun readOneNumberData(): LiveData<NumberEntity>
}