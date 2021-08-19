package com.example.iPrep.Fragment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.iPrep.MainActivity2;
import com.example.iPrep.R;
import com.example.iPrep.classesadapter;
import com.example.iPrep.classmodel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VideoLecturestopics extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    VideoLecturesAdapter videoLecturesAdapter;
    private List<VideoLecturesModel> videoLecturesModellist;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String id;
    String classes;
    Long idd;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_lecturestopics);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(VideoLecturestopics.this));
        mRecyclerView.setVisibility(View.VISIBLE);

        videoLecturesModellist = new ArrayList<>();
        videoLecturesAdapter = new VideoLecturesAdapter(getApplicationContext(),videoLecturesModellist);
        mRecyclerView.setAdapter(videoLecturesAdapter);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        id = preferences.getString("id",null);
        SharedPreferences preferences1 = getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        classes = preferences1.getString("classes",null);
        SharedPreferences preferences2 = getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        idd = preferences2.getLong("uid",0);


        reference = FirebaseDatabase.getInstance().getReference("topics").child("cbse").child("english").child(classes).child("subjects").child(id).child("video_lessons").child(String.valueOf(idd)).child("topics");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()){
                    VideoLecturesModel videoLecturesModel = datasnapshot.getValue(VideoLecturesModel.class);
                    videoLecturesModellist.add(videoLecturesModel);

                }
                videoLecturesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }

}