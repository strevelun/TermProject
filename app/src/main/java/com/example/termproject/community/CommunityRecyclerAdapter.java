package com.example.termproject.community;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommunityRecyclerAdapter extends RecyclerView.Adapter<CommunityAllViewHolder> {

    private ArrayList<Post> list = new ArrayList<>();

    private FirebaseAuth auth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://termproject-3711d-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference databaseReference;

    public CommunityRecyclerAdapter() {
        databaseReference = database.getReference();
        auth = FirebaseAuth.getInstance();


    }

    @NonNull
    @Override
    public CommunityAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, parent, false);

        CommunityAllViewHolder viewholder = new CommunityAllViewHolder(context, view);


        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityAllViewHolder holder, int position) {
        String title = list.get(position).title;
        String content = list.get(position).content;
        String writer = list.get(position).writer;
        String uri = list.get(position).uri;
        String univ = list.get(position).univ;

        holder.tv_title.setText(title);
        holder.tv_content.setText(content);
        holder.iv_image.setImageURI(Uri.parse(uri));
        holder.tv_univ.setText(univ + " 학생");

        databaseReference.child("Users").child(writer).child("Badge").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String badge = snapshot.getValue(String.class);
                long badgeInt = Integer.parseInt(badge);

                if(badgeInt <= 3000){
                    holder.tv_badge.setBackgroundColor(Color.GRAY);
                    holder.tv_badge.setText("0");
                }
                else if(badgeInt <= 6000) {
                    holder.tv_badge.setBackgroundColor(Color.rgb(0, 255, 255));
                    holder.tv_badge.setText("1");
                }
                else if(badgeInt <= 9000) {
                    holder.tv_badge.setBackgroundColor(Color.rgb(255, 191, 0));
                    holder.tv_badge.setText("2");
                }
                else if(badgeInt <= 12000) {
                    holder.tv_badge.setBackgroundColor(Color.rgb(136, 8, 8));
                    holder.tv_badge.setText("3");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void addItem(Post item) {
        list.add(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() { list.clear();}
}
