package com.example.termproject.community;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.termproject.PrefManager;
import com.example.termproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteActivity extends AppCompatActivity {

    TextView tv_title, tv_content;

    ImageView iv_find;
    Button btn_submit;
    Uri uri = null;

    private FirebaseAuth auth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://termproject-3711d-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        databaseReference = database.getReference();
        auth = FirebaseAuth.getInstance();
        tv_title = findViewById(R.id.et_title);
        tv_content = findViewById(R.id.et_content);

        // 파이어베이스에 이미지 올리기
        iv_find = findViewById(R.id.iv_find);
        iv_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1000);
            }
        });

        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 파이어베이스에 올리고 finish

                String title = tv_title.getText().toString();
                String content = tv_content.getText().toString();

                if(title.isEmpty()){
                    Toast.makeText(getApplicationContext(), "제목을 입력해주세요!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(content.isEmpty()){
                    Toast.makeText(getApplicationContext(), "내용을 입력해주세요!", Toast.LENGTH_LONG).show();
                    return;
                }

                FirebaseUser currentUser = auth.getCurrentUser();
                String uid = currentUser.getUid();

                Post post = new Post(uid, title, content, uri.toString(),
                        PrefManager.getString(getApplicationContext(), "Univ"),
                        PrefManager.getString(getApplicationContext(), "Badge")); // 처음 로그인할때 pref에 저장한 후 badge 입력

                databaseReference.child("Posts").push().setValue(post);

                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 1000){
            try {
                uri = data.getData();
                iv_find.setImageURI(uri);
            }catch(Exception e) {
                Toast.makeText(getApplicationContext(), "이미지를 가져오는데 문제가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }
}