package com.jlapps.demorx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clubesObservable = Observable.just("Palmeiras", "São Paulo", "Santos", "Corinthians")

        compositeDisposable.add(clubesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.startsWith("P") }
            .subscribeWith(getClubesObserver()))

        compositeDisposable.add(clubesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.startsWith("P") }
            .map { it.toUpperCase() }
            .subscribeWith(getClubesLetraMaiusculaObserver()))
    }

    private fun getClubesObserver() : DisposableObserver<String> {
        return object : DisposableObserver<String>(){
            override fun onComplete() {
                Log.d("Clubes", "onComplete")
            }

            override fun onNext(t: String) {
                Log.d("Clubes", t)
            }

            override fun onError(e: Throwable) {
                Log.d("Clubes", "OnError")

            }
        }
    }

    private fun getClubesLetraMaiusculaObserver() : DisposableObserver<String> {
        return object : DisposableObserver<String>(){
            override fun onComplete() {
                Log.d("Clubes", "onComplete")
            }

            override fun onNext(t: String) {
                Log.d("Clubes", t)
            }

            override fun onError(e: Throwable) {
                Log.d("Clubes", "OnError")

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
