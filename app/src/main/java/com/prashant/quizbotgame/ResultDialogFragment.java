package com.prashant.quizbotgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class ResultDialogFragment extends DialogFragment {
    TextView txtscore;
    static ResultDialogFragment newInstance() {
        ResultDialogFragment f = new ResultDialogFragment();
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Animation FadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        String score = getArguments().getString("score");
        View v = inflater.inflate(R.layout.fragment_result_dialog, container, false);
        v.startAnimation(FadeIn);
        TextView txtscore = v.findViewById(R.id.txtscore);
        txtscore.setText(score);

        // Watch for button clicks.
        Button button = (Button)v.findViewById(R.id.playagainbtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
            }
        });
        return v;
    }
}