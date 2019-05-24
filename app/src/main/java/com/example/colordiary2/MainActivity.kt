package com.example.colordiary2

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var colorSet : ColorSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example usage of colorSet
        colorSet = ColorSet(applicationContext)//or this
        colorSet.insert(Color.parseColor("#000000"), "Sleep")
        colorSet.insert(Color.parseColor("#FFFFFF"), "Living")
        colorSet.saveSet("NewSet")
        colorSet.print()
        colorSet.loadSet("NewSet")
        colorSet.print()
        ButtonEdit.setBackgroundColor(colorSet.getColor("Sleep"))
        ButtonEdit.setTextColor(colorSet.getColor("Living"))

    }

    fun enterEditActivity(v: View){
        //TODO: Switch into editActivity

    }

    fun enterStatisticsActivity(v: View){
        //TODO: Switch into statisticsActivity
    }

    fun enterColorsActivity(v: View){
        //TODO: Switch into colorsActivity
        //;>
    }

}
