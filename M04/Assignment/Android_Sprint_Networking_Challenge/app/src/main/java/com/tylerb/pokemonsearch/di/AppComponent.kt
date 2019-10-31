package com.tylerb.pokemonsearch.di

import com.tylerb.pokemonsearch.MainActivity
import com.tylerb.pokemonsearch.PokemonGetApi
import com.tylerb.pokemonsearch.PokemonObject
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Component(modules = [PokemonObject::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)


}
