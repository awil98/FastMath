package com.example.aaron.mathappl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class gameActivity extends AppCompatActivity {
    private int numOne, numTwo, answer, points;
    private int highScore = 0;
    private String question, selection;
    private Button options;
    private TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        //I need to set a question and some answers as soon as the activity is created
        setQuestion();
        //load up the high score
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        highScore = prefs.getInt("highScore", 0);
        //set the timer
        timer = (TextView)findViewById(R.id.timer);
        new CountDownTimer(30000, 1000){
            //This method is just to update the time
            public void onTick(long millisUntilFinished){
                timer.setText("Time Remaining: " + millisUntilFinished / 1000);
            }
            //This method is for when the time has finished
            public void onFinish(){
                timer.setText("Times Up!!");
                //Go back to the home Screen
                finish();
            }
        }.start();
    }

    public void checkAnswer(View view) {
        String s;
        int i;
        //find out which button was pressed
        s = ((Button)view).getText().toString();
        i = Integer.parseInt(s);
        //then compare the number on the button pressed with the answer that has been generated
        if(i == answer){
            points++;
            if(points > highScore){
                //save the high Score in a preference
                highScore = points;
                Toast.makeText(getApplicationContext(), "New High Score", Toast.LENGTH_SHORT).show();
                SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putInt("highScore", highScore);
                prefsEditor.apply();
            }
        }else {
            points--;
        }
        //update the points
        TextView pointsText = (TextView)findViewById(R.id.pointsTot);
        pointsText.setText("Points: " + Integer.toString(points));
        //then finally call the setQuestion method so that new questions and answers are generated
        setQuestion();
    }

    public void setQuestion(){
        //This method is going to set the question and the four answers
        Random rand = new Random();
        TextView questionText = (TextView)findViewById(R.id.questionId);
        numOne = rand.nextInt(100);
        numTwo = rand.nextInt(100);
        answer = numOne + numTwo;
        //now set the question
        question = Integer.toString(numOne) + " + " + Integer.toString(numTwo) + " =";
        questionText.setText(question);
        //now set all of the answers
        int seed = rand.nextInt(3);
        //this seed is to choose which iteration will be the correct answer
        for(int i = 0; i < 4; i++){
            //should run 4 times choosing one of the 4 buttons to be
            //the answer should be placed into one of the four buttons
            if(i == seed){
                selection = Integer.toString(answer);
            }else{
                int randNum = rand.nextInt(100);
                selection = Integer.toString(randNum);
            }
            switch(i){
                case 0:
                    options = (Button)findViewById(R.id.option1);
                    options.setText(selection);
                    break;
                case 1:
                    options = (Button)findViewById(R.id.option2);
                    options.setText(selection);
                    break;
                case 2:
                    options = (Button)findViewById(R.id.option3);
                    options.setText(selection);
                    break;
                case 3:
                    options = (Button)findViewById(R.id.option4);
                    options.setText(selection);
                    break;
            }
        }


    }
}
