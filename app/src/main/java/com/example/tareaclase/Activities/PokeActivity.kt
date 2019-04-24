package com.example.tareaclase.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tareaclase.R
import kotlinx.android.synthetic.main.activity_poke.*


class PokeActivity : AppCompatActivity() {

    var name:String = ""
    var numb:Int = 0
    var pos:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke)

        val mIntent = intent

        if(mIntent!=null){
            numb = mIntent.getIntExtra("id",numb)
            name = mIntent.getStringExtra("name")
            pos = numb + 1
            Glide.with(baseContext).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pos.png").into(poke_img_id)
            poke_id.text = name.capitalize()

        }
    }
}
