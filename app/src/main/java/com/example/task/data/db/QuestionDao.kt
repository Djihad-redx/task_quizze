package com.example.test_tasks.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.task.data.models.Question



@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Update
    suspend fun updateQuestion(question: Question)

    @Query("SELECT * FROM questions_table")
    suspend  fun getAllQuestions(): List<Question>

    @Query("DELETE FROM questions_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM questions_table WHERE id = :id")
     fun getQuestion(id:Int): LiveData<Question>



}