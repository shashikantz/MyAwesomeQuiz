package com.example.myawesomequiz.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myawesomequiz.R;
import com.example.myawesomequiz.activities.quiz.QuizActivity;
import com.example.myawesomequiz.models.QuizContract;
import com.google.firebase.analytics.FirebaseAnalytics;

public class StartingScreenActivity extends AppCompatActivity {
    private static final String TAG = "StartingScreenActivity";
    RecyclerView recyclerView;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        // Spinner showing previous years
        Spinner yearSpinner = (Spinner) findViewById(R.id.spinner_years);
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.previous_years, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
        Button buttonStartQuizYearwise = findViewById(R.id.btn_quiz_start_yearwise);
        buttonStartQuizYearwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = yearSpinner.getSelectedItem().toString().split("\\s+")[1];
                Log.d("MainActivity", ": spinner selected : " + year);


                startQuiz(QuizContract.QuestionsTable.COLUMN_QUE_TYPE_VALUE,year);
            }

        });


        // Spinner showing previous years
        Spinner topicSpinner = (Spinner) findViewById(R.id.spinner_topics);
        ArrayAdapter<CharSequence> topicAdapter = ArrayAdapter.createFromResource(this,
                R.array.question_topics, android.R.layout.simple_spinner_item);
        topicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        topicSpinner.setAdapter(topicAdapter);
        Button buttonStartQuizTopicwise = findViewById(R.id.btn_quiz_start_topicwise);
        buttonStartQuizTopicwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topicInDB = topicSpinner.getSelectedItem().toString().toLowerCase().replaceAll(" ","-");
                Log.d("MainActivity", ": spinner selected : " + topicInDB);

                startQuiz(QuizContract.QuestionsTable.COLUMN_TOPIC, topicInDB);
            }

        });

        EditText searchText = findViewById(R.id.edittext_search_questions);
        searchText.setSelectAllOnFocus(true);
        Button btnSearch = findViewById(R.id.btn_search_questions);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchString = searchText.getText().toString();
                Log.d(TAG, "btnSearch clicked! for string: " + searchString);
                startQuiz(QuizContract.QuestionsTable.COLUMN_QUESTION, searchString);
            }
        });


        Button btnFavoriteSearch = findViewById(R.id.btn_favorite_questions);
        btnFavoriteSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startQuiz(QuizContract.FavoritesTable.COLUMN_IS_FAVORITE, "1");
            }
        });
    }

    private void startQuiz(String columnName, String columnValue) {
        Intent intent = new Intent(StartingScreenActivity.this, QuizActivity.class);
        intent.putExtra("columnName", columnName);
        intent.putExtra("columnValue", columnValue);
        startActivity(intent);
    }

}