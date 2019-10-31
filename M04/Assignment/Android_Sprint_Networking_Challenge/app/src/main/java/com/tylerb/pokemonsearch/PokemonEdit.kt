package com.tylerb.pokemonsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_edit.*

class PokemonEdit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_edit)

        title = "About Pokemon"

        val pokemon = intent.getSerializableExtra(MainActivity.POKEMON_EDIT) as PassPokemon


        val type = pokemon.type.toString().replace("[", "").replace("]", "")
        val ability = pokemon.ability.toString().replace("[", "").replace("]", "")

        Picasso.get().load(pokemon.sprites).into(edit_pokemon_image)
        edit_pokemon_name.text =    "Name: ${pokemon.name}"
        edit_pokemon_id.text =      "Pokedex Location: ${pokemon.id}"
        edit_pokemon_ability.text = "Abilities: $ability"
        edit_pokemon_type.text =    "Type(s): $type"

        edit_pokemon_button.setOnClickListener {
            PokemonAbout.pokemonList.remove(pokemon)
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}
