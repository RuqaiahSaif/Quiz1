package com.example.geoquiz
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

private const val REQUEST_CODE_CHEAT = 0
    class MainActivity : AppCompatActivity() {

        private lateinit var trueButton: Button
        private lateinit var falseButton: Button
        private lateinit var cheatButton: Button
        private lateinit var nextButton: ImageButton
        private lateinit var pre1_button: ImageButton
        private lateinit var questionTextView: TextView
        private lateinit var answerTextView: TextView
        var q:Int=0
         var num:Int=0
        private val quizViewModel: QuizViewModel by lazy {
            ViewModelProviders.of(this).get(QuizViewModel::class.java)
        }


        private val TAG = "MainActivity"

        override fun onCreate (savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            Log.d(TAG ,"onCreate() called" + quizViewModel.currentIndex )
            setContentView(R.layout.activity_main)
            trueButton = findViewById(R.id.true_button)
            cheatButton = findViewById(R.id.cheat_button)
            falseButton = findViewById(R.id.false_button)
            nextButton = findViewById(R.id.next_button)
            pre1_button = findViewById(R.id.pre1_button)
            questionTextView = findViewById(R.id.question_text)

            answerTextView = findViewById(R.id. degree_text_view)

            updateQuestion()

            trueButton.setOnClickListener{
                checkAnswer(true)
            }

           falseButton.setOnClickListener{
                checkAnswer(false)
            }
            nextButton.setOnClickListener{
                       num=num+1
                quizViewModel.moveToNext()
                updateQuestion()
            }



            questionTextView.setOnClickListener{
                quizViewModel.moveToNext()
                updateQuestion()
            }



            pre1_button.setOnClickListener{
                if (quizViewModel.currentIndex==0){
                    quizViewModel.currentIndex =quizViewModel.questionBank.size-1
                }else {
                    quizViewModel.moveToNext()
                }
                updateQuestion()

            }
            cheatButton.setOnClickListener {
                val answerIsTrue =quizViewModel.questionBank[quizViewModel.currentIndex].answer
                val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue!!)
                startActivityForResult(intent,REQUEST_CODE_CHEAT)
                trueButton?.isEnabled = false
                falseButton?.isEnabled = false
                   q=q+1
            }


        }



        private fun updateQuestion() {

      if(   quizViewModel.questionBank[quizViewModel.currentIndex].setnum==0){
          quizViewModel.moveToNext()
          updateQuestion()
      }


           val questionTextResId = quizViewModel.questionBank[quizViewModel.currentIndex].textResId

            if (questionTextResId != null) {
                questionTextView.setText(questionTextResId)

            }
var an: Int? =quizViewModel.questionBank[quizViewModel.currentIndex].answerd
            if(an == 1 || an == 0){
                trueButton?.isEnabled = false
                falseButton?.isEnabled = false
            }
            else{
                trueButton?.isEnabled = true
                falseButton?.isEnabled = true
            }
            quizViewModel.questionBank[quizViewModel.currentIndex].setnum=0

              if(q>=3){
    cheatButton?.isEnabled = false

}

        }

        private fun checkAnswer(userAnswer:Boolean) {
            val correctAnswer = quizViewModel.questionBank[quizViewModel.currentIndex].answer
         val messageResId = when {
                quizViewModel.isCheater -> R.string.judgment_toast
             userAnswer == correctAnswer ->{
                 quizViewModel.questionBank[quizViewModel.currentIndex].answerd= 1
                 showFinalScore(quizViewModel.currentIndex)
                 R.string.correct_toast

             }
                else ->{quizViewModel.questionBank[quizViewModel.currentIndex].answerd = 0
                    R.string.incorrect_toast
                }
            }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

            trueButton?.isEnabled = false
            falseButton?.isEnabled = false

}





        private fun showFinalScore(  currentIndex:Int) : Int
        { var mScore:Int=0

                mScore+=quizViewModel.questionBank[currentIndex].deg

            answerTextView.setText(mScore.toString())
            return mScore ;
        }


        override fun onStart() {
            super.onStart()
            Log.d(TAG ,"onStart() called "  + quizViewModel.currentIndex)
        }

        override fun onPause() {
            super.onPause()
            Log.d(TAG ,"onPause() called "  + quizViewModel.currentIndex)
        }

        override fun onRestart() {
            super.onRestart()
            Log.d(TAG ,"onRestart() called " + quizViewModel.currentIndex )
        }

        override fun onResume() {
            super.onResume()
            Log.d(TAG ,"onResume() called " + quizViewModel.currentIndex)
        }

        override fun onStop() {
            super.onStop()
            Log.d(TAG ,"onStop() called "  + quizViewModel.currentIndex )
        }


        override fun onDestroy() {
            super.onDestroy()
            Log.d(TAG ,"onDestroy() called" )
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
              super.onActivityResult(requestCode, resultCode, data)
            if(resultCode!=Activity.RESULT_OK)
                return
            if(requestCode==REQUEST_CODE_CHEAT){
               quizViewModel.isCheater=data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false

            }
        }}





