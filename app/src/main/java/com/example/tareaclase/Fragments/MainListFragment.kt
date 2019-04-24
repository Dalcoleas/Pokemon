package com.example.tareaclase.Fragments

import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.service.autofill.Dataset
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentContainer
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tareaclase.Adapters.PokemonAdapter
import com.example.tareaclase.Models.Pokemon
import com.example.tareaclase.MyPokemonAdapter
import com.example.tareaclase.R
import java.util.zip.Inflater

class MainListFragment: Fragment() {

    private lateinit var pokemonAdapter: MyPokemonAdapter
    private lateinit var pokemons : ArrayList<Pokemon>

    companion object{
        fun newInstance(dataset: ArrayList<Pokemon>) : MainListFragment{
            val newFragment = MainListFragment()
            newFragment.pokemons = dataset
            return newFragment
        }
    }

    interface NewPokemonListener{

        fun managePortraitItemClick(pokemon: Pokemon)

        fun manageLandscapeItemClick (pokemon: Pokemon)
    }

    /*override fun onCreate(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.activity_main, container, false)

        //if(savedInstanceState != null) pokemons = savedInstanceState.

        initRecyclerView(resources.configuration.orientation, view)

        return view
    }

    fun initRecyclerView(orientation: Int, container: View){
        val linearLayoutManager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            pokemonAdapter = PokemonAdapter(pokemons, {pokemon:Pokemon ->})
        }
    }*/
}