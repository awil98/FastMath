package com.example.aaron.mathappl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //figure out how to fix content of screen going off page when screen is rotated
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //This method just sends user to the game activity
    public void goToGame(View view) {
        Intent intent = new Intent(this, gameActivity.class);
        startActivity(intent);
    }

    //This method sends the user to the high score activity
    public void goToHighScore(View view) {
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }
    //create intent and method for setting page
    //also consider creating a rest button in the setting that allows user to reset data

}
