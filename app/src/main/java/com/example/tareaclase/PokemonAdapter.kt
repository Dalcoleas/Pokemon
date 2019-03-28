package com.example.tareaclase

import com.example.tareaclase.Models.Pokemon
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pokemon_list.view.*

class PokemonAdapter(val items: List<Pokemon>, val clickListener: (Pokemon)-> Unit) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    // TODO: Para contar elementos creados

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list, parent, false)

        /*
         * TODO: Muestra el valor de contador de view creadas solo se hace aqui, para asegurar
         * que solo se asigne el valor aqui
         */
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(items[position],clickListener)

        //Glide.with(holder.itemView.context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$position.png").into(holder.itemView.img_poke)

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item:  Pokemon, clickListener: (Pokemon) -> Unit) = with(itemView){
            tv_pokemon_name.text = item.name
            tv_pokemon_type.text = item.type
            itemView.setOnClickListener{(clickListener(item))}

        }
    }

}