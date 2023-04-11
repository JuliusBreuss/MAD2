package com.example.movieappmad23.widgets
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.getMovies

class MoviesViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
        get() = _movieList
    var idCount = 300000

    fun getFavMovieList(): List<Movie> {
        return _movieList.filter { it.isFavorite }
    }

    fun getMovieById(movieID: String): Movie {
        return (_movieList.filter { it.id == movieID })[0]
    }

    fun toggleFav(movieID: String) {
        for (movie in movieList) {
            if (movie.id == movieID) {
                movie.isFavorite = !movie.isFavorite
            }
        }
    }

    fun checkStringValid(toCheck: String): Boolean {
        return !toCheck.isEmpty()
    }

    fun checkFloatValid(toCheck: String): Boolean {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        return toCheck.matches(regex)
    }

    fun checkGenreValid(toCheck: List<ListItemSelectable>): Boolean {
        for (genre in toCheck) {
            if (genre.isSelected) {
                return true
            }
        }
        return false
    }

    fun addMovie(title: String, year: String, genres: List<ListItemSelectable>, director: String, actors: String, plot: String, rating: Float ){

        var fullid = "tt" + idCount
        val selectedGenres = mutableListOf<Genre>()
        for (genre in genres){
            if (genre.isSelected){
                selectedGenres.add(enumValueOf(genre.title))
            }
        }



        _movieList.add(Movie(id = fullid, title = title, year = year, genre = selectedGenres, director=director, actors = actors, plot = plot, rating = rating, images = listOf("https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg?20200913095930")))
        idCount++
    }
}