package com.prashant.quizbotgame;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.prashant.quizbotgame.databinding.QuizlistBinding;
import com.prashant.quizbotgame.databinding.QuizplayBinding;

import java.util.List;

//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;

public class QuizList extends AppCompatActivity {
    private QuizlistBinding QLBinding;
    private QuizplayBinding QPBinding;
    private View mSharedElement;
    private DownloadManager.Query query;
//    private QuestionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QLBinding= DataBindingUtil.setContentView(this,R.layout.quizlist);
        final View logoimage;
        final View logoimage2;
//        query= FirebaseDatabase.getInstance().getReference().child("Question");
//        FirebaseRecyclerOptions<Question> qoptions = new FirebaseRecyclerOptions<Question>().setQuery(query, Question.class).build();
//        adapter = new QuestionAdapter(qoptions);
        logoimage=findViewById(R.id.logoimage);
//        final Data DataHelper = new Data();
        final DBHelper DBHelper = new DBHelper(this);
        final List<Question> QuestionList = DBHelper.getAllQuestions();




        final Intent getCat = getIntent();
        final Connector connector = new Connector(DBHelper.findQuestionByCategory(getCat.getStringExtra("category")), this);









        QLBinding.questionList.setLayoutManager(new LinearLayoutManager(this));
        QLBinding.questionList.setAdapter(connector);

        connector.setOnItemClickListener(new Connector.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent askquestion = new Intent(getApplicationContext(), QuizPlay.class);
                askquestion.putExtra("position", position);
                askquestion.putExtra("category", getCat.getStringExtra("category"));
                //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(QuizList.this, logoimage,"explode");
                startActivity(askquestion);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Intent IntentFromMain = getIntent();
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}