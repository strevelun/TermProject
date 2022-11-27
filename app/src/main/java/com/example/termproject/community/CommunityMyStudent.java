package com.example.termproject.community;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.termproject.R;

public class CommunityMyStudent extends Fragment {

    RecyclerView recyclerView;
    CommunityRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_my_student, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_student);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new CommunityRecyclerAdapter();

        // 데이터 삽입
        adapter.addItem(new ListItem("첫번째221212", "내용입니다", "작성자2"));

        recyclerView.setAdapter(adapter);

        return view;
    }
}