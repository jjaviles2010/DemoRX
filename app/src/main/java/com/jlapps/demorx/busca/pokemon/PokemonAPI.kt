package com.jlapps.demorx.busca.pokemon

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonAPI {

    @GET("/api/v2/pokemon")
    fun listaPokemons(): Observable<PokemonResponse>

    @GET("/api/v2/pokemon/{nomePokemon}")
    fun buscarPor(@Path("nomePokemon") nomePokemon: String) : Observable<Pokemon>
}