package com.example.iPrep;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iPrep.Fragment1.videoAdapter;
import com.example.iPrep.Fragment1.videosmodel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private RecyclerView recyclerView;

    private videoAdapter videoadapter;
    private List<videosmodel> mmVideosmodellist;

    DatabaseReference reference;
    String id;
    String classes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mmVideosmodellist = new ArrayList<>();
        videoadapter = new videoAdapter(getContext(),mmVideosmodellist);
        recyclerView.setAdapter(videoadapter);

        SharedPreferences preferences = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        id = preferences.getString("id",null);
        SharedPreferences preferences1 = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        classes = preferences.getString("classes",null);

        reference = FirebaseDatabase.getInstance().getReference("topics").child("cbse").child("english").child(classes).child("subjects").child(id).child("video_lessons");



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()){
                    videosmodel videosmodel1 = datasnapshot.getValue(videosmodel.class);
                    mmVideosmodellist.add(videosmodel1);

                }
                videoadapter = new videoAdapter(getContext(),mmVideosmodellist);
                recyclerView.setAdapter(videoadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}