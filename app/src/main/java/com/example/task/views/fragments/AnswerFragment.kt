package com.example.task.views.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.task.R
import com.example.task.data.models.Question
import com.example.task.databinding.FragmentAnswersBinding
import com.example.task.view_models.AnswerViewModel


import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AnswerFragment : Fragment(R.layout.fragment_answers) {
    lateinit var binding: FragmentAnswersBinding
    val args: AnswerFragmentArgs by navArgs()
    val viewModel: AnswerViewModel by viewModels()
    var question: Question? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnswersBinding.inflate(layoutInflater)

        viewModel.getQuestion(args.questionArg)
        viewModel.question.observe(viewLifecycleOwner, Observer {
            binding.question.text = it.question
            if(it.isAnswered){
                binding.nextBtn.setBackgroundColor(Color.BLUE)
            }else{
                binding.nextBtn.setBackgroundColor(Color.GRAY)
            }
            question = it
        })

        binding.nextBtn.setOnClickListener{
            if (question!!.isAnswered){
                findNavController().navigate(R.id.action_answerFragment_to_questionsFragment)
            }
        }
        binding.btYes.setOnClickListener{ view->
            viewModel.updateQuestion(question?.apply { isAnswered = true }!!)
        }
        binding.btNo.setOnClickListener{
            viewModel.updateQuestion(question?.apply { isAnswered = true }!!)
        }
        return binding.root
    }

}