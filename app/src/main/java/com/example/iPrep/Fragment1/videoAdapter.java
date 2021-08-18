package com.example.iPrep.Fragment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iPrep.R;
import com.example.iPrep.classesadapter;
import com.example.iPrep.classmodel;

import java.util.List;

public class videoAdapter extends RecyclerView.Adapter<videoAdapter.ViewHolder> {

    private Context mContext;
    private List<videosmodel> videosmodelList;

    public videoAdapter(Context mContext, List<videosmodel> videosmodelList) {
        this.mContext = mContext;
        this.videosmodelList = videosmodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.videos, parent, false);
        return new videoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final videosmodel classmodel = videosmodelList.get(i);

        viewHolder.classes.setText(classmodel.getTopicname());
        viewHolder.subject.setText(classmodel.getName());

    }

    @Override
    public int getItemCount() {
        return videosmodelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView subject,classes;
    RelativeLayout topLayout;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);


        classes = itemView.findViewById(R.id.title);
        subject = itemView.findViewById(R.id.subname);
        icon = itemView.findViewById(R.id.icon);
        topLayout = itemView.findViewById(R.id.linearclick);



    }
}
}
