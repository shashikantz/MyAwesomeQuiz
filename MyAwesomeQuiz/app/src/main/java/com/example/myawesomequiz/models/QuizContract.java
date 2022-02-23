package com.example.myawesomequiz.models;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "questions";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_QUE_NUMBER = "qno";
        public static final String COLUMN_QUE_TYPE = "qtype"; // CSP, MOCK
        public static final String COLUMN_QUE_TYPE_VALUE = "qtype_value"; // 2010

        public static final String COLUMN_QUESTION = "statement";
        public static final String COLUMN_OPTION1 = "A";
        public static final String COLUMN_OPTION2 = "B";
        public static final String COLUMN_OPTION3 = "C";
        public static final String COLUMN_OPTION4 = "D";
        public static final String COLUMN_ANSWER = "answer";

        public static final String COLUMN_EXPLANATION = "explanation";
        public static final String COLUMN_TOPIC = "topic";

    }
    public static class FavoritesTable implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_QUESTION_ID = "question_id";
        public static final String COLUMN_IS_FAVORITE = "is_favorite";
    }
}