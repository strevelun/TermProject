package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://termproject-3711d-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_register = findViewById(R.id.btn_register);
        Button btn_login = findViewById(R.id.btn_login);
        auth = FirebaseAuth.getInstance();
        TextView tv_email = findViewById(R.id.tv_email);
        TextView tv_password  = findViewById(R.id.tv_password);
        databaseReference = database.getReference();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = tv_email.getText().toString();
                String password = tv_password.getText().toString();

                if(email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "이메일을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Toast.makeText(getApplicationContext(), "로그인 성공!", Toast.LENGTH_LONG).show();
                            FirebaseUser currentUser = auth.getCurrentUser();
                            String uid = currentUser.getUid();
                            databaseReference.child("Users").child(uid).child("Univ").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    //Toast.makeText(getApplicationContext(), snapshot.getValue(String.class), Toast.LENGTH_LONG).show();
                                    PrefManager.setString(getApplicationContext(), "Univ", snapshot.getValue(String.class));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            databaseReference.child("Users").child(uid).child("Badge").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    PrefManager.setString(getApplicationContext(), "Badge", snapshot.getValue(String.class));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "로그인 오류 : " + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}