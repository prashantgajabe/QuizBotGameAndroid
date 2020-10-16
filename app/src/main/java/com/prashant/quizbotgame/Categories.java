package com.prashant.quizbotgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.prashant.quizbotgame.databinding.ActivityCategoriesBinding;
import com.prashant.quizbotgame.databinding.CategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class Categories extends AppCompatActivity {

    private ActivityCategoriesBinding categoriesBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoriesBinding = DataBindingUtil.setContentView(this,R.layout.activity_categories);

        final DBHelper DBHelper = new DBHelper(this);
        final List<String> categoryList = DBHelper.AllCategories();
        final CategoryAdapter connector = new CategoryAdapter(DBHelper.AllCategories(),this);
        categoriesBinding.categoryList.setLayoutManager( new LinearLayoutManager(this));
        categoriesBinding.categoryList.setAdapter(connector);

        connector.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent gotoquiz = new Intent(getApplicationContext(), QuizList.class);
                gotoquiz.putExtra("category", categoryList.get(position));
                startActivity(gotoquiz);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}