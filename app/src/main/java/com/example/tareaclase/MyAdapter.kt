package com.example.tareaclase

import com.example.tareaclase.Models.Pokemon

interface MyPokemonAdapter{
    fun changeDataSet(newDataSet : List<Pokemon>)
}