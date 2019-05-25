package com.example.colordiary2

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val REQUEST= 1234
    private lateinit var colorSet : ColorSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example usage of colorSet
        //colorSet = ColorSet(applicationContext)//or this
        //colorSet.insert(Color.parseColor("#000000"), "Sleep")
        //colorSet.insert(Color.parseColor("#FFFFFF"), "Living")
        //colorSet.saveSet("NewSet")
        //colorSet.print()
        //colorSet.loadSet("NewSet")
        //colorSet.print()
        //ButtonEdit.setBackgroundColor(colorSet.getColor("Sleep"))
        //ButtonEdit.setTextColor(colorSet.getColor("Living"))
        if(getNameOfSelectedPreset() == "" || hasAnySavedPresets()){
            definePresets()
        }
    }

    fun enterEditActivity(v: View){
        val i = Intent(this, DataSearch::class.java)
        startActivityForResult(i, REQUEST)

    }

    fun enterStatisticsActivity(v: View){
        //TODO: Switch into statisticsActivity
    }

    fun enterColorsActivity(v: View){
        val i = Intent(this, ColorActivity::class.java)
        startActivity(i)

        //TODO: Switch into colorsActivity
        //;>
    }

    private fun hasAnySavedPresets():Boolean{
        val mu :HashSet<String>
        val sp = getSharedPreferences("MainResource", Context.MODE_PRIVATE)
        mu = sp.getStringSet(getString(R.string.colorPresets),HashSet<String>()) as HashSet<String>//Get list of known colorSets
        return !mu.isNullOrEmpty()
    }

    private fun definePresets() {
        val sp = getSharedPreferences("MainResource", Context.MODE_PRIVATE)
        val edit = sp.edit()!!
        val mu = HashSet<String>()
        val one = ColorSet(applicationContext, "Primary")
        val two = ColorSet(applicationContext, "Secondary")

        one.insert(Color.parseColor("#000000"), "Sleep")
        one.insert(Color.parseColor("#FFFFFF"), "Living")
        two.insert(Color.parseColor("#000000"), "Sleep")
        two.insert(Color.parseColor("#FF0000"), "Living")

        mu.add(one.name)
        mu.add(two.name)

        edit.putStringSet(getString(R.string.colorPresets), mu)
        edit.apply()
    }

    fun getNameOfSelectedPreset(): String?{
        val sp = getSharedPreferences("MainResource", Context.MODE_PRIVATE)
        return sp.getString(getString(R.string.currentColorSet),"")//Loads name of main color set in program
    }

}
