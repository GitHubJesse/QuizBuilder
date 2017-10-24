package com.example.w0299104.quizbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuizActivity extends AppCompatActivity {

    String rawTextfile = "raw/definitions";

    TextView textViewDefinition;

    Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        String nameString;
        if(savedInstanceState == null){
            Bundle extras  = getIntent().getExtras();
            if(extras == null){
                nameString = null;
            } else {
                nameString = extras.getString("name");
            }
        } else {
            nameString = (String) savedInstanceState.getSerializable("name");
        }

        Toast.makeText(this, nameString, Toast.LENGTH_SHORT).show();
//        readAndParseRawText(rawTextfile);

        textViewDefinition = (TextView) findViewById(R.id.textViewDefinition);

        btnAnswer1 = (Button) findViewById(R.id.btnAnswer1);
        btnAnswer2 = (Button) findViewById(R.id.btnAnswer2);
        btnAnswer3 = (Button) findViewById(R.id.btnAnswer3);
        btnAnswer4 = (Button) findViewById(R.id.btnAnswer4);

        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Used to collect data from intent.
        //Wont be used here but I'm storing the method here for now
//        Bundle extras = getIntent().getExtras();
//        if (extras = != null) {
//            blah
//        }
    }

    public void readAndParseRawText(String txtFile) {
        InputStream is = null;
        try {
            //Open file to read from
            is = new FileInputStream(txtFile);

            //Setup file to be read from
            InputStreamReader iReader = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(iReader);

            String txtLine;

            //Read each line of the file one by one into it's respective variable.
            do {
                txtLine = buffer.readLine();
                //Use this line of text
                //Parse
                //Left side = Definition
                //Right side = Answer
            } while (txtLine != null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Close the file
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
