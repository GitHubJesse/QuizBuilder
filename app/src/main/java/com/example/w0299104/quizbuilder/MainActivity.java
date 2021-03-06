package com.example.w0299104.quizbuilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    TextView textViewTitle;
    TextView textViewEmptyName;
    EditText editTextName;
    Button btnStartQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartQuiz = (Button) findViewById(R.id.btnStart);
        editTextName = (EditText)  findViewById(R.id.editTextName);

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //Collect input from editTextName to send to the QuizActivity screen
            String inputName = editTextName.getText().toString();

            Intent i = new Intent(MainActivity.this, QuizActivity.class);
            //Send "name" as the access key and the value of inputName to be accessed by the key
            i.putExtra("name",inputName);

            if(!inputName.equals("")){
                startActivity(i);
                finishMainMenu();
            } else {
                Toast.makeText(MainActivity.this,"You must enter your name to continue.",Toast.LENGTH_LONG).show();
            }

            }
        });
    }

    public void finishMainMenu(){
        this.finish();
    }

}
