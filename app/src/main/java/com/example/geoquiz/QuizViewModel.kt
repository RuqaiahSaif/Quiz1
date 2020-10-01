package com.example.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel:ViewModel() {
    var isCheater:Boolean=false
    var currentIndex =0
    val questionBank = listOf(
        Question(R.string.question_text1 , true,2,1,2),
        Question(R.string.question_text2, true,2,1,2),
        Question(R.string.question_text3, true,2,1,2),
        Question(R.string.question_text4, true,2,2,4),
        Question(R.string.question_text5, true,2,2,4),
        Question(R.string.question_text6, false,2,2,4),
        Question(R.string.question_text7, true,2,3,6),
        Question(R.string.question_text8, true,2,3,6),
        Question(R.string.question_text9, false,2,3,6))

    fun moveToNext() {

        currentIndex=(0..10).random()
}

}

