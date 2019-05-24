package com.example.colordiary2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_data_search.*
import java.util.*


class DataSearch : AppCompatActivity(){

    var level = 1
    val yearView = findViewById<ListView>(R.id.year)
    val monthView = findViewById<ListView>(R.id.month)
    val dayView = findViewById<ListView>(R.id.day)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_search)
    }

    fun updateStatus() {

        if(level < 1) {
            backToLobby()

        } else if(level == 1) {
            yearView.setVisibility(View.VISIBLE)
            monthView.setVisibility(View.GONE)
            dayView.setVisibility(View.GONE)

        } else if(level == 2) {
            yearView.setVisibility(View.GONE)
            monthView.setVisibility(View.VISIBLE)
            dayView.setVisibility(View.GONE)

        } else if(level == 3) {
            yearView.setVisibility(View.GONE)
            monthView.setVisibility(View.GONE)
            dayView.setVisibility(View.VISIBLE)

        } else if(level > 3) {

            goToDay()
        }

    }

    fun backToLobby() {
        //TO DO
    }

    fun goToDay() {
        //TO DO
        //Search yy:mm:dd
    }



}