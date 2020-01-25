package com.jlapps.demorx.busca.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val results: List<Pokemon>
)

data class Pokemon(
    @SerializedName("name") val nome: String,
    val sprites: Sprites
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String
)