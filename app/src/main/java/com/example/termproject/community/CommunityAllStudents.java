package com.example.termproject.community;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.termproject.R;
import com.example.termproject.firebase.FirebaseConnection;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

public class CommunityAllStudents extends Fragment {

    RecyclerView recyclerView;
    CommunityRecyclerAdapter adapter;

    FirebaseConnection conn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_all_students, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_all);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new CommunityRecyclerAdapter();

        // 데이터 삽입
        adapter.addItem(new ListItem("첫번째", "내용입니다", "작성자1"));


        /*
        conn.DatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지 않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //반복문으로 데이터 리스트를 추출
                    User user = snapshot.getValue(User.class); // User 객체에 데이터 담는다
                    arrayList.add(user); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // db를 가져오던 중 에러 발생 시
                Log.e("MainActivity", String.valueOf(error.toException())); // 에러문 출력
            }
        });
         */

        recyclerView.setAdapter(adapter);

        return view;
    }
}