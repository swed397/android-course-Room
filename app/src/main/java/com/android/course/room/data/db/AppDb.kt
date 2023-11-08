package com.android.course.room.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.course.room.data.db.entities.FilmEntity
import com.android.course.room.data.db.entities.GenreEntity

@Database(entities = [FilmEntity::class, GenreEntity::class], version = 1)
abstract class FilmsDb : RoomDatabase() {

    abstract fun filmDao(): FilmDao
}