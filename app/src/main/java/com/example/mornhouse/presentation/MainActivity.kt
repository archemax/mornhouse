package com.example.mornhouse.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mornhouse.R


class MainActivity : AppCompatActivity() {


    private val viewModel: NumberViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonGetRandomNumber = findViewById<Button>(R.id.buttonGetRandomNumber)
        val buttonGetInfo = findViewById<Button>(R.id.buttonGetInfo)
        val textFromUser = findViewById<EditText>(R.id.editTextEnterNumber)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLayout)

        val numbersRvAdapter = NumbersRvAdapter(object : NumbersRvAdapter.ItemClickListener {
            override fun onItemClick(position: String) {
                //Log.d ("PosInRv", "$position")
                val intent = Intent(this@MainActivity, OneNumberActivity::class.java)
                intent.putExtra("info_about_number", position)
                startActivity(intent)
            }

        })
        recyclerView.adapter = numbersRvAdapter

        //get data from DB
        val fromDbViewModel = ViewModelProvider(this).get(FromDbViewModel::class.java)
        val liveDataFromDb = fromDbViewModel.getAllDataFromDb()
        liveDataFromDb.observe(this) { row ->
            Log.d("FromDb", "${row.size}")
            //add all data to adapter
            numbersRvAdapter.addAllNumberstoList(row)

        }

        buttonGetInfo.setOnClickListener {
            val numUserEntered11 = textFromUser.text.toString()
            viewModel.getNumberInfo(numUserEntered11)
            Log.d("numUserEntered11", numUserEntered11)
            //viewModel.addNumberToDb(numUserEntered11)
        }

        buttonGetRandomNumber.setOnClickListener {
            viewModel.getRandomNumberInfo()
        }


    }
}


//                Log.d("ResponceMainActivity", "Responce body is: ${responce.body()}")
//
//
//                val numberFromApi = responce.body()?.number
//                val textFromApi = responce.body()?.text
//
//                val numberObj = NumberEntity(0, numberFromApi!!, textFromApi!!)
//
//                val db = NumberDatabase.getDatabase(context = baseContext)
//                db.numberDao().addNewNumber(numberObj)
//                Log.d("Database", "data is put in db $numberObj")
//
//            }
//
//
//        }
//    }
//
//
//}
