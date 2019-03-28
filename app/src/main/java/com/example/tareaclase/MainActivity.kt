package com.example.tareaclase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.tareaclase.Models.Pokemon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    fun initRecycler() {

        var pokemon: MutableList<Pokemon> = MutableList(10) { i ->
            Pokemon(i,"Name" + i, "Type " + i)
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = PokemonAdapter(pokemon, {item : Pokemon->itemClicked(item)})

        rv_pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    fun itemClicked(item: Pokemon){
        Toast.makeText(this, "Clicked: ${item.name}", Toast.LENGTH_LONG).show()

        val intent = Intent(this, PokeActivity::class.java)

        intent.putExtra("name", item.name)
        intent.putExtra("type", item.type)
        startActivity(intent)
    }
}
