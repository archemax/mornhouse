package com.example.mornhouse.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mornhouse.database.NumberDatabase
import com.example.mornhouse.database.NumberEntity

class FromDbViewModel(application: Application) : AndroidViewModel(application) {
    private val numDao = NumberDatabase.getDatabase(application)

    val oneNumberLiveData = MutableLiveData<NumberEntity>()


    fun getAllDataFromDb(): LiveData<List<NumberEntity>> {
        return numDao.numberDao().readAllData()
    }
//    fun getOneNumDataFromDb() : LiveData<NumberEntity> {
//
//        return numDao.numberDao().readOneNumberData()
//
//
//    }

}