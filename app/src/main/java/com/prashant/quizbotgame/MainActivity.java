package com.prashant.quizbotgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button playBtn, loginBtn;
    AppBarLayout applogo;
    private SQLiteDatabase quizbotdb;
    //    DatabaseReference reference;
    Question q1;
    List<Question> tempstorage = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewbieData nb = new NewbieData(this);
        //Firebase Playground



        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = database.child("Questions");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    q1=new Question(ds.child("quesID").getValue(String.class),
                            ds.child("quesName").getValue(String.class),
                            ds.child("quesDetail").getValue(String.class),
                            ds.child("option1").getValue(String.class),
                            ds.child("option2").getValue(String.class),
                            ds.child("option3").getValue(String.class),
                            ds.child("option4").getValue(String.class),
                            ds.child("c_Option").getValue(String.class),
                            ds.child("qcategory").getValue(String.class),
                            ds.child("done").getValue(String.class));
                    Log.i("coption", "onDataChange: "+ds.child("c_Option").getValue(String.class));
                    tempstorage.add(q1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        if(nb.getFirst()==0){
            nb.setFirst();

            final Handler handler = new Handler();
            final int delay = 500;
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(false);
            builder.setView(R.layout.progressbar);
            final AlertDialog dialog = builder.create();
            handler.postDelayed(new Runnable(){
                public void run(){
                    if(!tempstorage.isEmpty())//checking if the data is loaded or not
                    {
                        List<Question> allQuestions = new ArrayList<Question>();
                        allQuestions=tempstorage;
                        DBHelper dbHelper = new DBHelper(MainActivity.this);
                        dialog.dismiss();
                        for (int i=0;i < allQuestions.size();i++) {
                            dbHelper.addQuestion(allQuestions.get(i).getQuesName(),
                                    allQuestions.get(i).getQuesDetail(),
                                    allQuestions.get(i).getOption1(),
                                    allQuestions.get(i).getOption2(),
                                    allQuestions.get(i).getOption3(),
                                    allQuestions.get(i).getOption4(),
                                    allQuestions.get(i).getC_Option(),
                                    allQuestions.get(i).getQCategory(),
                                    allQuestions.get(i).getDone());
                        }
                    }
                    else {
                        dialog.show();
                        handler.postDelayed(this, delay);
                    }
                }
            }, delay);
        }
        else if(nb.getFirst() == 1){
            //DO Nothing

        }






        /*Animation Values Start*/


        applogo=findViewById(R.id.appBarLayout);
        playBtn = findViewById(R.id.playbtn);
        loginBtn = findViewById(R.id.loginbtn);
        final Animation FadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        final Animation SlideLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
        applogo.startAnimation(FadeIn);
        playBtn.startAnimation(FadeIn);
        loginBtn.startAnimation(SlideLeft);
        ObjectAnimator logoanim = ObjectAnimator.ofFloat(applogo, "translationY", 300f);
        ObjectAnimator playbtnanim = ObjectAnimator.ofFloat(playBtn, "translationY", -300f);
        final ObjectAnimator loginbtnanim = ObjectAnimator.ofFloat(loginBtn, "translationX", -00f);
        logoanim.setDuration(0);
        playbtnanim.setDuration(1000);
        loginbtnanim.setDuration(1000);
        logoanim.start();
        playbtnanim.start();
        loginbtnanim.start();



        /*Animation Values End*/

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                Intent IntQuizList = new Intent(getApplicationContext(), Categories.class);
                startActivity(IntQuizList, options.toBundle());
            }
        });
        playBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                playBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                Intent IntLoginScreen = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(IntLoginScreen, options.toBundle());
            }
        });
        loginBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                loginBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
    }




}
//public class MainActivity extends AppCompatActivity {
//
//    Question q1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("q1");
//
//        q1=new Question("01",
//                "Q.01",
//                "Identify the capital city of Vietnam.",
//                "Vientián",
//                "Phnom Penh",
//                "Bangkok",
//                "Hanói",
//                "Hanói",
//                "G.K.",
//                "N");
//
//        myRef.setValue(q1);
//    }
//}