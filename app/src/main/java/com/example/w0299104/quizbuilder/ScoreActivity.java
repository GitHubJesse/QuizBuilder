package com.example.w0299104.quizbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        String scoreValue;
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                scoreValue = null;
            } else {
                scoreValue = extras.getString("score");
            }
        } else {
            scoreValue = (String) savedInstanceState.getSerializable("name");
        }
        Toast.makeText(this,scoreValue,Toast.LENGTH_SHORT).show();
    }
}
