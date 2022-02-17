package com.example.myawesomequiz.activities.quiz;

import android.content.res.ColorStateList;
import android.os.Bundle;
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

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;
    private RecyclerView recyclerView;
    private QuestionGalleryAdapter questionGalleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("CSP 2010");

        QuizDBAdapter mDbHelper = new QuizDBAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        questionList = mDbHelper.getTestData();

        questionCountTotal = questionList.size();


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