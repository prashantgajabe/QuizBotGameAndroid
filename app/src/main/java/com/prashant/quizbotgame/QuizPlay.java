package com.prashant.quizbotgame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.List;

public class QuizPlay extends AppCompatActivity {
    TextView txtQNumber,txtQDetail,txtResult;
    Button btn_back, btn_next, btn_tryagain;
    RadioGroup Options;
    RadioButton radioButton, radioButton2, radioButton3, radioButton4;
    String answer="";
    int position;
    int score=0;
    boolean backpress = false;
    int trials = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizplay);
        Intent myIntent = getIntent();
        position = myIntent.getIntExtra("position", 0);
        final DBHelper d = new DBHelper(this);
        final List<Question> quizlist = d.findQuestionByCategory(myIntent.getStringExtra("category"));
        final Handler handler = new Handler();
        final Animation FadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        final Animation FadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        txtResult = findViewById(R.id.txtResult);
        txtQNumber = findViewById(R.id.txtQNumber);
        txtQDetail = findViewById(R.id.txtQDetail);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        Options = findViewById(R.id.radioGroup);
        answer = quizlist.get(position).getC_Option();
        btn_tryagain = findViewById(R.id.btn_tryagain);
        btn_tryagain.setVisibility(View.INVISIBLE);
        txtResult.setVisibility(View.INVISIBLE);



        txtQNumber.setText( "Question #"+quizlist.get(position).getQuesID());
        txtQDetail.setText(quizlist.get(position).getQuesDetail());
        radioButton.setText(quizlist.get(position).getOption1());
        radioButton2.setText(quizlist.get(position).getOption2());
        radioButton3.setText(quizlist.get(position).getOption3());
        radioButton4.setText(quizlist.get(position).getOption4());





        radioButton.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btn_next.setEnabled(true);
                if(radioButton.isChecked() == true){
                    radioButton.setTextColor(Color.parseColor("#00cdcd"));
                } else{
                    radioButton.setTextColor(Color.WHITE);
                }
            }
        });

        radioButton2.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btn_next.setEnabled(true);
                if(radioButton2.isChecked() == true){
                    radioButton2.setTextColor(Color.parseColor("#00cdcd"));
                } else{
                    radioButton2.setTextColor(Color.WHITE);
                }
            }
        });

        radioButton3.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btn_next.setEnabled(true);
                if(radioButton3.isChecked() == true){
                    radioButton3.setTextColor(Color.parseColor("#00cdcd"));
                } else{
                    radioButton3.setTextColor(Color.WHITE);
                }
            }
        });

        radioButton4.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btn_next.setEnabled(true);
                if(radioButton4.isChecked() == true){
                    radioButton4.setTextColor(Color.parseColor("#00cdcd"));
                } else{
                    radioButton4.setTextColor(Color.WHITE);
                }
            }
        });

        btn_next = findViewById(R.id.btn_next);
        btn_next.setEnabled(false);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int RButtonID = Options.getCheckedRadioButtonId();
                View RButton = Options.findViewById(RButtonID);
                int RadioID = Options.indexOfChild(RButton);
                RadioButton r = (RadioButton) Options.getChildAt(RadioID);
                String RadioText = r.getText().toString();
                btn_next.setEnabled(false);
                answer = quizlist.get(position).getC_Option();

                if(RadioText.equals(answer)){
                    radioButton.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setEnabled(false);
                    r.setTextColor(Color.GREEN);
                    txtResult.setTextColor(Color.GREEN);
                    txtResult.setText("CORRECT!");
                    txtResult.setTypeface(txtResult.getTypeface(), Typeface.BOLD_ITALIC);
                    txtResult.setVisibility(View.VISIBLE);
                    btn_tryagain.setVisibility(View.INVISIBLE);

                    radioButton.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    d.updateSingleQuestionDone(Integer.parseInt(quizlist.get(position).getQuesID()), "Y");
                    score++;


                    //Next Question Code Starts Here.
                    if(position < (quizlist.size()-1)){
                        Log.i("positionlist", "onClick: "+position);
                        position = position+1;
                        Options.clearCheck();
                        btn_tryagain.setVisibility(View.INVISIBLE);
                        txtQNumber.startAnimation(FadeOut);
                        txtQDetail.startAnimation(FadeOut);
                        radioButton.startAnimation(FadeOut);
                        radioButton2.startAnimation(FadeOut);
                        radioButton3.startAnimation(FadeOut);
                        radioButton4.startAnimation(FadeOut);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txtResult.setVisibility(View.INVISIBLE);
                                txtQNumber.setText( "Question #"+quizlist.get(position).getQuesID());
                                txtQDetail.setText(quizlist.get(position).getQuesDetail());
                                radioButton.setText(quizlist.get(position).getOption1());
                                radioButton2.setText(quizlist.get(position).getOption2());
                                radioButton3.setText(quizlist.get(position).getOption3());
                                radioButton4.setText(quizlist.get(position).getOption4());
                                radioButton.setEnabled(true);
                                radioButton2.setEnabled(true);
                                radioButton3.setEnabled(true);
                                radioButton4.setEnabled(true);
                                radioButton.setChecked(false);
                                radioButton2.setChecked(false);
                                radioButton3.setChecked(false);
                                radioButton4.setChecked(false);
                                radioButton.setTextColor(Color.WHITE);
                                radioButton2.setTextColor(Color.WHITE);
                                radioButton3.setTextColor(Color.WHITE);
                                radioButton4.setTextColor(Color.WHITE);
                                txtQNumber.startAnimation(FadeIn);
                                txtQDetail.startAnimation(FadeIn);
                                radioButton.startAnimation(FadeIn);
                                radioButton2.startAnimation(FadeIn);
                                radioButton3.startAnimation(FadeIn);
                                radioButton4.startAnimation(FadeIn);
                            }
                        }, 990);
                    } else {
                        showDialog();
                    }
                } else{
                    trials--;
                    radioButton.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setEnabled(false);
                    radioButton.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    r.setTextColor(Color.RED);
                    txtResult.setTextColor(Color.RED);
                    txtResult.setText("INCORRECT! " + "Tries Left: " + trials);
                    txtResult.setTypeface(txtResult.getTypeface(), Typeface.BOLD_ITALIC);
                    txtResult.setVisibility(View.VISIBLE);
                    btn_tryagain.setVisibility(View.VISIBLE);
                }
            }
        });


        btn_tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResult.setVisibility(View.INVISIBLE);
                btn_tryagain.setVisibility(View.INVISIBLE);
                btn_next.setEnabled(false);
                radioButton.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton.setTextColor(Color.WHITE);
                radioButton2.setTextColor(Color.WHITE);
                radioButton3.setTextColor(Color.WHITE);
                radioButton4.setTextColor(Color.WHITE);
                if(trials==0){
                    QuizPlay.this.finish();
                }
            }
        });



        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backpress=true; QuizPlay.this.finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    void showDialog() {
        // Create and show the dialog.
        Bundle bundle = new Bundle();
        bundle.putString("score", "Score: " + score);
        DialogFragment newFragment = ResultDialogFragment.newInstance();
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(),"dialog");
    }
}
