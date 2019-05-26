package com.example.colordiary2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_color_edit.*
import android.graphics.Color
import android.widget.SeekBar



class ColorEdit : Fragment() {

    private var seekR:Int = 0
    private var seekG:Int = 0
    private var seekB:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val onSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar) {}

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            when (seekBar.id) {
                switchRed.id -> seekR = progress
                switchGreen.id -> seekG = progress
                switchBlue.id -> seekB = progress
            }

            colorize()
        }
    }

    private fun colorize(){
        val colorID = Color.rgb(seekR,seekG,seekB)
        colorArea.setBackgroundColor(colorID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity?.intent != null){
            val cl = activity?.intent!!.getIntExtra("color",0)
            colorArea.setBackgroundColor(cl)
            editText.setText(activity?.intent!!.getStringExtra("text"))

            seekR = Color.red(cl)
            seekG = Color.green(cl)
            seekB = Color.blue(cl)

            switchRed.max = 255
            switchGreen.max = 255
            switchBlue.max =255

            switchRed.progress = seekR
            switchGreen.progress = seekG
            switchBlue.progress = seekB

        }
        buttonApply.setOnClickListener {
            val ac = getActivity() as EditColorActivity
            ac.save(seekR,seekG,seekB,editText.text.toString())
        }

        buttonRemove.setOnClickListener {
            val ac = getActivity() as EditColorActivity
            ac.remove()
        }

        switchRed.setOnSeekBarChangeListener(onSeekBarChangeListener)
        switchGreen.setOnSeekBarChangeListener(onSeekBarChangeListener)
        switchBlue.setOnSeekBarChangeListener(onSeekBarChangeListener)
    }

}
