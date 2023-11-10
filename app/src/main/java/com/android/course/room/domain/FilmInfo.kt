package com.android.course.room.domain

data class FilmInfo(
    val title: String,
    val genre: String,
    val year: Int,
    val authors: String,
    val info: String,
    val rate: Double
)
