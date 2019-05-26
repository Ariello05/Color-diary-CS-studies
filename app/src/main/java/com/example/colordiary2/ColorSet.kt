package com.example.colordiary2

import android.content.Context
import android.util.Log
import java.lang.Exception



class ColorSet(private val context: Context, var name: String = "") {
    var indexHelper:Int = 0
    private var colors = arrayListOf<Pair<Int,String>>()

    /**
     * Loads from local file colorSet
     * @param setName name of the set to load
     */
    fun loadSet(setName:String){
        colors = LocalPersistence.readObjectFromFile(context,setName) as ArrayList<Pair<Int, String>>
        name = setName
    }

    /**
     *  Saves to local files this colorSet
     * @param setName where to save current set
     */

    fun saveSet(setName:String = name){
        LocalPersistence.writeObjectToFile(context,colors,setName)
    }

    override fun toString(): String {
        return name
    }

    /**
     * Gets ArrayList of ColorId
     * @return ArrayList<Int> of first elem in Arraylist<pair>
     */
    fun getColorIdArrayList():ArrayList<Int>{
        val intArray = ArrayList<Int>()
        for(c: Pair<Int,String> in colors){
            intArray.add(c.first)
        }
        return intArray
    }

    /**

     * Returns size of the arrayList
     */
    fun getSize():Int{
        return colors.size
    }

    /**
     * Gets ArrayList of Activity name
     * @return ArrayList<String> of second elem in Arraylist<pair>
     */
    fun getActivityNameArrayList():ArrayList<String>{
        val stringArray = ArrayList<String>()
        for(c: Pair<Int,String> in colors){
            stringArray.add(c.second)
        }
        return stringArray
    }

    /**
     *      Gets the pair at index
     *
     *  @param index index of array
     *  @return Pair<Int,String> at index
     */
    fun getPair(index:Int):Pair<Int,String>{
        return colors[index]
    }

    /**
     *  Updates the pair in array at index
     *  @param index index of array
     *  @param pair new Pair to be inserted
     */
    fun updatePair(index:Int,pair: Pair<Int,String>){
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