package com.example.colordiary2

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.content.DialogInterface




class HoursAdapter (private val context: Context, private val hours: Array<HourPlan>, private val colorSetName: String) : BaseAdapter() {


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
           //TODO: corect activities
           val colors = arrayOf("sleep", "learning", "chill", "other stuff")

           val builder = AlertDialog.Builder(this.context)
           builder.setTitle("Pick acivity")
           builder.setItems(colors, DialogInterface.OnClickListener { dialog, which ->
               hours[position].nameOfActivity=colors[which]
               TextView.text = hours[position].hourString+":"+hours[position].minutesString+"          "+hours[position].nameOfActivity
           })
           builder.show() }



       //todo set corect color
      // TextView.setBackgroundColor(colors[0].first)
        return TextView
    }


    /**
     * Loads from local file colorSet
     * @param setName name of the set to load
     */
    fun loadSet(setName:String){
        colors = LocalPersistence.readObjectFromFile(context,setName) as ArrayList<Pair<Int, String>>
    }

}