package com.android.course.room.data.repo

import com.android.course.room.data.db.FilmDao
import com.android.course.room.domain.PreviewFilmInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import java.io.Closeable
import javax.inject.Inject

class FilmsDbRepo @Inject constructor(private val dbDao: FilmDao) : Closeable {

    private val scopeIO = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    suspend fun getFilmsPreviewList(): Deferred<List<PreviewFilmInfo>> = scopeIO.async {
        dbDao.getAllFilmsAndGenres()
            .map { PreviewFilmInfo(title = it.film.title, genre = it.genreEntity.name) }
    }

    override fun close() {
        scopeIO.cancel()
    }

}