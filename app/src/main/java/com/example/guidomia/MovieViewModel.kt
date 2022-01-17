package com.example.guidomia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.guidomia.db.Movie
import com.example.guidomia.db.MovieRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()
    val movie = MutableLiveData<Movie>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getAllMovies()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    viewModelScope.launch {
                        repository.insert(filterMovies(response.body()!!.results))
                    }
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun filterMovies(items: List<Movie>): ArrayList<Movie> {
        val movies: ArrayList<Movie> = ArrayList()
        for (item in items) {
            if (item.kind == "feature-movie") {
                movies.add(item)
            }
        }
        return movies

    }

    fun getAll() = liveData {
        repository.movies.collect {
            emit(it)
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}