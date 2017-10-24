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
import java.util.ArrayList;
import java.util.StringTokenizer;

public class QuizActivity extends AppCompatActivity {

    //String rawTextfile = "raw/definitions";

    ArrayList<String> definitionList = new ArrayList<>();
    ArrayList<String> answerList = new ArrayList<>();

    TextView textViewDefinition;
    Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String nameString;
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                nameString = null;
            } else {
                nameString = extras.getString("name");
            }
        } else {
            nameString = (String) savedInstanceState.getSerializable("name");
        }

        readAndParseRawText();

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

    public void readAndParseRawText(){//String txtFile) {
        String txtLine = "";
        ArrayList<String> lines = new ArrayList<>();

        InputStream is = getResources().openRawResource(R.raw.definitions);

        StringBuilder buildString = new StringBuilder();

        ArrayList<String> list = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try{
            while((txtLine = br.readLine()) != null){

                lines.add(txtLine);

                //Toast.makeText(this, txtLine, Toast.LENGTH_LONG).show();
            }

            for(int i=0;i<lines.size();i++){

                StringTokenizer tokens = new StringTokenizer(lines.get(i),":");

                //This will tokenize the left side of the delimiter
                String def = tokens.nextToken();

                //This will tokenize the right side of the delimiter
                String answer = tokens.nextToken();

                definitionList.add(def);
                answerList.add(answer);

                Toast.makeText(this,definitionList.get(i),Toast.LENGTH_LONG).show();
                Toast.makeText(this,answerList.get(i),Toast.LENGTH_LONG).show();
            }

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
