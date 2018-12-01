package com.example.helen.truefalsequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    Button buttonFalse;
    Button buttonTrue;
    boolean playerbool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        InputStream stream = getResources().openRawResource(R.raw.questions);
        String jsonString = readTextFile(stream);
        Gson gson = new Gson();

        Question[] questions = gson.fromJson(jsonString, Question[].class);
        final List<Question> questionList = Arrays.asList(questions);


        wireWidgets();

        final Quiz quiz = new Quiz(questionList, 0, 0);

        final TextView text = findViewById(R.id.textView);
        text.setText(questionList.get(quiz.getCurrentQuestion()).getQuestion());

        final TextView textView = findViewById(R.id.scoreid);


        //   must have the Question and Score setText under both onClick()'s because
        // currentQuestion and score only incrememts under these if statements in the
        // score-changing methods.

        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerbool = true;


                if(playerbool == questionList.get(quiz.getCurrentQuestion()).getAnswer()){
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
                    quiz.incrScore();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Sorry, not quite!", Toast.LENGTH_LONG).show();
                    quiz.decrScore();
                }

                text.setText(questionList.get(quiz.getCurrentQuestion()).getQuestion());
                textView.setText(String.valueOf(quiz.getScore()));
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerbool = false;

                if(playerbool == questionList.get(quiz.getCurrentQuestion()).getAnswer()){
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
                    quiz.incrScore();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Sorry, not quite!", Toast.LENGTH_LONG).show();
                    quiz.decrScore();
                }

                text.setText(questionList.get(quiz.getCurrentQuestion()).getQuestion());
                textView.setText(String.valueOf(quiz.getScore()));
            }
        });


        //  Disables the Buttons if you've reached the end of the game.

        if(quiz.getCurrentQuestion() == 10){
            buttonTrue.setOnClickListener(null);
            buttonFalse.setOnClickListener(null);

        }
    }

    private void wireWidgets() {
        buttonTrue = findViewById(R.id.trueid);
        buttonFalse = findViewById(R.id.falseid);
    }


    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }


}
