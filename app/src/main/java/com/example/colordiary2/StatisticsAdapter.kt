package com.example.colordiary2

import android.content.Context
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

        var text = activity[position] + " " + color[position] + " " + amount[position]

        view!!.findViewById<TextView>(R.id.textView).text = text

        return view!!
    }

}