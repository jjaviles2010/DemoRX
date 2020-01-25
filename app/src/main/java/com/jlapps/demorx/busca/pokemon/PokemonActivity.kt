package com.jlapps.demorx.busca.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.jlapps.demorx.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

    private val api = PokemonService()
    private val compositeDisposable = CompositeDisposable()
    private val pokemons = arrayListOf<Pokemon>()
    private lateinit var pokemonAdapter : PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        pokemonAdapter = PokemonAdapter(this, pokemons)
        initViews()

        compositeDisposable.add(
            api.loadPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    pokemons.add(it)
                },{

                },{
                    pokemonAdapter.notifyDataSetChanged()
                })
        )
    }

    private fun initViews() {
        rvPokemons.adapter = pokemonAdapter
        rvPokemons.layoutManager = GridLayoutManager(this, 3)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
