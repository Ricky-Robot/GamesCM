package com.amaurypm.gamescm.network

import com.amaurypm.gamescm.model.Game
import com.amaurypm.gamescm.model.GameDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Creado por Amaury Perea Matsumura el 03/11/23
 */
interface GamesApi {

    //cm/games/games_list.php
    @GET                //getGames("cm/games/games_list.php")
    fun getGames(
        @Url url: String?
    ): Call<ArrayList<Game>>

    //cm/games/game_detail.php?id=21357
    //cm/games/game_detail.php?id=21357&token=34798hjkjhbkjhdfs987897342hjk
    @GET("cm/games/game_detail.php")
    fun getGameDetail(                    //getGameDetail("21357", "34798hjkjhbkjhdfs987897342hjk")
        @Query("id") id: String?/*,
        @Query("token") token: String?*/
    ): Call<GameDetail>

    ///games/game_detail/410150
    ///games/game_detail/410150/amaury
    @GET("games/game_detail/{id}/{user}")
    fun getGameDetailApiary(             //getGameDetailApiary("410150", "amaury")
        @Path("id") id: String?,
        @Path("user") user: String?
    ): Call<GameDetail>

}