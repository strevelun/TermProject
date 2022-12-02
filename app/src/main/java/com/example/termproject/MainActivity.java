package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.termproject.community.CommunityFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private long backKeyPressedTime = 0;

     public void onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 한번더 누르면 종료", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
            auth.signOut();
        }
    }

    public void onCreate(Bundle savedInstanceState) {


        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TimerFragment timerFragment  = new TimerFragment();
        RankingFragment rankingFragment = new RankingFragment();
        CommunityFragment communityFragment = new CommunityFragment();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        setFragment("timerFragment", timerFragment);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch(item.getItemId()){
                    case R.id.TimerTap :
                        setFragment("timerFragment", timerFragment);
                        break;
                    case R.id.RankingTap:
                        setFragment("rankingFragment", rankingFragment);
                        break;
                    case R.id.CommunityTap:
                        setFragment("communityFragment", communityFragment);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    void setFragment(String tag, Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if(manager.findFragmentByTag(tag) == null)
            ft.add(R.id.fragmentContainer, fragment, tag);

        Fragment timer = manager.findFragmentByTag("timerFragment");
        Fragment ranking = manager.findFragmentByTag("rankingFragment");
        Fragment community = manager.findFragmentByTag("communityFragment");

        if(timer != null)
            ft.hide(timer);

        if(ranking != null)
            ft.hide(ranking);

        if(community != null)
            ft.hide(community);

        if(tag == "timerFragment"){
            if(timer != null){
                ft.show(timer);
            }
        }

        if(tag == "rankingFragment"){
            if(ranking != null){
                ft.show(ranking);
            }
        }

        if(tag == "communityFragment"){
            if(community != null){
                ft.show(community);
            }
        }

        ft.commitAllowingStateLoss();
    }

    private void replaceFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
    }


    public void onDestroy() {
        super.onDestroy();
        PrefManager.clear(getApplicationContext());
    }
}