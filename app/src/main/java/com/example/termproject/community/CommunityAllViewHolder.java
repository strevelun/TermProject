package com.example.termproject.community;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termproject.MainActivity;
import com.example.termproject.R;

public class CommunityAllViewHolder  extends RecyclerView.ViewHolder {

    public TextView tv_title;
    public ImageView iv_image;
    public TextView tv_content;
    public TextView tv_univ;
    public TextView tv_badge;

    public CommunityAllViewHolder(Context context, @NonNull View itemView) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.tv_title);
        iv_image = itemView.findViewById(R.id.iv_image);
        tv_content = itemView.findViewById(R.id.tv_content);
        tv_univ = itemView.findViewById(R.id.tv_univ);
        tv_badge = itemView.findViewById(R.id.tv_badge);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // context.startActivity()
            }
        });
    }
}
