package com.example.myawesomequiz.activities.quiz;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myawesomequiz.models.Question;
import com.example.myawesomequiz.R;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    protected static final String TAG = "QuizActivity";
    private List<Question> questionList;
    private int questionCountTotal;
    private RecyclerView recyclerView;
    private QuestionGalleryAdapter questionGalleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Bundle extras = getIntent().getExtras();
        String columnName = extras.getString("columnName");
        String columnValue = extras.getString("columnValue");

        Log.d(TAG, "columnName: " + columnName + " columnValue: "+ columnValue);




        QuizDBAdapter mDbHelper = new QuizDBAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        questionList = mDbHelper.getTestData(columnName,columnValue);
        questionCountTotal = questionList.size();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(columnValue.replaceAll("-", " ") + " " + "(" + questionCountTotal + " Qs)");


        recyclerView  = (RecyclerView)findViewById(
                R.id.recycler_view_quiz);

        questionGalleryAdapter
                = new QuestionGalleryAdapter(
                questionList, getApplication());
        recyclerView.setAdapter(questionGalleryAdapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));



    }

}