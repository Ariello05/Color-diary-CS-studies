package com.example.colordiary2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import java.util.*


class DayEditActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_edit)
    }




    fun editDayOnClick(view: View){
        var cdate=Calendar.getInstance()


        when (view.getId()) {

            R.id.todayButton -> {
                editDay(cdate.time)
            }

            R.id.tomorrowButton -> {
                cdate.add(Calendar.DAY_OF_MONTH, 1)
                editDay(cdate.time)
            }

            R.id.yesterdayButton -> {
                cdate.add(Calendar.DAY_OF_MONTH, -1)
                editDay(cdate.time)
            }

            R.id.otherDayButton ->{
            //TODO: popup date
            }
        }


    }
    fun editDay(date: Date){
        //TODO: implement
    }

    }