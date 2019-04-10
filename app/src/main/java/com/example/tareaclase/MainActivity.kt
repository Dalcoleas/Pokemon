package com.example.tareaclase

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.tareaclase.Models.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AssyncTaskHandleJson().execute()

    }

    inner class AssyncTaskHandleJson : AsyncTask<String,String,String>(){
        override fun doInBackground(vararg p0: String?): String {
            val text :String
            val url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=100"
            val conn = URL(url).openConnection() as HttpURLConnection

            try{
                conn.connect()
                text = conn.inputStream.use { it.reader().use{reader-> reader.readText()} }
                Log.d("msg", "URL verificada con éxito!")
                Log.d("msg", text)

            } finally {
                conn.disconnect()
            }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }

    }

    private fun handleJson(result : String?) {

        val `object` = JSONObject(result)
        val pokemon:JSONArray = `object`.getJSONArray("results")

        val pokemons:MutableList<Pokemon> = MutableList(100){
            i-> Pokemon(i, JSONObject(pokemon.getString(i)).getString("name"))
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = PokemonAdapter(pokemons, {item : Pokemon->itemClicked(item)})

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
        startActivity(intent)
    }

}