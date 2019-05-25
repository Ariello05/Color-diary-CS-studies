package com.example.colordiary2

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_color.*
import android.R.layout
import android.graphics.Color
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener





class ColorActivity : AppCompatActivity() {

    lateinit var colorSet:ColorSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val mu :HashSet<String>
        val sp = getSharedPreferences("MainResource", Context.MODE_PRIVATE)
        mu = sp.getStringSet(getString(R.string.colorPresets),HashSet<String>()) as HashSet<String>//Get list of known colorSets

        val adapter = ArrayAdapter<String>(
            this, layout.simple_spinner_item, mu.toList()
        )

        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item)
        spinnerPresets.adapter = adapter

        val layoutManager = GridLayoutManager(this, 2)
        listViewColorSet.layoutManager = layoutManager

        colorSet = ColorSet(this)
        //colorSet.loadSet(getNameOfSelectedPreset()!!)

        var myAdapter= ColorActivityArrayAdapter(this, colorSet)
        listViewColorSet.adapter = myAdapter
        listViewColorSet.addItemDecoration(GridLayoutDecorator(2,50,false))

        listViewColorSet.setHasFixedSize(false)

        spinnerPresets.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                colorSet.loadSet(mu.elementAt(position))
                myAdapter.notifyDataSetChanged()

            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }
        }

        val oldName= getNameOfSelectedPreset()!!
        for(str: String in mu){
            if(str == oldName){
                spinnerPresets.setSelection(mu.indexOf(str))
                break
            }
        }

    }

    override fun onStop() {
        val sp = getSharedPreferences("MainResource", Context.MODE_PRIVATE)
        val edit = sp.edit()!!
        edit.putString(getString(R.string.currentColorSet), colorSet.name)
        edit.apply()

        super.onStop()
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