package com.pedroroig.viewmodelunittesting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val filmsLiveData = MutableLiveData<List<Film>>()

    fun fetchFilms() {
        val films = listOf(
            Film(1,
                1991,
                "Terminator 2: Judgment Day",
                "James Cameron"),
            Film(2,
                1985,
                "Back to the Future",
                "Robert Zemeckis"),
            Film(3,
                1980,
                "Star Wars. Episode V: The Empire Strikes Back",
                "Irvin Kershner")
        )

        filmsLiveData.value = films
    }
}