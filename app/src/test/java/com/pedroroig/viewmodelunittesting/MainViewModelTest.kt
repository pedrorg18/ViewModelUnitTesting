package com.pedroroig.viewmodelunittesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify


class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    private val observer: Observer<List<Film>> = mock()

    private inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)

    @Before
    fun before() {
        viewModel = MainViewModel()
        viewModel.filmsLiveData.observeForever(observer)
    }

    @Test
    fun fetchFilms_Assert() {
        viewModel.fetchFilms()

        val captor = argumentCaptor<List<Film>>()
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(3, value.size)

            with(value.first()) {
                assertEquals(id, 1)
                assertEquals(year, 1991)
                assertEquals(name, "Terminator 2: Judgment Day")
                assertEquals(director, "James Cameron")
            }

            with(value[1]) {
                assertEquals(id, 2)
                assertEquals(year, 1985)
                assertEquals(name, "Back to the Future")
                assertEquals(director, "Robert Zemeckis")
            }

            with(value[2]) {
                assertEquals(id, 3)
                assertEquals(year, 1980)
                assertEquals(name, "Star Wars. Episode V: The Empire Strikes Back")
                assertEquals(director, "Irvin Kershner")
            }
        }
    }
}