package com.example.colordiary2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ColorActivityArrayAdapter(var context: Context, var colorList: ColorSet) : RecyclerView.Adapter<ColorActivityArrayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.colorset_layout,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return colorList.getSize()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: ColorActivityArrayAdapter.ViewHolder, i: Int) {
        viewHolder.color.setBackgroundColor(colorList.getPair(i).first)
        viewHolder.text.text = colorList.getPair(i).second

        viewHolder.color.setOnClickListener {
            val ac = context as Activity
            var myIntent = Intent(ac, EditColorActivity::class.java)
            myIntent.putExtra("text",colorList.getPair(i).second)
            myIntent.putExtra("color",colorList.getPair(i).first)
            colorList.indexHelper = i
            ac.startActivityForResult(myIntent,1)
        }
/*
        viewHolder.img.setOnTouchListener(object: View.OnTouchListener {

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                v?.performClick()
/*
                if(event!!.action == MotionEvent.ACTION_UP) {
                    val ac = context as Activity
                    var myIntent = Intent(ac, ClickedActivity::class.java)
                    myIntent.putExtra("imageID",galleryList[i].image_id)
                    myIntent.putExtra("imageRating",galleryList[i].rating)
                    myIntent.putExtra("imageDescription",galleryList[i].description)
                    ac.startActivityForResult(myIntent,1)
                }
                */
                return true
            }

        })
        */
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var color : Button = itemView.findViewById(R.id.ButtonColorColorSet)
        var text : TextView = itemView.findViewById(R.id.TextActivityColorSet)
    }

}