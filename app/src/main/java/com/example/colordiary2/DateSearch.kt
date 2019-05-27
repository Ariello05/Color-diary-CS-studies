package com.example.colordiary2


import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_data_search.*
import java.text.SimpleDateFormat
import java.time.Year

import java.util.*


class DateSearch : AppCompatActivity(){

    var level = 1

    lateinit var yearView: ListView
    lateinit var monthView: ListView
    lateinit var dayView: ListView

    lateinit var yearadapter: ListAdapter
    lateinit var monthadapter: ListAdapter
    lateinit var dayadapter: ListAdapter

    var goToY: String ="YY"
    var goToM: String ="MM"
    var goToD: String ="DD"

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
            slist.add("20")
        }

        yearadapter = SearchListAdapter(this,nlist,slist)
        yearView.adapter = yearadapter

        yearView.setOnItemClickListener { parent, view, position, id ->
            level++
            goToY = nlist[position]
            updateStatus()
        }

    }

    fun initMonthList() {

        val months: Array<String> = resources.getStringArray(R.array.months)
        var nlist = ArrayList<String>()
        var slist = ArrayList<String>()

        for(i in 0..months.size-1) {
            nlist.add((i+1).toString())
            slist.add(months[i] + " ")
        }

        monthadapter = SearchListAdapter(this,nlist,slist)
        monthView.adapter = monthadapter

        monthView.setOnItemClickListener { parent, view, position, id ->
            level++
            if(position<10) goToM = "0"+nlist[position]
            else goToM = nlist[position]
            updateStatus()
        }

    }

    fun initDayList() {

        val days: Array<String> = resources.getStringArray(R.array.days)
        var nlist = ArrayList<String>()
        var slist = ArrayList<String>()

        var calendar = Calendar.getInstance()
        var sDate = "20" + goToY + "/" + goToM + "/01"
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        calendar.setTime(sdf.parse(sDate))

        var i=1

        nlist.add(" " + i.toString())
        slist.add(days[calendar.get(Calendar.DAY_OF_WEEK)-1])
        calendar.add(Calendar.DATE, 1)
        i++

        while(calendar.get(Calendar.DAY_OF_MONTH) > 1) {

            nlist.add(" " + i.toString())
            slist.add(days[calendar.get(Calendar.DAY_OF_WEEK)-1])

            calendar.add(Calendar.DATE, 1)
            i++
        }

        dayadapter = SearchListAdapter(this,nlist,slist)
        dayView.adapter = dayadapter

        dayView.setOnItemClickListener { parent, view, position, id ->
            level++
            goToD = nlist[position]
            updateStatus()
        }


    }

    fun goBack(view: View) {
        level--
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
            level --
        }

    }

    fun backToLobby() {
        finish()
    }

    fun goToDay() {

        var dateToEdit=goToY+goToM+goToD
        val i=Intent(this, EditDayActivity::class.java)
        i.putExtra("Date",dateToEdit)
        i.putExtra("colorSetFileName", intent.getStringExtra("setFileName"))
        i.putExtra("dateFileName", dateToEdit)
        startActivityForResult(i, 1234)
    }



}