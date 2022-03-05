package com.example.task.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions_table")
data class Question(
    var question: String,
    var isAnswered: Boolean
){
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null
}