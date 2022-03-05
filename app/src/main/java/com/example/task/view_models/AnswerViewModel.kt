package com.example.task.view_models

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.task.data.models.Question
import com.example.task.data.repositories.MainRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnswerViewModel
@Inject constructor(
    val repo: MainRepository
):ViewModel() {

    val question = MediatorLiveData<Question>()

     fun getQuestion(id:Int){
         val questionResult = repo.getQuestion(id)
        question.addSource(questionResult){ result->
            result?.let { question.value = it }
        }
    }

    fun updateQuestion(newQuestion: Question) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateQuestion(newQuestion)
    }

}