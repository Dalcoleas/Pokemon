package com.example.tareaclase.Utils

import com.example.tareaclase.Models.Pokemon

object AppConstants{
    val dataset_saveinstance_key = "CLE"
    val MAIN_LIST_KEY = "key_list_coin"
}

interface MyPokemonAdapter{
    fun changeDataSet(newDataSet : ArrayList<Pokemon>)
}