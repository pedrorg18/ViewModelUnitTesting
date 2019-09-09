package com.pedroroig.viewmodelunittesting

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = MainViewModel()
        viewModel.fetchFilms()
        viewModel.filmsLiveData.observe(this, Observer { filmList ->
            filmList.forEach {
                textView.text = "${textView.text}\n\n${formatFilm(it)}"
            }

        })
    }

    private fun formatFilm(film: Film) =
        with(film) {
            "$name, $year\n$director"
        }
}
