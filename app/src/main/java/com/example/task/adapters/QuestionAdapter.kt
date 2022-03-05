package com.example.task.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.data.models.Question
import com.example.task.views.fragments.QuestionFragmentDirections


class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {
    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    val diffCallback = object : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Question>)  {
        differ.submitList(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.question_item,
                parent,
                false
            )
        )    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.itemView.apply{
            findViewById<TextView>(R.id.question_tv).text = "Question ${position+1}"
            if(position == 0){
               // findViewById<ConstraintLayout>(R.id.question_layout).setBackgroundColor(Color.LTGRAY)
             } else if(position!=0 && !differ.currentList[position-1].isAnswered ){
                findViewById<ConstraintLayout>(R.id.question_layout).setBackgroundColor(Color.LTGRAY)
            }
        }

        holder.itemView.setOnClickListener { view->

            if(position==0){
                view.findNavController().navigate(QuestionFragmentDirections.actionQuestionsFragmentToAnswerFragment().setQuestionArg(differ.currentList[position].id!!))
            }else
            if(position !=0 && differ.currentList[position-1].isAnswered){
               view.findNavController().navigate(QuestionFragmentDirections.actionQuestionsFragmentToAnswerFragment().setQuestionArg(differ.currentList[position].id!!))
            }

        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
}