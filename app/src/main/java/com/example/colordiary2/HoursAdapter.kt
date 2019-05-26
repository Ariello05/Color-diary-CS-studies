package com.example.colordiary2

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.content.DialogInterface
import android.graphics.Color
import com.google.gson.Gson


class HoursAdapter (private val context: Context, private var hours: Array<HourPlan>, private val colorSetFileName: String, private val dateToEdit: String, private val dateFileName:String) : BaseAdapter() {
    var colorsets=ArrayList<Pair<Int, String>>()
    var activities=ArrayList<Pair<Int, String>>()
    var hoursOfDay=Array<HourPlan>(48){HourPlan(0,0)}
    var days=ArrayList<Pair<String, Array<HourPlan>>>()
    init{
    loadDayPlan()

        //todo corect activities

        activities.add(Pair(1, "sleep"))
        activities.add(Pair(2, "learning"))
        activities.add(Pair(3, "chill"))
        activities.add(Pair(4, "training"))
    }

    private var colors = arrayListOf<Pair<Int,String>>()
    override fun getCount(): Int {
        return hours.size
    }


    override fun getItemId(position: Int): Long {
        return 0
    }


    override fun getItem(position: Int): Any? {
        return null
    }

   override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        val TextView = TextView(context)
        TextView.text = hours[position].hourString+":"+hours[position].minutesString+"          "+hours[position].nameOfActivity
       TextView.textSize=30f
       TextView.setOnClickListener {

           val activity = Array<String>(activities.size) {""}
           for(i in 0..activity.size-1){
               activity[i]= activities[i].second
           }

           val builder = AlertDialog.Builder(this.context)
           builder.setTitle("Pick acivity")
           builder.setItems(activity, DialogInterface.OnClickListener { dialog, which ->
               hours[position].nameOfActivity=activity[which]
               TextView.text = hours[position].hourString+":"+hours[position].minutesString+"          "+hours[position].nameOfActivity
                TextView.setBackgroundColor(Color.parseColor(findColor(hours[position].nameOfActivity)))
               saveDayPlan()
           })
           builder.show() }



       //todo set corect color
       TextView.setBackgroundColor(Color.parseColor(findColor(hours[position].nameOfActivity)))
        return TextView
    }
    fun findColor(nameofactivity:String):String{
        var j=0
        var k=0
        //todo corect this
        for (j in 0..activities.size-1){
        if (activities[j].second==nameofactivity)
            k=j
        }
        var i=0
        loadSet(colorSetFileName)
        for (i in 0..colors.size-1){
            if (colors[i].first==k)
                return colors[i].second
        }
        return colors[0].second

    }


    /**
     * Loads from local file colorSet
     * @param setName name of the set to load
     */
    fun loadSet(setName:String){
        try{
        colors = LocalPersistence.readObjectFromFile(context,setName) as ArrayList<Pair<Int, String>>}
        catch (e: TypeCastException){
            colors.add(Pair(1, "#049500"))
            colors.add(Pair(2, "#800099"))
            colors.add(Pair(3, "#CC0323"))
            colors.add(Pair(4, "#036300"))
        }
    }
    fun loadDayPlan(){

        try{
        days=LocalPersistence.readObjectFromFile(context, dateFileName) as ArrayList<Pair<String, Array<HourPlan>>>
        }
        catch (e:TypeCastException){
            days.add(Pair(dateToEdit,hours ))
            //e.printStackTrace()
        }

        hoursOfDay = days[findDay()].second
        hours=hoursOfDay
        System.out.println(days[findDay()].second[0].nameOfActivity)

    }

    fun saveDayPlan(){
        hoursOfDay=hours
        days.set(findDay(), Pair(dateToEdit, hoursOfDay))
        LocalPersistence.writeObjectToFile(context, days, dateFileName)
    }
    fun findDay():Int{
        var i=0
        while(i<days.size-1 && days.get(i).first!=dateToEdit) {

            i++
        }
        return i
    }

}