package com.example.colordiary2

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

import android.app.Activity
import android.content.Context

/**
 *
 * Writes/reads an object to/from a private local file
 *
 *
 */
object LocalPersistence {


    /**
     *
     * @param context
     * @param object
     * @param filename
     */
    fun writeObjectToFile(context: Context, `object`: Any, filename: String) {

        var objectOut: ObjectOutputStream? = null
        try {

            val fileOut = context.openFileOutput(filename, Activity.MODE_PRIVATE)
            objectOut = ObjectOutputStream(fileOut)
            objectOut.writeObject(`object`)
            fileOut.fd.sync()

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close()
                } catch (e: IOException) {
                    // do nowt
                }

            }
        }
    }


    /**
     *
     * @param context
     * @param filename
     * @return
     */
    fun readObjectFromFile(context: Context, filename: String): Any? {

        var objectIn: ObjectInputStream? = null
        var `object`: Any? = null
        try {

            val fileIn = context.applicationContext.openFileInput(filename)
            objectIn = ObjectInputStream(fileIn)
            `object` = objectIn.readObject()

        } catch (e: FileNotFoundException) {
            // Do nothing
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close()
                } catch (e: IOException) {
                    // do nowt
                }

            }
        }

        return `object`
    }

}