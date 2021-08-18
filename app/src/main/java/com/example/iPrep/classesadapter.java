package com.example.iPrep;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.List;

public class classesadapter extends RecyclerView.Adapter<classesadapter.ViewHolder> {

    private Context mContext;
    private List<classmodel> classmodelslists;

    public classesadapter(Context mContext, List<classmodel> classmodelslists, String id) {
        this.mContext = mContext;
        this.classmodelslists = classmodelslists;
    }

    @NonNull
    @Override
    public classesadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.upcomingevents, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull classesadapter.ViewHolder viewHolder, int i) {
    final classmodel classmodel = classmodelslists.get(i);
        Glide.with(mContext).load(classmodel.getIcon()).into(viewHolder.icon);
        viewHolder.classes.setText(classmodel.getClss());
        viewHolder.subject.setText(classmodel.getSubject());

        viewHolder.topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putLong("id",classmodel.getId());
                editor.apply();
                Intent intent = new Intent(mContext,VideoLecture.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classmodelslists.size();
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
