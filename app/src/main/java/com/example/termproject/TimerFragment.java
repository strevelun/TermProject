package com.example.termproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TimerFragment extends Fragment {

    private Chronometer chronometer;
    private boolean running;
    private long pauseOffset;
    private long score;

    private FirebaseAuth auth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://termproject-3711d-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        databaseReference = database.getReference();
        auth = FirebaseAuth.getInstance();

        chronometer = view.findViewById(R.id.chronometer);
        chronometer.setFormat("%s");

        Button startBtn = view.findViewById(R.id.start_btn);
        Button resetBtn = view.findViewById(R.id.reset_btn);
        //TextView tv_score = view.findViewById(R.id.tv_score);

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
                    running = false;

                    String uid = auth.getUid();
                    databaseReference.child("Users").child(uid).child("Badge").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String badge = snapshot.getValue(String.class);
                            long badgeInt = Integer.parseInt(badge);
                            long result = badgeInt + score;
                            Toast.makeText(requireContext(), result+"", Toast.LENGTH_SHORT).show();
                            databaseReference.child("Users").child(uid).child("Badge").setValue(result+"");
                            score = 0;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        return view;
    }
}