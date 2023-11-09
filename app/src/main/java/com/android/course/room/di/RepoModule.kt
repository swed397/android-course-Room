package com.android.course.room.di

import com.android.course.room.data.db.FilmDao
import com.android.course.room.data.repo.FilmsDbRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule(private val filmDao: FilmDao) {

    @Provides
    @Singleton
    fun provideFilmsRepo(): FilmsDbRepo = FilmsDbRepo(filmDao)
}