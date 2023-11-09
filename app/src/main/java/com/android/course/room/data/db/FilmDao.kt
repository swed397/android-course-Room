package com.android.course.room.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.android.course.room.data.db.entities.FilmEntity
import com.android.course.room.data.db.entities.FilmWithGenreEntity
import com.android.course.room.data.db.entities.GenreEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM films ")
    suspend fun getAllFilms(): List<FilmEntity>

    @Query("SELECT * FROM films ")
    @Transaction
    suspend fun getAllFilmsAndGenres(): List<FilmWithGenreEntity>

    @Query("SELECT * FROM genres ")
    suspend fun getAllGenres(): List<GenreEntity>

    @Insert
    suspend fun insertGenre(entity: GenreEntity)

    @Insert
    suspend fun insertFilms(entity: FilmEntity)
}