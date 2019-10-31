package com.tylerb.pokemonsearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonGetApi {

    @GET("{name-id}")
    fun getPokemonByNameOrID(@Path("name-id") pokemonNameNumber: String): Call<Pokemon>

}


