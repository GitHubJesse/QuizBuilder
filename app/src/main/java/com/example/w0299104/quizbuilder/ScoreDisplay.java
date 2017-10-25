package com.example.w0299104.quizbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreDisplay extends AppCompatActivity {

    TextView textViewScore;
    String scoreString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_display);

        textViewScore = (TextView) findViewById(R.id.textViewScoreDisplay);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                scoreString = null;
            } else {
                scoreString = extras.getString("score");
            }
        } else {
            scoreString = (String) savedInstanceState.getSerializable("score");
        }

        textViewScore.setText(scoreString);
    }
}
