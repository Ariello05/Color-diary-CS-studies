package com.example.colordiary2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StatisticsAdapter (context: Context, var activity: ArrayList<String>, var color: ArrayList<Int>, var amount: ArrayList<Int>) :
    ArrayAdapter<String>(context, R.layout.list_layout, activity) {

    // ViewHolder!!

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView

        if (view == null) {

            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.list_layout, parent, false)
        }

        var text = activity[position] + " " + amount[position]

        val colorID = color[position]
        val channelRed = Color.red(colorID)
        val channelGreen = Color.green(colorID)
        val channelBlue = Color.blue(colorID)

        val viewtext = view!!.findViewById<TextView>(R.id.textView)

        if(channelRed < 60 && channelGreen < 60 && channelBlue < 60)
        {
            viewtext.setTextColor(Color.parseColor("#FFFFFF"))
        }else if(channelRed > 195 && channelGreen > 195 && channelBlue > 195){
            viewtext.setTextColor(Color.parseColor("#000000"))
        }


        viewtext.text = text
        viewtext.setBackgroundColor(colorID)

        return view!!
    }

}