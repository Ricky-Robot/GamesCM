package com.amaurypm.gamescm.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amaurypm.gamescm.R
import com.amaurypm.gamescm.databinding.ActivityMainBinding
import com.amaurypm.gamescm.model.Game
import com.amaurypm.gamescm.network.GamesApi
import com.amaurypm.gamescm.network.RetrofitService
import com.amaurypm.gamescm.ui.adapters.GamesAdapter
import com.amaurypm.gamescm.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = RetrofitService.getRetrofit()
            .create(GamesApi::class.java)
            .getGames("cm/games/games_list.php")
            //.getGames("games/games_list")

        call.enqueue(object: Callback<ArrayList<Game>>{
            override fun onResponse(
                call: Call<ArrayList<Game>>,
                response: Response<ArrayList<Game>>
            ) {
                binding.pbConexion.visibility = View.INVISIBLE

                Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                val gamesAdapter = GamesAdapter(response.body()!!){ game ->
                    //Click en cada juego
                    //Toast.makeText(this@MainActivity, "Click en el juego: ${game.title}", Toast.LENGTH_SHORT).show()

                    val bundle = bundleOf(
                        "id" to game.id
                    )

                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)

                    intent.putExtras(bundle)

                    startActivity(intent)
                }

                binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)

                binding.rvMenu.adapter = gamesAdapter

            }

            override fun onFailure(call: Call<ArrayList<Game>>, t: Throwable) {
                binding.pbConexion.visibility = View.INVISIBLE
                Toast.makeText(this@MainActivity,
                    "No hay conexión disponible",
                    Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }
}