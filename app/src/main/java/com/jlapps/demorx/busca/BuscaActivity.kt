package com.jlapps.demorx.busca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.jlapps.demorx.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_busca.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class BuscaActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    private var dataManager = ClubeDataManager()

    private lateinit var adapter : ClubeAdapter

    private lateinit var clubes : ArrayList<Clube>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca)

        clubes = arrayListOf()

        initView()

        compositeDisposable.add(
            RxTextView.textChangeEvents(etClube)
                .skipInitialValue()
                .debounce (1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(pesquisaClubeObserver())
        )
    }

    private fun pesquisaClubeObserver(): DisposableObserver<TextViewTextChangeEvent> {
        return object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                filter(textViewTextChangeEvent.text().toString().toLowerCase(Locale.getDefault()))
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        }
    }

    private fun filter(inputText: String) {
        val clubesFiltrados = ArrayList<Clube>()

        for (clube in clubes) {
            val nomeClube = clube.nome
            if (nomeClube.toLowerCase(Locale.getDefault()).contains(inputText)) {
                clubesFiltrados.add(clube)
            }
        }
        adapter.filter(clubesFiltrados)
    }



    private fun initView() {
        rvClubes.layoutManager = LinearLayoutManager(this)
        rvClubes.setHasFixedSize(true)

        clubes.addAll(dataManager.getClubes())

        adapter = ClubeAdapter(this, clubes)
        rvClubes.adapter = adapter
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}


