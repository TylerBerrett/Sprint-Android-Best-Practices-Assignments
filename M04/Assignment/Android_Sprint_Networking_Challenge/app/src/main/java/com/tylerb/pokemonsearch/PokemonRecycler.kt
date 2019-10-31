package com.tylerb.pokemonsearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view.view.*

class PokemonRecycler(val list: ArrayList<PassPokemon>) : RecyclerView.Adapter<PokemonRecycler.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(list[position].sprites).into(holder.pokemonImage)
        holder.pokemonName.text = list[position].name
        holder.pokemonType.text = list[position].type.toString().replace("[", "").replace("]", "")

        holder.all.setOnClickListener {
            val intent = Intent(it.context, PokemonEdit::class.java)
            intent.putExtra(MainActivity.POKEMON_EDIT, list[position])
            startActivity(it.context, intent, null)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val all = view.card_all
        val pokemonImage = view.card_image
        val pokemonName = view.card_tv_name
        val pokemonType = view.card_tv_type
    }

}