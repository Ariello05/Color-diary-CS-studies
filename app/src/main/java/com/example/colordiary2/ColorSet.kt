package com.example.colordiary2

import android.content.Context
import android.util.Log
import java.lang.Exception


class ColorSet(private val context: Context) {
    private var colors = arrayListOf<Pair<Int,String>>()

    /**
     * Loads from local file colorSet
     * @param setName name of the set to load
     */
    fun loadSet(setName:String){
        colors = LocalPersistence.readObjectFromFile(context,setName) as ArrayList<Pair<Int, String>>
    }

    /**
     *  Saves to local files this colorSet
     * @param setName where to save current set
     */
    fun saveSet(setName:String){
        LocalPersistence.witeObjectToFile(context,colors,setName)
    }

    /**
     *      Gets the pair at index
     *
     *  @param index index of array
     *  @return pair at index
     */
    fun getPair(index:Int):Pair<Int,String>{
        return colors[index]
    }

    /**
     *  Sets the pair into array at index
     *  @param index index of array
     *  @param pair new Pair to be inserted
     */
    fun setPair(index:Int,pair: Pair<Int,String>){
        colors[index] = pair
    }

    /**
     *  Gets the color ID from the set
     * @param str expression of the pair<colorID,expression>
     * @return returns colorID if found
     * @throws Exception on not found
     */
    fun getColor(str:String):Int{
        for(c: Pair<Int,String> in colors){
            if(c.second == str)
                return c.first
        }
        throw Exception("No such expression in set!")
    }

    /**
     *  Inserts the colorID,expression pair
     * @param colorID int id of the RGB color
     * @param str expression of the pair<colorID,expression>
     * @throws Exception on duplicate value
     */
    fun insert(colorID: Int, str: String){
        for(c: Pair<Int,String> in colors){
            if(c.second == str || c.first == colorID) {
                throw Exception("Duplicate exression in set!")
            }
        }

        colors.add(Pair(colorID,str))
    }


    /**
     * Deletes pair
     * @param str expression of the pair<colorID,expression>
     */
    fun delete(str:String){
        for(c: Pair<Int,String> in colors){
            if(c.second == str) {
                colors.remove(c)
                break
            }
        }
    }

    /**
     * Prints color
     */
    fun print(){
        for(c: Pair<Int,String> in colors){
            Log.d("Diary", "Color of: ${c.second} \t is: ${c.first}")
        }
    }
}