package com.example.iPrep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ImageView signout;
    private RecyclerView mRecyclerView;
    private classesadapter classesadapter;
    private List<classmodel> classmodelList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        signout = findViewById(R.id.notification);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
        mRecyclerView.setVisibility(View.VISIBLE);

        classmodelList = new ArrayList<>();
        classesadapter = new classesadapter(this,classmodelList,id);
        mRecyclerView.setAdapter(classesadapter);

        reference = FirebaseDatabase.getInstance().getReference("classes");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()){
                    classmodel classmodel1 = datasnapshot.getValue(classmodel.class);
                    classmodelList.add(classmodel1);

                }
                classesadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                finish();
            }
        });

   }
}