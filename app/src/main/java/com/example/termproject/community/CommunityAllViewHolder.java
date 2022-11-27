package com.example.termproject.community;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termproject.R;

public class CommunityAllViewHolder  extends RecyclerView.ViewHolder {

    public TextView tv_title;
    public ImageView iv_image;

    public CommunityAllViewHolder(Context context, @NonNull View itemView) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.tv_title);
    }
}
