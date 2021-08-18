package com.example.iPrep.Fragment2;

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

import com.example.iPrep.R;
import com.example.iPrep.ReadBook.ReadingBook;


import java.util.List;

public class bookAdapter extends RecyclerView.Adapter<bookAdapter.ViewHolder> {
    private Context mContext;
    private List<bookmodel> bookmodelList;

    public bookAdapter(Context mContext, List<bookmodel> bookmodelList) {
        this.mContext = mContext;
        this.bookmodelList = bookmodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.books, parent, false);
        return new bookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final bookmodel Bookmodel = bookmodelList.get(i);

        viewHolder.classes.setText(Bookmodel.getTopicname());
        viewHolder.subject.setText(Bookmodel.getName());
        viewHolder.topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putLong("id",Bookmodel.getId());
                editor.apply();
                Intent intent = new Intent(mContext, ReadingBook.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookmodelList.size();
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
