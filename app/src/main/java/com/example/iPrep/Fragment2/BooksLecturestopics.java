package com.example.iPrep.Fragment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.iPrep.Fragment1.VideoLecturesAdapter;
import com.example.iPrep.Fragment1.VideoLecturesModel;
import com.example.iPrep.Fragment1.VideoLecturestopics;
import com.example.iPrep.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BooksLecturestopics extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    BookLecturesAdapter bookLecturesAdapter;
    private List<BookLecturesModel> bookLecturesModellist;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String id;
    String classes;
    Long idd;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_lecturestopics);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(BooksLecturestopics.this));
        mRecyclerView.setVisibility(View.VISIBLE);

        bookLecturesModellist = new ArrayList<>();
        bookLecturesAdapter = new BookLecturesAdapter(getApplicationContext(),bookLecturesModellist);
        mRecyclerView.setAdapter(bookLecturesAdapter);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        id = preferences.getString("id",null);
        SharedPreferences preferences1 = getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        classes = preferences1.getString("classes",null);
        SharedPreferences preferences2 = getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        idd = preferences2.getLong("uid",0);

        reference = FirebaseDatabase.getInstance().getReference("topics").child("cbse").child("english").child(classes).child("subjects").child(id).child("books_ncert").child(String.valueOf(idd)).child("topics");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()){
                    BookLecturesModel bookLecturesModel = datasnapshot.getValue(BookLecturesModel.class);
                    bookLecturesModellist.add(bookLecturesModel);

                }
                bookLecturesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}