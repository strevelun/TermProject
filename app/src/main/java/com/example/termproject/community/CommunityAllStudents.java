package com.example.termproject.community;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.termproject.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class CommunityAllStudents extends Fragment {

    RecyclerView recyclerView;
    CommunityRecyclerAdapter adapter;

    private FirebaseAuth auth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://termproject-3711d-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_all_students, container, false);

        databaseReference = database.getReference();
        auth = FirebaseAuth.getInstance();

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_all);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new CommunityRecyclerAdapter();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    adapter.clear();
                    for (DataSnapshot postSnapshot : snapshot.child("Post").getChildren()) {
                        Post post = postSnapshot.getValue(Post.class);
                        adapter.addItem(post);
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        recyclerView.setAdapter(adapter);

        return view;
    }
}