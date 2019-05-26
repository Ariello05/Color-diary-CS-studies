package com.example.colordiary2

import java.io.Serializable

class HourPlan(var hour: Int, var minutes: Int): Serializable{
     var nameOfActivity: String
     var nameOfColor: String
     var minutesString=""
     var hourString=""

    init {
        this.nameOfActivity = ""
        this.nameOfColor = "basicColor"
        updateStrings()

    }

    fun updateStrings(){
        minutesString=minutes.toString()
        when(minutesString){
            "0"->minutesString="00"
        }
        this.hourString=hour.toString()
    }

}