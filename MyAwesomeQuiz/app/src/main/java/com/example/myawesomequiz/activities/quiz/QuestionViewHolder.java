package com.example.myawesomequiz.activities.quiz;
// ViewHolder code for RecyclerView


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myawesomequiz.R;

public class QuestionViewHolder
        extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView textViewQuestionStatement;
    RadioGroup rbGroup;

    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;

    private OptionSelectionListener listener;

    QuestionViewHolder(View questionView, OptionSelectionListener listener)
    {
        super(questionView);

        this.listener = listener;
        textViewQuestionStatement = questionView.findViewById(R.id.text_view_question_statement);
        rbGroup = questionView.findViewById(R.id.radio_group);

        rb1 = (RadioButton) questionView.findViewById(R.id.radio_button1);
        rb2 = (RadioButton) questionView.findViewById(R.id.radio_button2);
        rb3 = (RadioButton) questionView.findViewById(R.id.radio_button3);
        rb4 = (RadioButton) questionView.findViewById(R.id.radio_button4);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
    }



    @Override
    public void onClick(View btnSelected) {
        int questionNumber = this.getAdapterPosition();
        switch (btnSelected.getId()){
            case R.id.radio_button1:
                listener.onSelectionOption1(btnSelected,questionNumber,1);
                break;
            case R.id.radio_button2:
                listener.onSelectionOption2(btnSelected,questionNumber,2);
                break;
            case R.id.radio_button3:
                listener.onSelectionOption3(btnSelected,questionNumber,3);
                break;
            case R.id.radio_button4:
                listener.onSelectionOption4(btnSelected,questionNumber,4);
                break;
            default:
                break;


        }

    }

    public interface OptionSelectionListener {
        void onSelectionOption1(View buttonSelected, int questionNumber, int answerSelected);
        void onSelectionOption2(View buttonSelected, int questionNumber, int answerSelected);
        void onSelectionOption3(View buttonSelected, int questionNumber, int answerSelected);
        void onSelectionOption4(View buttonSelected, int questionNumber, int answerSelected);

    }
}
