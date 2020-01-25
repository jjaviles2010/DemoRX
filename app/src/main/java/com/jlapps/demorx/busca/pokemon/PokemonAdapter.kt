package com.jlapps.demorx.busca.pokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlapps.demorx.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_item.view.*

class PokemonAdapter(
    val context: Context,
    val pokemons: List<Pokemon> ) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.let {
            holder.bindView(pokemon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.pokemon_item, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(
            pokemon: Pokemon
        ) = with(itemView) {
            tvPokemon.text = pokemon.nome
            Picasso.get().load(pokemon.sprites.frontDefault).into(ivPokemon)
        }
    }
}
