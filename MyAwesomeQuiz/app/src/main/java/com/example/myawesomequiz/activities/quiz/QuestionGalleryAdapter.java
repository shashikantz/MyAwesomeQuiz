package com.example.myawesomequiz.activities.quiz;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myawesomequiz.R;
import com.example.myawesomequiz.models.Question;

import java.util.List;

public class QuestionGalleryAdapter
        extends RecyclerView.Adapter<QuestionViewHolder> {

    List<Question> questionList;

    Context context;

    public QuestionGalleryAdapter(List<Question> list,
                                  Context context)
    {
        this.questionList = list;
        this.context = context;
    }

    @Override
    public QuestionViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType)
    {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View questionView
                = inflater
                .inflate(R.layout.question_card,
                        parent, false);

        QuestionViewHolder viewHolder
                = new QuestionViewHolder(questionView, new QuestionViewHolder.OptionSelectionListener() {
            @Override
            public void onSelectionOption1(View btnSelected, int questionNumber, int selectedAnswer) {
                Log.d("QuestionAdapter >>" , "Question no: "+questionNumber+" ,clicked answer no: " + selectedAnswer + " Correct ans: "+ questionList.get(questionNumber).getCorrectAnswer() );
                questionList.get(questionNumber).setSelectedAnswer(1);
                changeOptionTextColor(btnSelected, isAnswerCorrect(questionNumber,selectedAnswer));
            }
            @Override
            public void onSelectionOption2(View btnSelected, int questionNumber, int selectedAnswer) {
                Log.d("QuestionAdapter >>" , "Question no: "+questionNumber+" ,clicked answer no: " + selectedAnswer + " Correct ans: "+ questionList.get(questionNumber).getCorrectAnswer() );
                questionList.get(questionNumber).setSelectedAnswer(2);
                changeOptionTextColor(btnSelected, isAnswerCorrect(questionNumber,selectedAnswer));
            }
            @Override
            public void onSelectionOption3(View btnSelected, int questionNumber, int selectedAnswer) {
                Log.d("QuestionAdapter >>" , "Question no: "+questionNumber+" ,clicked answer no: " + selectedAnswer + " Correct ans: "+ questionList.get(questionNumber).getCorrectAnswer() );
                questionList.get(questionNumber).setSelectedAnswer(3);
                changeOptionTextColor(btnSelected, isAnswerCorrect(questionNumber,selectedAnswer));
            }
            @Override
            public void onSelectionOption4(View btnSelected, int questionNumber, int selectedAnswer) {
                Log.d("QuestionAdapter >>" , "Question no: "+questionNumber+" ,clicked answer no: " + selectedAnswer + " Correct ans: "+ questionList.get(questionNumber).getCorrectAnswer() );
                questionList.get(questionNumber).setSelectedAnswer(4);
                changeOptionTextColor(btnSelected, isAnswerCorrect(questionNumber,selectedAnswer));
            }

        });
        return viewHolder;
    }

    private void changeOptionTextColor(View btnSelected, boolean isAnswerCorrect) {
        RadioButton rbSelected = (RadioButton) btnSelected;
        if (isAnswerCorrect){
            rbSelected.setTextColor(Color.GREEN);
        }
        else{
            rbSelected.setTextColor(Color.RED);
        }
    }

    private boolean isAnswerCorrect(int questionNumber, int selectedAnswer) {

        char correctAnswer = questionList.get(questionNumber).getCorrectAnswer();
        boolean isAnswerCorrect = false;
        //convert correct answers into integers
        switch (correctAnswer) {
            case 'A':
                if(selectedAnswer == 1){
                    isAnswerCorrect = true;
                }
                break;
            case 'B':
                if(selectedAnswer == 2){
                    isAnswerCorrect = true;
                }
                break;
            case 'C':
                if(selectedAnswer == 3){
                    isAnswerCorrect = true;
                }
                break;
            case 'D':
                if(selectedAnswer == 4){
                    isAnswerCorrect = true;
                }
                break;
            default:
                break;
        }

        return isAnswerCorrect;
    }

    @Override
    public void
    onBindViewHolder(final QuestionViewHolder viewHolder,
                     final int position)
    {
        viewHolder.textViewQuestionType.setText(questionList.get(position).getQtype() +" " +
                questionList.get(position).getQtype_value());
        viewHolder.textViewQuestionStatement
                .setText("Q." +questionList.get(position).getQno() + " "
                        + questionList.get(position).getQuestion());
        viewHolder.rb1
                .setText(questionList.get(position).getOption1());
        viewHolder.rb2
                .setText(questionList.get(position).getOption2());
        viewHolder.rb3
                .setText(questionList.get(position).getOption3());
        viewHolder.rb4
                .setText(questionList.get(position).getOption4());

        viewHolder.text_view_explanation.setText(questionList.get(position).getExplanation());

        Log.d("QuestionAdapter >>" , "Explanation >>>>" + questionList.get(position).getExplanation());

        /*
        RecyclerView recycles views. Earlier selected view can be used to show the offscreen item(question with
        different data). Problem: Different data but the preselected options in the recycled view.
        Following part addressed this problem.
         */

        viewHolder.hiddenView.setVisibility(View.GONE);

        int selectedAnswer = questionList.get(position).getSelectedAnswer();
        int questionNumber = position;
        if (selectedAnswer == -1) { // no option selected yet
            Log.d("QuestionAdapter >>" , "No option selected yet " );

            viewHolder.rbGroup.clearCheck();
            //reset the view to the default
            viewHolder.rb1.setTextColor(Color.BLACK);
            viewHolder.rb2.setTextColor(Color.BLACK);
            viewHolder.rb3.setTextColor(Color.BLACK);
            viewHolder.rb4.setTextColor(Color.BLACK);

        } else if (selectedAnswer == 1) {
            viewHolder.rb1.setChecked(true);
            changeOptionTextColor(viewHolder.rb1, isAnswerCorrect(questionNumber,selectedAnswer));
        }else if (selectedAnswer == 2) {
            viewHolder.rb2.setChecked(true);
            changeOptionTextColor(viewHolder.rb2, isAnswerCorrect(questionNumber,selectedAnswer));
        }else if (selectedAnswer == 3) {
            viewHolder.rb3.setChecked(true);
            changeOptionTextColor(viewHolder.rb3, isAnswerCorrect(questionNumber,selectedAnswer));
        }else if (selectedAnswer == 4) {
            viewHolder.rb4.setChecked(true);
            changeOptionTextColor(viewHolder.rb4, isAnswerCorrect(questionNumber,selectedAnswer));
        }

    }

    @Override
    public int getItemCount()
    {
        return questionList.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
