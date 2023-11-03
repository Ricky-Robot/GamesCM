package com.amaurypm.gamescm.model

import com.google.gson.annotations.SerializedName

/**
 * Creado por Amaury Perea Matsumura el 03/11/23
 */
data class Game(
    @SerializedName("id")
    var id: String?,
    @SerializedName("thumbnail")
    var thunmbnail: String?,
    @SerializedName("title")
    var title: String?
)
