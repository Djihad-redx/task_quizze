package com.example.task.view_models

import android.util.Log
import androidx.lifecycle.*
import com.example.task.data.models.Question
import com.example.task.data.repositories.MainRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(val repo: MainRepository) : ViewModel() {

    private val _response = MutableLiveData<List<Question>>()
    val response : LiveData<List<Question>> = _response

    init {
        getAllQuestions()
    }


    fun getAllQuestions() = viewModelScope.launch(Dispatchers.Main) {
        val result = repo.getQuestions()
        _response.postValue(result)
    }


    fun newGame(listQuestion:List<Question>) = viewModelScope.launch {
        repo.deleteAll()
        for (i in 0..4){
            repo.insertQuestion(listQuestion[0])
            getAllQuestions()}

    }


}
