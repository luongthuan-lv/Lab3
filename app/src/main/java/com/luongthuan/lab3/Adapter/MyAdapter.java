package com.luongthuan.lab3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luongthuan.lab3.Activity.Main2Activity;
import com.luongthuan.lab3.Model.Root;
import com.luongthuan.lab3.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public List<Root> rootList;
    public Context context;



    public MyAdapter(List<Root> rootList,Context context) {
        this.rootList = rootList;
        this.context=context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Root root=rootList.get(position);
        holder.tvTitle.setText(root.getTitle());
        holder.tvTime.setText(root.getDate());
        Log.e("yyyyyyyyy",root.getDate());
        holder.tvView.setText("HaHa");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Main2Activity.class);
                intent.putExtra("link",root.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rootList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvTime, tvView;
        public ImageView ivPicture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvView = itemView.findViewById(R.id.tvView);
            ivPicture = itemView.findViewById(R.id.ivPicture);

        }
    }


}
