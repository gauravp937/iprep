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
import com.example.iPrep.Fragment2.booksmodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    private RecyclerView recyclerView;

    private bookAdapter bookadapter;
    private List<booksmodel> mmBooksmodellist;

    DatabaseReference reference;
    String id;
    String classes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_second, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mmBooksmodellist = new ArrayList<>();
        bookadapter = new bookAdapter(getContext(),mmBooksmodellist);
        recyclerView.setAdapter(bookadapter);

        SharedPreferences preferences = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        id = preferences.getString("id",null);
        SharedPreferences preferences1 = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        classes = preferences1.getString("classes",null);

        reference = FirebaseDatabase.getInstance().getReference("topics").child("cbse").child("english").child(classes).child("subjects").child(id).child("books_ncert");



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()){
                    booksmodel videosmodel1 = datasnapshot.getValue(booksmodel.class);
                    mmBooksmodellist.add(videosmodel1);

                }
                bookadapter = new bookAdapter(getContext(),mmBooksmodellist);
                recyclerView.setAdapter(bookadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}