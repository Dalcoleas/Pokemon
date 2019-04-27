package com.example.tareaclase.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tareaclase.Models.Pokemon
import com.example.tareaclase.R
import kotlinx.android.synthetic.main.activity_poke.*


class PokeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke)

        val mIntent = intent

        if(mIntent!=null){
            val receiver : Pokemon = intent?.extras?.getParcelable("POKE") ?: Pokemon(0, " ")
            init(receiver)
        }
    }

    fun init(pokemon: Pokemon){

        Glide.with(this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id+1}.png").into(poke_img_id)
        poke_id.text = pokemon.name.capitalize()
    }
}
