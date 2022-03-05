package com.example.task.di

import android.content.Context
import androidx.room.Room
import com.example.test_tasks.utilities.Constants.QUESTION_DATA_BASE_NAME
import com.example.test_tasks.data.db.QuestionDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideQuestionDataBase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        QuestionDataBase::class.java,
        QUESTION_DATA_BASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideQuestionDao(db: QuestionDataBase) = db.getQuestionDao()
}