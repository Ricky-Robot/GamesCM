package com.amaurypm.gamescm.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.amaurypm.gamescm.R
import com.amaurypm.gamescm.databinding.ActivityDetailsBinding
import com.amaurypm.gamescm.model.GameDetail
import com.amaurypm.gamescm.network.GamesApi
import com.amaurypm.gamescm.network.RetrofitService
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val id = bundle?.getString("id", "0")

        val call = RetrofitService.getRetrofit()
            .create(GamesApi::class.java)
            .getGameDetail(id)

        call.enqueue(object: Callback<GameDetail>{
            override fun onResponse(call: Call<GameDetail>, response: Response<GameDetail>) {

                with(binding) {

                    pbConexion.visibility = View.INVISIBLE

                    tvTitle.text = response.body()?.title

                    Glide.with(this@DetailsActivity)
                        .load(response.body()?.image)
                        .into(ivImage)

                    tvLongDesc.text = response.body()?.longDesc
                }

            }

            override fun onFailure(call: Call<GameDetail>, t: Throwable) {
                binding.pbConexion.visibility = View.INVISIBLE
                Toast.makeText(this@DetailsActivity,
                    "No hay conexión disponible",
                    Toast.LENGTH_SHORT)
                    .show()
            }

        })


    }
}