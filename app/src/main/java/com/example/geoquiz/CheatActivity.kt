package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
    "com.bignerdranch.android.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var myTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cheat)


       var answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
         answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
       myTextView = findViewById(R.id.a)
        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)

            setAnaswerShowRuslt(true)

    }
        myTextView.setText("API level"+Build.VERSION.RELEASE);
    }
   private fun setAnaswerShowRuslt(isanswer:Boolean){
        val data=Intent().apply{
            putExtra(EXTRA_ANSWER_SHOWN,isanswer)
        }
        setResult(Activity.RESULT_OK,data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue)
            }
        }
    }
}



