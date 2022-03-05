package com.example.task.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.R
import com.example.task.adapters.QuestionAdapter
import com.example.task.data.models.Question
import com.example.task.databinding.FragmentQuestionsBinding
import com.example.task.view_models.QuestionsViewModel

import com.example.test_tasks.utilities.Constants.GESTION_1
import com.example.test_tasks.utilities.Constants.GESTION_2
import com.example.test_tasks.utilities.Constants.GESTION_3
import com.example.test_tasks.utilities.Constants.GESTION_4
import com.example.test_tasks.utilities.Constants.GESTION_5



import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : Fragment(R.layout.fragment_questions) {

    private val viewModel : QuestionsViewModel by viewModels()
    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!
    private lateinit var questionAdapter: QuestionAdapter

    private val questionList:List<Question> =
        listOf(
            Question(GESTION_1,false),
            Question(GESTION_2,false),
            Question(GESTION_3,false),
            Question(GESTION_4,false),
            Question(GESTION_5,false),)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionsBinding.inflate(layoutInflater)
        setupRecyclerView()

        binding.button2.setOnClickListener {
            viewModel.newGame(questionList)
        }
        viewModel.response.observe(viewLifecycleOwner) { result ->
            questionAdapter.submitList(result)
        }
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        viewModel.getAllQuestions()
    }


    private fun setupRecyclerView() = binding.recyclerView.apply {
        questionAdapter = QuestionAdapter()
        adapter = questionAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }




}