package com.truyentd.moviecompose.domain.repository

import com.truyentd.moviecompose.data.model.MovieData

interface MovieRepository {
    fun getMovies(): List<MovieData>
}
