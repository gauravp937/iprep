package com.example.iPrep.Fragment2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iPrep.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadingBook extends AppCompatActivity {
    TextView textView;
    private PDFView pdfView;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_book);
        pdfView = findViewById(R.id.pdfviewer);
        textView = findViewById(R.id.text1);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}