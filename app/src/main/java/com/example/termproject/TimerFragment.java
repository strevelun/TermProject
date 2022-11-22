package com.example.termproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class TimerFragment extends Fragment {

    private Chronometer chronometer;
    private boolean running;
    private long pauseOffset;
    private long score;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        chronometer = view.findViewById(R.id.chronometer);
        chronometer.setFormat("%s");

        Button startBtn = view.findViewById(R.id.start_btn);
        Button resetBtn = view.findViewById(R.id.reset_btn);
        TextView tv_score = view.findViewById(R.id.tv_score);

        //시작
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running){
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chronometer.start();
                    running = true;
                }
            }
        });



        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(running) {
                    score = SystemClock.elapsedRealtime() - chronometer.getBase();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    pauseOffset = 0;
                    chronometer.stop();
                    Toast.makeText(getContext(), score + "", Toast.LENGTH_SHORT).show();
                    score = 0;
                    running = false;
                }
            }
        });

        return view;
    }
}