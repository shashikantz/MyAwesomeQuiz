package com.example.myawesomequiz.activities.quiz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myawesomequiz.database.DataBaseHelper;
import com.example.myawesomequiz.models.Question;
import com.example.myawesomequiz.models.QuizContract;

public class QuizDBAdapter {

    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public QuizDBAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public QuizDBAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public QuizDBAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    @SuppressLint("Range")
    public List<Question> getTestData() {
        List<Question> questionList = new ArrayList<>();


        try {
            Cursor c = mDb.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME + " limit 100", null);

            if (c.moveToFirst()) {
                do {
                    Question question = new Question();
                    question.setId(Integer.parseInt(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ID))));
                    question.setQno(Integer.parseInt(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUE_NUMBER))));
                    question.setQtype(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUE_TYPE)));
                    question.setQtype_value(Integer.parseInt(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUE_TYPE_VALUE))));
                    question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                    question.setCorrectAnswer(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER)).trim().toCharArray()[0]);
                    question.setExplanation(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_EXPLANATION)));
                    questionList.add(question);
                } while (c.moveToNext());
            }

            c.close();
            return questionList;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}