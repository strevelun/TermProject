package com.example.termproject.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termproject.R;

import java.util.ArrayList;

public class CommunityRecyclerAdapter extends RecyclerView.Adapter<CommunityAllViewHolder> {

    private ArrayList<ListItem> list = new ArrayList<>();

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

        holder.tv_title.setText(title);
        // holder.tv_title.setText(title);
        // holder.tv_title.setText(title);
    }

    public void addItem(ListItem item) {
        list.add(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
