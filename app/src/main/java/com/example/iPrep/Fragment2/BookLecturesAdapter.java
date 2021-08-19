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

import java.util.List;

public class BookLecturesAdapter extends RecyclerView.Adapter<BookLecturesAdapter.ViewHolder>{

    private Context mContext;
    private List<BookLecturesModel> bookLecturesModelList;

    public BookLecturesAdapter(Context mContext, List<BookLecturesModel> bookLecturesModelList) {
        this.mContext = mContext;
        this.bookLecturesModelList = bookLecturesModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bookslecturetopic, parent, false);
        return new BookLecturesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final BookLecturesModel bookLecturesModel = bookLecturesModelList.get(i);

        viewHolder.classes.setText(bookLecturesModel.getTopicName());
        viewHolder.subject.setText(bookLecturesModel.getName());
        viewHolder.topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("linkk",bookLecturesModel.getOnlineLink());
                editor.apply();
                Intent intent = new Intent(mContext, ReadingBook.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookLecturesModelList.size();
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
