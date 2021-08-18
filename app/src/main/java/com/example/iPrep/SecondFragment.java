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
import com.example.iPrep.Fragment2.bookAdapter;
import com.example.iPrep.Fragment2.bookmodel;
import com.example.iPrep.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    private RecyclerView recyclerView;
    private bookAdapter bookAdapter;
    private List<bookmodel> mmbookmodellist;

    DatabaseReference reference;
    long id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_second, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mmbookmodellist = new ArrayList<>();
        bookAdapter = new bookAdapter(getContext(),mmbookmodellist);
        recyclerView.setAdapter(bookAdapter);

        SharedPreferences preferences = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        id = preferences.getLong("id",0);

        reference = FirebaseDatabase.getInstance().getReference("classes").child(String.valueOf(id)).child("videolectures");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()){
                    bookmodel videosmodel1 = datasnapshot.getValue(bookmodel.class);
                    mmbookmodellist.add(videosmodel1);

                }
                bookAdapter = new bookAdapter(getContext(),mmbookmodellist);
                recyclerView.setAdapter(bookAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}