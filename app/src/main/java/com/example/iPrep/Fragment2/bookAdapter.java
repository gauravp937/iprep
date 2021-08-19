package com.example.iPrep.Fragment2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iPrep.Fragment1.VideoLecturestopics;
import com.example.iPrep.Fragment1.videosmodel;
import com.example.iPrep.R;

import java.util.List;

public class bookAdapter extends RecyclerView.Adapter<bookAdapter.ViewHolder> {

    private Context mContext;
    private List<booksmodel> booksmodelList;

    public bookAdapter(Context mContext, List<booksmodel> booksmodelList) {
        this.mContext = mContext;
        this.booksmodelList = booksmodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.books, parent, false);
        return new bookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final booksmodel classmodel = booksmodelList.get(i);

        viewHolder.classes.setText(classmodel.getName());
        viewHolder.topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("idd",classmodel.getName());
                editor.putLong("uid",classmodel.getUid());
                editor.apply();
                Intent intent = new Intent(mContext, BooksLecturestopics.class);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return booksmodelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


    public TextView classes;
    RelativeLayout topLayout;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);


        classes = itemView.findViewById(R.id.title);
        topLayout = itemView.findViewById(R.id.linearclick);



    }
}
}
