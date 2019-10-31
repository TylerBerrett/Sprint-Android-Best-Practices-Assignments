package com.tylerb.pokemonsearch

import java.io.Serializable

data class Sprites (
    val front_default: String
)

data class ability(
    val name: String
)

data class ablityList(
    val ability: ability
)

data class type(
    val name: String
)

data class typeList(
    val type: type
)


data class Pokemon (
    val name: String,
    val sprites: Sprites,
    val id: Int,
    val abilities: List<ablityList>,
    val types: List<typeList>
)

data class PassPokemon(
    var name: String,
    var sprites: String,
    var id: Int,
    var ability: ArrayList<String>,
    var type: ArrayList<String>
): Serializable