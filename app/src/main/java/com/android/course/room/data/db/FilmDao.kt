package com.android.course.room.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.course.room.data.db.entities.FilmEntity
import com.android.course.room.data.db.entities.GenreEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM films ")
    fun getAllFilms(): List<FilmEntity>

    @Insert
    suspend fun insertGenre(entity: GenreEntity)
}