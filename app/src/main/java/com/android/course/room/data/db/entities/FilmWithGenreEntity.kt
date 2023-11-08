package com.android.course.room.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class FilmWithGenreEntity(
    @Embedded val film: FilmEntity,
    @Relation(parentColumn = "genreId", entityColumn = "id") val genreEntity: GenreEntity
)