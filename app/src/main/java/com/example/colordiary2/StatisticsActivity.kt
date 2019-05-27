package com.example.colordiary2

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_statistics.*

class StatisticsActivity : AppCompatActivity(){

    lateinit var listView: ListView
    lateinit var listAdap: ListAdapter

    lateinit var colors: ColorSet

    lateinit var aktywnosci: ArrayList<String>
    lateinit var kolory: ArrayList<Int>

    var amount = ArrayList<Int>()
    var sum = 0

    var pointer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        colors = ColorSet(this)
        colors.loadSet(getNameOfSelectedPreset()!!)

        aktywnosci = colors.getActivityNameArrayList()
        kolory = colors.getColorIdArrayList()

        initArray()
        initList()
        updateView()
    }

    var hoursOfDay=Array<HourPlan>(48){HourPlan(0,0)}
    var days=ArrayList<Pair<String, Array<HourPlan>>>()

    fun initArray() {

        for(i in 0..5) {
            amount.add(0)
        }

        var ar = ArrayList<String>()

        try{

            ar = LocalPersistence.readObjectFromFile(this,"VisitedDates") as ArrayList<String>

            for(i in 0..ar.size-1) {

                var fileName = ar[i]

                days=LocalPersistence.readObjectFromFile(this, fileName) as ArrayList<Pair<String, Array<HourPlan>>>
                hoursOfDay = days[findDay(fileName)].second

                for(j in 0..hoursOfDay.size-1) {


                    for(k in 0..aktywnosci.size-1) {

                        if (hoursOfDay[j].nameOfActivity == aktywnosci[k]) {
                            amount[k]++
                            sum++
                        }

                    }

                }

            }

        }
        catch(e:TypeCastException){ }


    }

    fun findDay(fm :String):Int{
        var i=0
        while(i<days.size-1 && days.get(i).first!=fm) {

            i++
        }
        return i
    }

    fun initList() {

        listView = findViewById<ListView>(R.id.activityList)

        listAdap = StatisticsAdapter(this,aktywnosci,kolory,amount)
        listView.adapter = listAdap

        listView.setOnItemClickListener { parent, view, position, id ->
            pointer = position
            updateView()
        }


    }

    fun updateView() {

        allHours.text = sum.toString()
        aktywnosc.text = aktywnosci[pointer] + ": "

        var prc: Float = amount[pointer].toFloat()/sum.toFloat()*100

        percentage.text = prc.toString()


    }

    fun getNameOfSelectedPreset(): String?{
        val sp = getSharedPreferences("MainResource", Context.MODE_PRIVATE)
        return sp.getString(getString(R.string.currentColorSet),"")//Loads name of main color set in program
    }

}