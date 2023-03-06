package com.example.mornhouse.presentation

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mornhouse.database.NumberDao
import com.example.mornhouse.database.NumberDatabase
import com.example.mornhouse.database.NumberEntity
import com.example.mornhouse.database.NumberRepository
import com.example.mornhouse.retrofit.NumberDataClass
import com.example.mornhouse.retrofit.RetrofitObject
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext


class NumberViewModel(application: Application) : AndroidViewModel(Application()) {
    val myDao = NumberDatabase.getDatabase(application)
    val listFromDb = MutableLiveData<List<NumberEntity>>()

    private val _numberMutableLiveData: MutableLiveData<String> = MutableLiveData<String>()
    private val _textMutableLiveData: MutableLiveData<String> = MutableLiveData<String>()

    val numberMutableLiveData: LiveData<String> = _numberMutableLiveData
    val textMutableLiveData: LiveData<String> = _textMutableLiveData


    fun getNumberInfo(numberFromUser: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responce = try {
                RetrofitObject.api.getNumber(numberFromUser)

            } catch (e: IOException) {
                Log.d("NumberViewModel", "No Internet connction at all $e")
                return@launch
            } catch (e: HttpException) {
                Log.d("MainActivity", "Server error $e")
                return@launch
            }
            Log.d("Log11", "${responce.body()?.text}")
            Log.d("Log11", "${responce.body()?.number}")

            _numberMutableLiveData.postValue(responce.body()?.number)
            _textMutableLiveData.postValue(responce.body()?.text)


            val numberFromApi = responce.body()?.number
            val textFromApi = responce.body()?.text
            val numberObj = NumberEntity(0, numberFromApi!!, textFromApi!!)

            //val db = NumberDatabase.getDatabase()
            myDao.numberDao().addNewNumber(numberObj)
            Log.d("Database", "data is put in db $numberObj")

            //


        }


    }


    ///////////////////////////////////////////////////////////////////////////////////
    //get/put data to database
//    fun getAllNumbersFromDb() {
//        numberRepository.getAllNumbers()
//    }

    fun addNumberToDb(number: String) {
        val numberObj = NumberEntity(
            0,
            numberMutableLiveData.toString(),
            textMutableLiveData.toString()
        )
    }

    fun getRandomNumberInfo() {

        viewModelScope.launch {
            val responce = try {
                RetrofitObject.api.getRandomNumber()
            } catch (e: IOException) {
                Log.d("MainActivity", "No Internet connction $e")
                return@launch
            } catch (e: HttpException) {
                Log.d("MainActivity", "Server error $e")
                return@launch
            } catch (e: java.lang.NumberFormatException) {
                //Toast.makeText(this@MainActivity, "the number has wrong type", Toast.LENGTH_LONG).show()
                Log.d("MainActivity", "wring number format $e")
                return@launch
            }
            Log.d("LogRandomNumber", "${responce.body()?.text}")
            Log.d("LogRandomNumber", "${responce.body()?.number}")

            _numberMutableLiveData.postValue(responce.body()?.number)
            _textMutableLiveData.postValue(responce.body()?.text)

            val numberFromApi = responce.body()?.number
            val textFromApi = responce.body()?.text
            val numberObj = NumberEntity(0, numberFromApi!!, textFromApi!!)

            //val db = NumberDatabase.getDatabase()
            myDao.numberDao().addNewNumber(numberObj)
            Log.d("Database", "data is put in db $numberObj")


        }
    }
}



