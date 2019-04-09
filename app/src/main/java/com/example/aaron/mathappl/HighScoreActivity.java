package com.example.aaron.mathappl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {

    //implement that a feature that shows the questions that were wrong and right
    //implement a feature that probably also shows that date and time of a high score
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        Intent intent = getIntent();
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        highScore = prefs.getInt("highScore", 0);
        TextView score = (TextView)findViewById(R.id.score);
        score.setText(Integer.toString(highScore));
    }
}
