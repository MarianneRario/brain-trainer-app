package com.semaphore.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* PROPERTY DECLARATIONS */
    Button startBtn; // initialize the button property outside the onCreate method
    boolean isActive = false; // flag for active or not
    CountDownTimer timer; // countdown timer property
    ArrayList<Integer> answers = new ArrayList<Integer>(); // arraylist that will hold the answer
    Random rand = new Random(); // generates random number
    int locationOfCorrectAnswer; // property that will hold the location of correct answer
    TextView remark; // property that will change if the answer chosen is correct or not
    int scoreCtr = 0; // property that will count the score
    int numberOfQuestions = 0; // property that will hold the total number of questions
    TextView scoreTextView;


    /* ONCLICK LISTENER THAT WILL SET THE START BUTTON TO INVISIBLE */
    public void start(View view){
        startBtn.setVisibility(View.INVISIBLE);
    }

    /* ONCLICK LISTENER THAT WILL LISTEN TO EVERY CLICK OF THE ANSWER BUTTON */
    public void answer(View view) {

        remark = (TextView) findViewById(R.id.remarkTextViewId); // reference for the remark textview
        remark.setVisibility(View.VISIBLE);

        // compare the location of correct answer to the tag of the clicked button
       if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag())){
           remark.setText("Correct!");
           scoreCtr++;

       } else {
           remark.setText("Incorrect!");
       }

        numberOfQuestions++; // increase the number of questions regardless whether the ans is right or wrong
        scoreTextView.setText(Integer.toString(scoreCtr) + "/" + Integer.toString(numberOfQuestions)); // update the score in the textview
        generateQuestion(); // generate new question
    }

    /* FUNCTION FOR TIMER */
    public void timer(){
        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                updateTime((int) l/1000);
            }

            @Override
            public void onFinish() {
                Log.i("Timer", "Timer is up!");
            }
        }.start();

    }

    /* FUNCTION TO UPDATE TIMER TEXTVIEW */
    public void updateTime(int secs){
        TextView timerTextView = (TextView) findViewById(R.id.timerTextViewId); // textview reference
        timerTextView.setText(secs + "s");
    }

    /* FUNCTION THAT GENERATES NEW QUESTION */
    public void generateQuestion(){

        // generates random number from 0 - 20
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        // display the generated number inside the question textview
        TextView question = (TextView) findViewById(R.id.questionTextViewId); // reference of the question textview
        question.setText(a + " + " +b);

        // call the checkAnswer function immediately
        changeAnswerText(a, b);
    }

    /* FUNCTION THAT CHANGES THE TEXT OF THE ANSWER BUTTON */
    public void changeAnswerText(int a, int b){
        locationOfCorrectAnswer = rand.nextInt(4); // will hold the location of the correct answer; we pass 4 because we'll be picking from 0- 3

        // clear the array everytime that the array is called because it will just accumulate answers; if we wont clear the arraylist, only the first 4 elements will be shown in the button
        answers.clear();

        // fill out the array that will hold the answers using for loop
        for(int i=0; i<4; i++){ // this will run 4x because we have 4 buttons
            if(i==locationOfCorrectAnswer){
                answers.add(a+b); // add the correct answer in the array
            } else {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == (a+b)){ // if wrong answer is the same as the right answer, continue to generate
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer); // add the wrong answer in the array
            }
        }

        /* Change the answers button text */
        // references for the answer button
        Button button0 = (Button) findViewById(R.id.btn1);
        Button button1 = (Button) findViewById(R.id.btn2);
        Button button2 = (Button) findViewById(R.id.btn3);
        Button button3 = (Button) findViewById(R.id.btn4);

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.startBtnId); // reference of the start button
        scoreTextView = (TextView) findViewById(R.id.scoreTextViewId); // reference of the score textview
        scoreTextView.setText(Integer.toString(scoreCtr) + "/" + Integer.toString(numberOfQuestions)); // update the score in the textview
        generateQuestion(); // call the generate question when the screen starts


    }
}