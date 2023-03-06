package com.example.mornhouse.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.mornhouse.R

class OneNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_number)

        val infoTextView = findViewById<TextView>(R.id.oneNumberInfoTextView)
        val descriptionTextView = findViewById<TextView>(R.id.oneNumberDescriprion)

        val info = intent.getStringExtra("info_about_number")
        infoTextView.text = info

//        val fullInfoViewModel = ViewModelProvider(this).get( FromDbViewModel::class.java)
//        fullInfoViewModel.getOneNumDataFromDb()
//
//        fullInfoViewModel.getOneNumDataFromDb().observe(this){ item ->
//            infoTextView.text = item.number
//            descriptionTextView.text = item.description
//
//        }


    }
}