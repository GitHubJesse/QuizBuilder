package com.example.w0299104.quizbuilder;

import android.content.Intent;
//import android.support.design.widget.Snackbar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

//TODO Comment to github push a comment of what to do
    public static final String TAG = "QuizActivity";

    HashMap<String,String> questionHashMap = new HashMap<>();
    ArrayList<String> definitionList = new ArrayList<>();
    ArrayList<String> answerList = new ArrayList<>();

    //ArrayList<String> usedKeys = new ArrayList<>();

    int questionsAnswered = 0;
    String nameString;
    int score = 0;
    String currentCorrectAnswer;

    TextView textViewDefinition;
    TextView textViewLastAnswer;
    Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;

    int totalQuestions = 30;

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
        textViewLastAnswer = (TextView) findViewById(R.id.textViewLastAnswer);

        btnAnswer1 = (Button) findViewById(R.id.btnAnswer1);
        btnAnswer2 = (Button) findViewById(R.id.btnAnswer2);
        btnAnswer3 = (Button) findViewById(R.id.btnAnswer3);
        btnAnswer4 = (Button) findViewById(R.id.btnAnswer4);

        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(1);
                if(questionsAnswered >= totalQuestions){
                    displayScoreScreen(view);
                } else {
                    shuffleKeys();
                }
            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(2);
                if(questionsAnswered >= totalQuestions){
                    displayScoreScreen(view);
                } else {
                    shuffleKeys();
                }
            }
        });

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(3);
                if(questionsAnswered >= totalQuestions){
                    displayScoreScreen(view);
                } else {
                    shuffleKeys();
                }
            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(4);
                if(questionsAnswered >= totalQuestions){
                    displayScoreScreen(view);
                } else {
                    shuffleKeys();
                }
            }
        });

        for (int shuffles = 0;shuffles<10;shuffles++){
            softShuffle();
        }
        shuffleKeys();

    }//end of onCreate()

    public void checkAnswer(int buttonID){
        questionsAnswered++;

        boolean answerCorrect = false;

        switch (buttonID){
            case 1:
                if (btnAnswer1.getText().toString().equals(currentCorrectAnswer)) {
                    answerCorrect = true;
                    score++;
                }
                break;
            case 2:
                if (btnAnswer2.getText().toString().equals(currentCorrectAnswer)) {
                    answerCorrect = true;
                    score++;
                }
                break;
            case 3:
                if (btnAnswer3.getText().toString().equals(currentCorrectAnswer)) {
                    answerCorrect = true;
                    score++;
                }
                break;
            case 4:
                if (btnAnswer4.getText().toString().equals(currentCorrectAnswer)) {
                    answerCorrect = true;
                    score++;
                }
                break;
        }

        if (answerCorrect) {
            //Toast.makeText(this,"Correct",Toast.LENGTH_LONG).show();
            textViewLastAnswer.setTextColor(Color.parseColor("#34CC1C"));
            textViewLastAnswer.setBackgroundColor(Color.parseColor("#C6DEDB"));
        } else {
            //Toast.makeText(this,"Incorrect",Toast.LENGTH_LONG).show();
            textViewLastAnswer.setTextColor(Color.parseColor("#CC3B1C"));
            textViewLastAnswer.setBackgroundColor(Color.parseColor("#C6DEDB"));
        }
        textViewLastAnswer.setText(textViewDefinition.getText().toString()+"'s salary is "+currentCorrectAnswer);
    }

    //Very verbose function, will likely comment a lot to remember each steps purpose
    public void shuffleKeys(){
        int min = 0, max = definitionList.size();

        //int randomIndex = ThreadLocalRandom.current().nextInt(min, definitionList.size() + 1);

        Random r = new Random();

        int randomIndex = r.nextInt(max - min) + min;

        String newDefinition = definitionList.get(randomIndex);


        Object[] shuffleKeys = questionHashMap.keySet().toArray();
        Object key = shuffleKeys[new Random().nextInt(shuffleKeys.length)];

        //Assign each Definition per button press until 10 answers submitted
        textViewDefinition.setText(newDefinition);

        String correctAnswer = questionHashMap.get(newDefinition);

        //This randomizes which button the correct answer will appear on
        int correctAnswerLocation = r.nextInt(5 - 1) + 1;

        //Take random int 1-4 and use that to assign the location of the correct answer
        switch (correctAnswerLocation){
            case 1:
                btnAnswer1.setText(correctAnswer);
                break;
            case 2:
                btnAnswer2.setText(correctAnswer);
                break;
            case 3:
                btnAnswer3.setText(correctAnswer);
                break;
            case 4:
                btnAnswer4.setText(correctAnswer);
                break;
        }

        ArrayList<String> usedAnswers = new ArrayList<>();
        usedAnswers.add(correctAnswer);
        currentCorrectAnswer = correctAnswer;

        for (int a=0;a<4;a++){

            int randWrongAnswer = r.nextInt(answerList.size());

            if(answerList.get(randWrongAnswer).equals(correctAnswer)){
                //Prevent the answer value from escaping this loop until it is not the correct answer
                do{
                    randWrongAnswer = r.nextInt(answerList.size());
                } while(answerList.get(randWrongAnswer).equals(correctAnswer));
                continue;
            }
            if(usedAnswers.contains(answerList.get(randWrongAnswer))) {
                continue;
            }
            usedAnswers.add(answerList.get(randWrongAnswer));
        }

        for(int b=0;b<usedAnswers.size();b++){
            switch (b) {
                case 1:
                    if(b == correctAnswerLocation) {
                        continue;
                    } else {
                        btnAnswer1.setText(usedAnswers.get(b));
                    }
                    break;
                case 2:
                    if(b == correctAnswerLocation) {
                        continue;
                    } else {
                        btnAnswer2.setText(usedAnswers.get(b));
                    }
                    break;
                case 3:
                    if(b == correctAnswerLocation) {
                        continue;
                    } else {
                        btnAnswer3.setText(usedAnswers.get(b));
                    }
                    break;
                case 4:
                    if(b == correctAnswerLocation) {
                        continue;
                    } else {
                        btnAnswer4.setText(usedAnswers.get(b));
                    }
                    break;
            }
        }
        //Remove current definition so this question is not repeated
        definitionList.remove(randomIndex);
    }

    public void displayScoreScreen(View v){
        String scoreString = Integer.toString(score);

        scoreString = nameString + ", you scored " + scoreString + "/"+totalQuestions;

        Intent i = new Intent(QuizActivity.this, ScoreDisplay.class);
        //Send "name" as the access key and the value of inputName to be accessed by the key
        i.putExtra("score",scoreString);

        startActivity(i);
        finishQuiz();
    }

    public void readAndParseRawText(){//String txtFile) {
        String txtLine = "";
        ArrayList<String> lines = new ArrayList<>();





        ////Used to be R.raw.definitions for the assignment
        InputStream is = getResources().openRawResource(R.raw.hockeycaphits);





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
            Log.v(TAG,"IO Exception   -   "+e.getMessage());
        } finally {
            //Close the file
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.v(TAG,"IO Exception   -   "+e.getMessage());
            }
        }
    }
    public void finishQuiz(){
        this.finish();
    }

    public void softShuffle() {
        int min = 0, max = definitionList.size();

        //int randomIndex = ThreadLocalRandom.current().nextInt(min, definitionList.size() + 1);

        Random r = new Random();

        int randomIndex = r.nextInt(max - min) + min;

        String newDefinition = definitionList.get(randomIndex);


        Object[] shuffleKeys = questionHashMap.keySet().toArray();
        Object key = shuffleKeys[new Random().nextInt(shuffleKeys.length)];

        //Assign each Definition per button press until 10 answers submitted
        textViewDefinition.setText(newDefinition);

        String correctAnswer = questionHashMap.get(newDefinition);

        //This randomizes which button the correct answer will appear on
        int correctAnswerLocation = r.nextInt(5 - 1) + 1;

        //Take random int 1-4 and use that to assign the location of the correct answer
        switch (correctAnswerLocation) {
            case 1:
                btnAnswer1.setText(correctAnswer);
                break;
            case 2:
                btnAnswer2.setText(correctAnswer);
                break;
            case 3:
                btnAnswer3.setText(correctAnswer);
                break;
            case 4:
                btnAnswer4.setText(correctAnswer);
                break;
        }

        ArrayList<String> usedAnswers = new ArrayList<>();
        usedAnswers.add(correctAnswer);
        currentCorrectAnswer = correctAnswer;

        for (int a = 0; a < 4; a++) {

            int randWrongAnswer = r.nextInt(answerList.size());

            if (answerList.get(randWrongAnswer).equals(correctAnswer)) {
                //Prevent the answer value from escaping this loop until it is not the correct answer
                do {
                    randWrongAnswer = r.nextInt(answerList.size());
                } while (answerList.get(randWrongAnswer).equals(correctAnswer));
                continue;
            }
            if (usedAnswers.contains(answerList.get(randWrongAnswer))) {
                continue;
            }
            usedAnswers.add(answerList.get(randWrongAnswer));
        }

        for (int b = 0; b < usedAnswers.size(); b++) {
            switch (b) {
                case 1:
                    if (b == correctAnswerLocation) {
                        continue;
                    } else {
                        btnAnswer1.setText(usedAnswers.get(b));
                    }
                    break;
                case 2:
                    if (b == correctAnswerLocation) {
                        continue;
                    } else {
                        btnAnswer2.setText(usedAnswers.get(b));
                    }
                    break;
                case 3:
                    if (b == correctAnswerLocation) {
                        continue;
                    } else {
                        btnAnswer3.setText(usedAnswers.get(b));
                    }
                    break;
                case 4:
                    if (b == correctAnswerLocation) {
                        continue;
                    } else {
                        btnAnswer4.setText(usedAnswers.get(b));
                    }
                    break;
            }
        }
    }
}
