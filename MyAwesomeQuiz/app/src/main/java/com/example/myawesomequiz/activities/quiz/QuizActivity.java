package com.example.myawesomequiz.activities.quiz;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myawesomequiz.R;
import com.example.myawesomequiz.models.Question;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    protected static final String TAG = "QuizActivity";
    private List<Question> questionList;
    private int questionCountTotal;
    private RecyclerView recyclerView;
    private QuestionGalleryAdapter questionGalleryAdapter;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        Bundle extras = getIntent().getExtras();
        String columnName = extras.getString("columnName");
        String columnValue = extras.getString("columnValue");

        Log.d(TAG, "columnName: " + columnName + " columnValue: "+ columnValue);




        QuizDBAdapter dbAdapter = new QuizDBAdapter(this);

        questionList = dbAdapter.getTestData(columnName,columnValue);
        questionCountTotal = questionList.size();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(columnValue.replaceAll("-", " ") + " " + "(" + questionCountTotal + " Qs)");


        recyclerView  = (RecyclerView)findViewById( R.id.recycler_view_quiz);
        questionGalleryAdapter = new QuestionGalleryAdapter( questionList, getApplication(), dbAdapter);
        recyclerView.setAdapter(questionGalleryAdapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));



    }

}