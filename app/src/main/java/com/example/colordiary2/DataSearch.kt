package com.example.colordiary2

import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_data_search.*
import java.time.Year
import java.util.*


class DataSearch : AppCompatActivity(){

    var level = 1
    lateinit var yearView: ListView
    lateinit var monthView: ListView
    lateinit var dayView: ListView

    lateinit var yearadapter: ListAdapter
    lateinit var monthadapter: ListAdapter
    lateinit var dayadapter: ListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_search)

        yearView = findViewById<ListView>(R.id.year)
        monthView = findViewById<ListView>(R.id.month)
        dayView = findViewById<ListView>(R.id.day)

        initYearList()
        initMonthList()
    }

    fun initYearList() {

        var nlist = ArrayList<String>()
        var slist = ArrayList<String>()

        for(i in 19..30) {
            nlist.add(i.toString())
            slist.add("")
        }

        yearadapter = SearchListAdapter(this,nlist)
        yearView.adapter = yearadapter

    }

    fun initMonthList() {

        val months: Array<String> = resources.getStringArray(R.array.months)
        var slist = ArrayList<String>()

        for(i in 0..months.size-1) {
          slist.add(months[i])
        }

        monthadapter = SearchListAdapter(this,slist)
        monthView.adapter = monthadapter

    }

    var calendar = Calendar.getInstance()

    fun initDayList() {


    }

    fun goBack(view: View) {
        level++
        updateStatus()
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
            initDayList()
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