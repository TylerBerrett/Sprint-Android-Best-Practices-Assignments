package com.tylerb.pokemonsearch

import dagger.Module
import dagger.Provides
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object PokemonObject {
    private const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"

    @Singleton
    @Provides
    fun retrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun getPokemon(retrofitInstance: Retrofit) = retrofitInstance.create(PokemonGetApi::class.java)
}