package com.example.termproject.community;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.termproject.R;
import com.google.android.material.tabs.TabLayout;

public class CommunityFragment extends Fragment {


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_community, container, false);

        ViewPager vp = view.findViewById(R.id.viewpager);
        CommunityPageAdapter communityPageAdapter = new CommunityPageAdapter(getFragmentManager());
        vp.setAdapter(communityPageAdapter);
        communityPageAdapter.setTitle("xx대 재학생");

        TabLayout tab = view.findViewById(R.id.tab);
        tab.setupWithViewPager(vp);


        return view;
    }
}