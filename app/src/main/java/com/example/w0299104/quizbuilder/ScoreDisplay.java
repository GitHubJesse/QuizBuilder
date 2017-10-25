package com.example.w0299104.quizbuilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreDisplay extends AppCompatActivity {

    TextView textViewScore;
    String scoreString = "";
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_display);

        btnRestart = (Button) findViewById(R.id.btnRestart);
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

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScoreDisplay.this, MainActivity.class);
                startActivity(i);
                finishScoreDisplay();
            }
        });
    }
    public void finishScoreDisplay() { this.finish(); }
}
