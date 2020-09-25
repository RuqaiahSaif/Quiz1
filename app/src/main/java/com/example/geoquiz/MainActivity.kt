package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class  MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var questionTextView: TextView

    val questionBank = listOf(
        Question(R.string.question_text, true),
        Question(R.string.question_sanaa, true),
        Question(R.string.question_taiz, false)
    )

    private var currentIndex = 0
    var myarray = Array<Int>(questionBank.size) { 0 }

    companion object {
        var result = 0

    }

    private fun checkAnswer(userAnswer: Boolean) {
        val myarray = questionBank[currentIndex].textResId
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            result = result + 1
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
        this.falseButton.isEnabled = false
        this.trueButton.isEnabled = false
        if (currentIndex == questionBank.size-1) {
            Toast.makeText(this, "result is$result", Toast.LENGTH_SHORT)
                .show()
        }

    }


    private fun updateQuestion() {
        if (questionBank[currentIndex].textResId in myarray) {
            falseButton.isEnabled = false
            trueButton.isEnabled = false

        } else {
            falseButton.isEnabled = true
            trueButton.isEnabled = true
        }
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        questionTextView = findViewById(R.id.quest_text)

        val next1_Button = findViewById<ImageButton>(R.id.next1_button)
        val pre1_Button = findViewById<ImageButton>(R.id.pre1_button)



        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        falseButton.setOnClickListener {
            checkAnswer(false)

        }

        next1_Button.setOnClickListener {



            currentIndex = (currentIndex + 1) % questionBank.size

            updateQuestion()
        }


           pre1_Button.setOnClickListener {



              currentIndex = (currentIndex + 1) % questionBank.size

              updateQuestion()}

    }
}





