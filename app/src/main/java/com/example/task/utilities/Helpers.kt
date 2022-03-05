package com.example.test_tasks.utilities

import android.content.Context
import android.widget.Toast

object Helpers {

    fun toastMe(context: Context,content:String){
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show()
    }
}