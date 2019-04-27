package com.example.tareaclase.Activities

import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.tareaclase.Models.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import com.example.tareaclase.Adapters.PokemonAdapter
import com.example.tareaclase.Fragments.MainDetailsFragment
import com.example.tareaclase.Fragments.MainListFragment
import com.example.tareaclase.R


class MainActivity : AppCompatActivity(), MainListFragment.ListenerTools {

    /*private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager*/

    private var pokemonList = ArrayList<Pokemon>()

    private lateinit var mainFragment : MainListFragment
    private lateinit var mainContentFragment : MainDetailsFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMainFragment()

    }

    override fun managePortraitItemClick(pokemon: Pokemon) {
        val pokeBundle = Bundle()
        pokeBundle.putParcelable("POKE",pokemon)
        startActivity(Intent(this,PokeActivity::class.java).putExtras(pokeBundle))
    }

    override fun manageLandscapeItemClick(pokemon: Pokemon) {
        mainContentFragment = MainDetailsFragment.newInstance(pokemon)
        changeFragment(R.id.land_main_cont_fragment, mainContentFragment)
    }

    fun initMainFragment(){
        mainFragment = MainListFragment.newInstance(pokemonList)

        val resource = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.main_fragment
        else{
            mainContentFragment = MainDetailsFragment.newInstance(Pokemon(0," "))

            changeFragment(R.id.land_main_cont_fragment, mainContentFragment)

            R.id.land_main_fragment
        }

        changeFragment(resource,mainFragment)

        AssyncTaskHandleJson().execute()
    }

    private fun changeFragment(id:Int, frag:Fragment){
        supportFragmentManager.beginTransaction().replace(id,frag).commit()
    }

    private inner class AssyncTaskHandleJson : AsyncTask<String,Void,String>(){
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

        val root = JSONObject(result)
        val results = root.getJSONArray("results")

        var  x = 0

        while( x < results.length()){
            val result = JSONObject(results[x].toString())
            var pokemon = Pokemon(x,result.getString("name"))

            pokemonList.add(pokemon)
            x++
        }

        mainFragment.updatePokemonAdapter(pokemonList)
    }


    /*fun itemClicked(item: Pokemon){
        //Toast.makeText(this, "Clicked: ${item.id}", Toast.LENGTH_LONG).show()
        val intent = Intent(this, PokeActivity::class.java)
        intent.putExtra("name", item.name)
        intent.putExtra("id", item.id)
        startActivity(intent)
    }*/

}