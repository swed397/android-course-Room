package com.android.course.room.data.repo

import com.android.course.room.data.db.FilmDao
import com.android.course.room.domain.FilmInfo
import com.android.course.room.domain.PreviewFilmInfo
import com.android.course.room.toDto
import javax.inject.Inject

class FilmsDbRepo @Inject constructor(private val dbDao: FilmDao) {

    suspend fun getFilmsPreviewList(): List<PreviewFilmInfo> =
        dbDao.getAllFilmsAndGenres().map { it.toDto() }

    suspend fun getFilmInfoById(id: Long): FilmInfo =
        dbDao.getFilmById(id).let { it.toDto(dbDao.getGenreById(it.genreId).name) }

    suspend fun getAllGenres(): List<String> = dbDao.getAllGenres().map { it.name }
}