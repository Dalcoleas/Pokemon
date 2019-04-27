package com.example.tareaclase.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tareaclase.Models.Pokemon
import com.example.tareaclase.R
import kotlinx.android.synthetic.main.pokemon_details.view.*

class MainDetailsFragment : Fragment(){
    var pokemon = Pokemon(0," ")

    companion object{
        fun newInstance(item :Pokemon) : MainDetailsFragment{
            val newFragment = MainDetailsFragment()
            newFragment.pokemon = item
            return newFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.pokemon_details, container, false)
        bindData(view)
        return view
    }

    fun bindData ( view: View){
        view.name_fragment.text = pokemon.name

        Glide.with(view.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id+1}.png")
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.image_fragment)

    }
}