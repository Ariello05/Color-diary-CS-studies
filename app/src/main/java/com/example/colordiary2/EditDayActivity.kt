package com.example.colordiary2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_edit_day.*


class EditDayActivity : AppCompatActivity(){
    private var dateToEdit:String
    private val hours = Array(48) { HourPlan(0,0) }
    private val colorSetName=""
    init{
        dateToEdit=""
        for(i in 0..47){
            hours[i].hour=i/2
            var j=i%2
            when (j){
                0->hours[i].minutes=0
                1->hours[i].minutes=30
            }
            hours[i].updateStrings()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dateToEdit=intent.getStringExtra("Date")
        setContentView(R.layout.activity_edit_day)
        currDateText.setTextSize(30f)
        currDateText.setBackgroundColor(getColor(R.color.colorPrimary))
        currDateText.text="Editing "+dateToEdit.subSequence(4, 6)+"."+dateToEdit.subSequence(2,4)+"."+dateToEdit.subSequence(0, 2)
        val gridView = findViewById<GridView>(R.id.list)
        val hourAdapter = HoursAdapter(this, hours, colorSetName)
        gridView.adapter = hourAdapter


    }



}