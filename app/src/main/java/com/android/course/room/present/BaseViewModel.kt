package com.android.course.room.present

import androidx.lifecycle.ViewModel
import com.android.course.room.data.repo.FilmsDbRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseViewModel(private val filmsDbRepo: FilmsDbRepo) : ViewModel() {

    val scopeIO = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCleared() {
        super.onCleared()
        scopeIO.cancel()
    }
}