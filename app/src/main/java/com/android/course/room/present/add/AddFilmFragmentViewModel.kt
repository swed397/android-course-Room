package com.android.course.room.present.add

import com.android.course.room.data.repo.FilmsDbRepo
import com.android.course.room.present.BaseViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

class AddFilmFragmentViewModel @Inject constructor(private val filmsDbRepo: FilmsDbRepo) :
    BaseViewModel(filmsDbRepo) {

    suspend fun getAllGenres(): List<String> = scopeIO.async { filmsDbRepo.getAllGenres() }.await()

    suspend fun save() {
        //ToDo
    }
}