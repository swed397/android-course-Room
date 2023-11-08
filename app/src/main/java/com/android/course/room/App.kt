package com.android.course.room

import android.app.Application
import androidx.room.Room
import com.android.course.room.data.db.FilmsDb
import com.android.course.room.data.db.entities.GenreEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class App : Application() {

    lateinit var db: FilmsDb

    val scope = CoroutineScope(Dispatchers.IO)
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, FilmsDb::class.java, "Films.db")
            .build()

        scope.launch {
            db.filmDao().insertGenre(GenreEntity(name = "Боевик"))
            db.filmDao().insertGenre(GenreEntity(name = "Ужасы"))
            db.filmDao().insertGenre(GenreEntity(name = "Фантастика"))
            db.filmDao().insertGenre(GenreEntity(name = "Сериал"))


        }
    }
}