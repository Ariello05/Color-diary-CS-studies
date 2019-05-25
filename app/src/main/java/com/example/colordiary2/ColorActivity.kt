package com.example.colordiary2

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_color.*
import android.R.layout
import android.graphics.Color
import android.support.v7.widget.GridLayoutManager
import android.widget.Spinner
import android.widget.ArrayAdapter



class ColorActivity : AppCompatActivity() {

    lateinit var colorSet:ColorSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val mu :HashSet<String>
        val sp = getSharedPreferences("MainResource", Context.MODE_PRIVATE)
        mu = sp.getStringSet(getString(R.string.colorPresets),HashSet<String>()) as HashSet<String>//Get list of known colorSets

        val adapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, mu.toList()
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPresets.adapter = adapter

        var layoutManager = GridLayoutManager(this, 2)
        listViewColorSet.layoutManager = layoutManager

        colorSet.loadSet(getNameOfSelectedPreset()!!)

        var myAdapter= ColorActivityArrayAdapter(this, colorSet)
        listViewColorSet.adapter = myAdapter
        listViewColorSet.addItemDecoration(GridLayoutDecorator(2,50,false))

        listViewColorSet.setHasFixedSize(false)


    }

    fun getNameOfSelectedPreset(): String?{
        val sp = getSharedPreferences("MainResource", Context.MODE_PRIVATE)
        return sp.getString(getString(R.string.currentColorSet),"")//Loads name of main color set in program
    }

}

/*
currentSet = ColorSet(applicationContext)
val str = getNameOfCurrentPreset()
if(str == ""){
    currentSet.insert(Color.parseColor("#000000"), "Sleep")
    currentSet.insert(Color.parseColor("#FFFFFF"), "Living")
}else {
    currentSet.loadSet(str!!)
}

val adapter = ArrayAdapter<String>(
    this, android.R.layout.simple_spinner_item, currentSet.getActivityNameArrayList()
)

adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
spinnerPresets.adapter = adapter
*/