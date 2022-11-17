package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private long backKeyPressedTime = 0;

     public void onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 한번더 누르면 종료", Toast.LENGTH_SHORT).show()
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

        replaceFragment(timerFragment);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.TimerTap :
                        replaceFragment(timerFragment);
                        break;
                    case R.id.RankingTap:
                        replaceFragment(rankingFragment);
                        break;
                    case R.id.CommunityTap:
                        replaceFragment(communityFragment);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }


    private void replaceFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
    }


    public void onDestroy() {
        super.onDestroy();

        // 앱을 종료할 때는 길찾기에 등록한 정보를 초기화(0과 공백문자로 세팅) 함
//        MyApplication.prefs.setString(getString(R.string.name), "")
//        MyApplication.prefs.putFloat(getString(R.string.myLatitude), 0.0f)
//        MyApplication.prefs.putFloat(getString(R.string.myLongitude), 0.0f)
//        MyApplication.prefs.putFloat(getString(R.string.destinationLatitude), 0.0f)
//        MyApplication.prefs.putFloat(getString(R.string.destinationLongitude), 0.0f)
    }
}