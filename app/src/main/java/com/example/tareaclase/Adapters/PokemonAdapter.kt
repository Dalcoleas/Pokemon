package com.example.tareaclase.Adapters

import com.example.tareaclase.Models.Pokemon
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tareaclase.MyPokemonAdapter
import com.example.tareaclase.R
import kotlinx.android.synthetic.main.pokemon_list.view.*

class PokemonAdapter(var items: List<Pokemon>, val clickListener: (Pokemon)-> Unit) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(),
    MyPokemonAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position],clickListener)
    }

    override fun changeDataSet(newDataSet: List<Pokemon>) {
        this.items = newDataSet
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item:  Pokemon, clickListener: (Pokemon) -> Unit) = with(itemView){
            Glide.with(itemView.context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${item.id+1}.png").into(itemView.img_poke)
            tv_pokemon_name.text = item.name.capitalize()
            itemView.setOnClickListener{(clickListener(item))}

        }
    }


}