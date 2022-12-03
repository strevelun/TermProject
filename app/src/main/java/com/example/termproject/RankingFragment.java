package com.example.termproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.termproject.community.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class RankingFragment extends Fragment {

    private static final int MILLISINFUTURE = 7*1000;
    private static final int COUNT_DOWN_INTERVAL = 1000;

    private int count = 5;
    private CountDownTimer countDownTimer;

    TextView tv_first;
    TextView tv_second;
    TextView tv_third;

    TextView tv_univ_first;
    TextView tv_univ_second;
    TextView tv_univ_third;

    private FirebaseAuth auth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://termproject-3711d-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference databaseReference;

    ArrayList<User> userList = new ArrayList<User>();

    Map<String, Integer> scoreMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        scoreMap.put("경북대학교", 0);
        scoreMap.put("영남대학교", 0);
        scoreMap.put("계명대학교", 0);

        databaseReference = database.getReference();
        auth = FirebaseAuth.getInstance();

        tv_first = view.findViewById(R.id.tv_first);
        tv_second = view.findViewById(R.id.tv_second);
        tv_third = view.findViewById(R.id.tv_third);

        tv_univ_first = view.findViewById(R.id.tv_univFirst);
        tv_univ_second = view.findViewById(R.id.tv_univSecond);
        tv_univ_third = view.findViewById(R.id.tv_univThird);

        countDownTimer();

        bringUsers();

        //countDownTimer.start();

        return view;
    }

    public void countDownTimer(){

        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            public void onTick(long millisUntilFinished) {
                count --;
            }
            public void onFinish() {
                bringUsers();
            }
        };
    }

    public void bringUsers() {



        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    userList.clear();
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        User user = postSnapshot.getValue(User.class);
                        String univ = user.getUniv();
                        int s = scoreMap.get(univ);
                        scoreMap.put(univ, s + Integer.parseInt(user.getBadge()));
                        userList.add(user);
                    }
                    Collections.sort(userList, Collections.reverseOrder());

                    List<Map.Entry<String, Integer>> list_entries = new ArrayList<Map.Entry<String, Integer>>(scoreMap.entrySet());

                    Collections.sort(list_entries, new Comparator<Map.Entry<String, Integer>>() {
                        public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
                            return obj1.getValue().compareTo(obj2.getValue());
                        }
                    });

                    tv_univ_first.setText(list_entries.get(2).getKey());
                    tv_univ_second.setText(list_entries.get(1).getKey());
                    tv_univ_third.setText(list_entries.get(0).getKey());

                    tv_first.setText(userList.get(0).Name);
                    tv_second.setText(userList.get(1).Name);
                    tv_third.setText(userList.get(2).Name);

                    //Toast.makeText(requireContext(), "갱신완료", Toast.LENGTH_SHORT).show();



                    count = 5;
                    countDownTimer.start();
                } catch (Exception e) {
                    userList.clear();
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}