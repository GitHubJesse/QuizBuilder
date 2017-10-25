package com.example.w0299104.quizbuilder;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class QuizActivity extends AppCompatActivity {

    HashMap<String,String> questionHashMap = new HashMap<>();
    ArrayList<String> definitionList = new ArrayList<>();
    ArrayList<String> answerList = new ArrayList<>();

    ArrayList<String> usedKeys = new ArrayList<>();
    ArrayList<String> usedAnswers = new ArrayList<>();

    String nameString;
    int score = 0;

    TextView textViewDefinition;
    Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

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
                if (definitionList.size() < 1){
                    //displayScoreScreen(view);
                }
                shuffleKeys();
            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (definitionList.size() < 1){
                    //displayScoreScreen(view);
                }
                shuffleKeys();
            }
        });

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (definitionList.size() < 1){
                    //displayScoreScreen(view);
                }
                shuffleKeys();
            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (definitionList.size() < 1){
                    //displayScoreScreen(view);
                }
                shuffleKeys();
            }
        });

        shuffleKeys();

        //Used to collect data from intent.
        //Wont be used here but I'm storing the method here for now
//        Bundle extras = getIntent().getExtras();
//        if (extras = != null) {
//            blah
//        }
    }//end of onCreate()


    public void shuffleKeys(){
        int min = 0, max = definitionList.size();

        //int randomIndex = ThreadLocalRandom.current().nextInt(min, definitionList.size() + 1);
        Random r = new Random();

        int randomIndex = r.nextInt(max - min) + min;

        String newDefinition = definitionList.get(randomIndex);
        Toast.makeText(this,randomIndex+" "+newDefinition,Toast.LENGTH_LONG).show();


        Object[] shuffleKeys = questionHashMap.keySet().toArray();
        Object key = shuffleKeys[new Random().nextInt(shuffleKeys.length)];
        //Toast.makeText(this,key+" :: " +questionHashMap.get(key),Toast.LENGTH_LONG).show();
        btnAnswer1.setText(questionHashMap.get(newDefinition));
        btnAnswer2.setText(questionHashMap.get(newDefinition));
        btnAnswer3.setText(questionHashMap.get(newDefinition));
        btnAnswer4.setText(questionHashMap.get(newDefinition));
        definitionList.remove(randomIndex);
    }

    public void displayScoreScreen(View v){
        //Hardcode test
        score = 4;
        String scoreString = Integer.toString(score);


        //Snackbar.make(this,scoreString,Snackbar.LENGTH_SHORT).show();
        Snackbar snackbar = Snackbar
                .make(v, nameString + ", you scored " + scoreString + "/10", Snackbar.LENGTH_LONG);

        snackbar.show();
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

                //Add the Definition and Answer of this line to the respective list with the index equal to i
                definitionList.add(def);
                answerList.add(answer);

                //Assign each value into the array and group them by index number.
                //The definition will be the key and the answer will be the value
                questionHashMap.put(definitionList.get(i),answerList.get(i));

                //DEBUG Displays (definition +" is "+ answer). This proves that the parsing worked perfectly
                //Toast.makeText(this,definitionList.get(i)+" is "+answerList.get(i),Toast.LENGTH_LONG).show();
            }

            //Creates an entry set of the HashMap values
            Set set = questionHashMap.entrySet();
            //Initiate iterator to go through the HashMap
            Iterator iterator = set.iterator();
            //While there is still more HashMap values, keep iterating though the HaspMap
            while (iterator.hasNext()){
                Map.Entry mapEntry = (Map.Entry)iterator.next();

                //DEBUG Displays the Key value and Answer value to confirm that it was done correctly.
                //Toast.makeText(this,"Key is: "+ mapEntry.getKey() + "\nValue is: "+mapEntry.getValue()+"\n",Toast.LENGTH_LONG).show();
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
    public void finishQuiz(){
        this.finish();
    }
}
