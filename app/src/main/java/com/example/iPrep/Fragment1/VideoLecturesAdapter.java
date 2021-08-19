package com.example.iPrep.Fragment1;

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

import com.example.iPrep.Fragment2.ReadingBook;
import com.example.iPrep.R;

import java.util.List;

public class VideoLecturesAdapter extends RecyclerView.Adapter<VideoLecturesAdapter.ViewHolder>{

    private Context mContext;
    private List<VideoLecturesModel> videoLecturesModelList;

    public VideoLecturesAdapter(Context mContext, List<VideoLecturesModel> videoLecturesModelList) {
        this.mContext = mContext;
        this.videoLecturesModelList = videoLecturesModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.videolecturestopics, parent, false);
        return new VideoLecturesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final VideoLecturesModel videoLecturesModel = videoLecturesModelList.get(i);

        viewHolder.classes.setText(videoLecturesModel.getTopicName());
        viewHolder.subject.setText(videoLecturesModel.getName());
        viewHolder.topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("links",videoLecturesModel.getOnlineLink());
                editor.apply();
                Intent intent = new Intent(mContext, WatchVideo.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoLecturesModelList.size();
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
