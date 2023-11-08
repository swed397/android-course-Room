package com.android.course.room.data.db.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "films", indices = [Index(value = ["id", "title"], unique = true)])
data class FilmEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val title: String,
    val year: Int,
    val genreId: Long,
    val info: String?,
    val rate: Int?
)
