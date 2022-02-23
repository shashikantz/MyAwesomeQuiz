package com.example.myawesomequiz.activities.quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myawesomequiz.database.DataBaseHelper;
import com.example.myawesomequiz.models.Question;
import com.example.myawesomequiz.models.QuizContract;

public class QuizDBAdapter {

    protected static final String TAG = "QuizDBAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public QuizDBAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    @SuppressLint("Range")
    public List<Question> getTestData(String columnName, String columnValue) {
        List<Question> questionList = new ArrayList<>();

        String sqlQuery = "SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME + " "
                + "LEFT JOIN " + QuizContract.FavoritesTable.TABLE_NAME + " "
                + "ON " +  QuizContract.QuestionsTable.TABLE_NAME + "." + QuizContract.QuestionsTable.COLUMN_ID
                + " = " + QuizContract.FavoritesTable.TABLE_NAME + "." +  QuizContract.FavoritesTable.COLUMN_QUESTION_ID + " "
                + "WHERE " + columnName + " = " + "'" + columnValue + "'" + " "
                + "ORDER BY " + QuizContract.QuestionsTable.COLUMN_QUE_TYPE_VALUE + " DESC";
        Log.d(TAG, "getTestData >> sqlQuery: " + sqlQuery);

        try {
            // open the DB
            mDb = mDbHelper.getReadableDatabase();
            Cursor c = mDb.rawQuery(sqlQuery, null);

            if (c.moveToFirst()) {
                do {
                    Question question = new Question();
                    question.setId(Integer.parseInt(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ID))));
                    question.setQno(c.getPosition() + 1);
                    question.setQtype(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUE_TYPE)));
                    question.setQtype_value(Integer.parseInt(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUE_TYPE_VALUE))));
                    question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                    question.setCorrectAnswer(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER)).trim().toCharArray()[0]);
                    question.setExplanation(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_EXPLANATION)));

                    String isFavorite = c.getString(c.getColumnIndex(QuizContract.FavoritesTable.COLUMN_IS_FAVORITE));
                    Log.e(TAG, "setFavorite is : >>" + isFavorite+ " Q id: " + question.getId() );
                    if(isFavorite != null){
                        question.setFavorite(true);
                        Log.e(TAG, "setFavorite is true!!!! >>" + " Q id: " + question.getId() );
                    }
                    questionList.add(question);
                } while (c.moveToNext());
            }

            c.close();
            return questionList;


        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        } finally {
            mDb.close();
        }

    }


    //add question_id to the 'favorites' table
    public void setFavorite(int questionId) {
        try {
            mDb = mDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(QuizContract.FavoritesTable.COLUMN_QUESTION_ID, String.valueOf(questionId));
            values.put(QuizContract.FavoritesTable.COLUMN_IS_FAVORITE, "1"); // "1" -> true
            mDb.insert(QuizContract.FavoritesTable.TABLE_NAME, null, values);
        } catch (SQLException mSQLException) {
            Log.e(TAG, "setFavorite >>" + mSQLException.toString());
            throw mSQLException;
        } finally {
            mDb.close();
        }
    }

    //remove favorite question form 'favorites' table
    public void unSetFavorite(int questionId) {
        try {
            mDb = mDbHelper.getWritableDatabase();
//            mDb.rawQuery("delete from favorites where question_id = " + String.valueOf(questionId), null);
            mDb.delete(QuizContract.FavoritesTable.TABLE_NAME, "question_id=?", new String[]{String.valueOf(questionId)});
        } catch (SQLException mSQLException) {
            Log.e(TAG, "unSetFavorite >>" + mSQLException.toString());
            throw mSQLException;
        } finally {
            mDb.close();
        }
    }
}