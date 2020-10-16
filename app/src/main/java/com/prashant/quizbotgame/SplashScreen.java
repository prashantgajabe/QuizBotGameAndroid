package com.prashant.quizbotgame;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;

import pl.droidsonroids.gif.GifImageView;

public class SplashScreen extends AppCompatActivity {
    AppBarLayout applogo;
    GifImageView loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        applogo=findViewById(R.id.appBarLayout);
        loading = findViewById(R.id.loading);
        ObjectAnimator logoanim = ObjectAnimator.ofFloat(applogo, "translationY", 300f);
        logoanim.setDuration(1000);
        logoanim.start();
        final Animation FadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        FadeOut.setDuration(1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.startAnimation(FadeOut);
            }
        },2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainActivity);
                finish();
            }
        },3000);
    }
}