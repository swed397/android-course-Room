package com.android.course.room

import com.android.course.room.data.db.entities.FilmEntity
import com.android.course.room.data.db.entities.FilmWithGenreEntity
import com.android.course.room.domain.FilmInfo
import com.android.course.room.domain.PreviewFilmInfo

const val FILM_INFO_KEY = "FILM_INFO"

fun FilmWithGenreEntity.toDto(): PreviewFilmInfo = PreviewFilmInfo(
    id = film.id!!,
    title = film.title,
    genre = genreEntity.name
)

fun FilmEntity.toDto(genre: String): FilmInfo = FilmInfo(
    title = title,
    genre = genre,
    year = year,
    authors = "",
    info = info ?: "",
    rate = rate ?: 0.0
)
