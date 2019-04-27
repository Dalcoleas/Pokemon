package com.example.tareaclase.Fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tareaclase.Adapters.PokemonAdapter
import com.example.tareaclase.Models.Pokemon
import com.example.tareaclase.R
import com.example.tareaclase.Utils.AppConstants
import com.example.tareaclase.Utils.MyPokemonAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.pokemon_recycler.view.*
import java.lang.RuntimeException

class MainListFragment: Fragment() {

    private lateinit var pokemonAdapter: MyPokemonAdapter
    private lateinit var pokemons : ArrayList<Pokemon>
    var listenerTools : ListenerTools? = null

    companion object{
        fun newInstance(dataset: ArrayList<Pokemon>) : MainListFragment{
            val newFragment = MainListFragment()
            newFragment.pokemons = dataset
            return newFragment
        }
    }

    interface ListenerTools{

        fun managePortraitItemClick(pokemon: Pokemon)

        fun manageLandscapeItemClick (pokemon: Pokemon)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.pokemon_recycler, container, false)

        if(savedInstanceState != null) pokemons = savedInstanceState.getParcelableArrayList<Pokemon>(AppConstants.MAIN_LIST_KEY)!!

        initRecyclerView(resources.configuration.orientation, view)

        return view
    }

    fun initRecyclerView(orientation: Int, container: View){
        val linearLayoutManager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            pokemonAdapter = PokemonAdapter(pokemons, {pokemon:Pokemon -> listenerTools?.managePortraitItemClick(pokemon)})
        }

        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            pokemonAdapter = PokemonAdapter(pokemons, {pokemon:Pokemon -> listenerTools?.manageLandscapeItemClick(pokemon)})
        }

        container.rec_pokemon_list.adapter = pokemonAdapter as PokemonAdapter

        container.rec_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    fun updatePokemonAdapter ( pokemonList: ArrayList<Pokemon>){
        pokemonAdapter.changeDataSet(pokemonList)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is ListenerTools){
            listenerTools = context
        } else{
            throw RuntimeException("Necesario implementar interfaz!")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.MAIN_LIST_KEY,pokemons)
        super.onSaveInstanceState(outState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerTools = null
    }

}