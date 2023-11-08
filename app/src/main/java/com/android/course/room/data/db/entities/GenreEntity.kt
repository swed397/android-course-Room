package com.android.course.room.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String
)
