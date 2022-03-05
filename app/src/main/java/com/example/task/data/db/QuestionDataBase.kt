package com.example.test_tasks.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test_tasks.data.models.Question

@Database(entities = [Question::class],
    version = 1
    )
abstract class QuestionDataBase:RoomDatabase() {
    abstract fun getQuestionDao():QuestionDao
}