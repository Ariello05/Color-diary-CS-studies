package com.example.colordiary2

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_color.*
import kotlinx.android.synthetic.main.fragment_color_edit.*

class EditColorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_color)
    }

    fun end(r:Int, g:Int, b:Int, str:String){
        val returnIntent = Intent()
        returnIntent.putExtra("text", str)
        returnIntent.putExtra("color", Color.rgb(r,g,b))
        setResult(Activity.RESULT_OK,returnIntent)

        finish()
    }

}
