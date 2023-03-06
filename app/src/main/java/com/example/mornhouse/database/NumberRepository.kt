package com.example.mornhouse.database

class NumberRepository(private val numberDatabase: NumberDatabase) {

    suspend fun insertNumber(number: NumberEntity) {
        numberDatabase.numberDao().addNewNumber(number)
    }

    fun getAllNumbers() {
        numberDatabase.numberDao().readAllData()
    }
}