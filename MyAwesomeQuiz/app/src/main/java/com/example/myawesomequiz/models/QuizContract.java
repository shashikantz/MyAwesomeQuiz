package com.example.myawesomequiz.models;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "questions";
        public static final String COLUMN_QUESTION = "statement";
        public static final String COLUMN_OPTION1 = "A";
        public static final String COLUMN_OPTION2 = "B";
        public static final String COLUMN_OPTION3 = "C";
        public static final String COLUMN_OPTION4 = "D";
        public static final String COLUMN_ANSWER = "answer";
    }
}