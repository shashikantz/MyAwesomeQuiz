package com.example.myawesomequiz.activities.quiz;
// ViewHolder code for RecyclerView


import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myawesomequiz.R;

public class QuestionViewHolder
        extends RecyclerView.ViewHolder implements View.OnClickListener {
    final String TAG = "QuestionViewHolder";
    ToggleButton btn_favorite;
    Button btn_explanation;
    TextView text_view_explanation;
    TextView textViewQuestionStatement;
    RadioGroup rbGroup;

    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;

    LinearLayout hiddenView;
    CardView cardView;

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


        btn_explanation = questionView.findViewById(R.id.btn_explanation);
        hiddenView = questionView.findViewById(R.id.hidden_view_explanation);
        text_view_explanation = questionView.findViewById(R.id.text_view_explanation);

        btn_explanation.setOnClickListener(this);

        btn_favorite = questionView.findViewById(R.id.btn_favorite);
        btn_favorite.setOnClickListener(this);
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
            case R.id.btn_explanation:
                // If the CardView is already expanded, set its visibility
                //  to gone and change the expand less icon to expand more.
                if (hiddenView.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    TransitionManager.beginDelayedTransition(hiddenView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    TransitionManager.beginDelayedTransition(hiddenView,
                            new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.btn_favorite:
                listener.onFavoriteBtnSelection(btnSelected, getAdapterPosition());
//                Log.d(TAG, "Favorite btn selected.");
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
        void onFavoriteBtnSelection(View buttonSelected, int adapterPosition);

    }
}
