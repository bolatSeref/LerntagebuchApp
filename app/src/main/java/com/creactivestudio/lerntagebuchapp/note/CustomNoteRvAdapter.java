package com.creactivestudio.lerntagebuchapp.note;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creactivestudio.lerntagebuchapp.R;

import java.util.ArrayList;

public class CustomNoteRvAdapter extends RecyclerView.Adapter <CustomNoteRvAdapter.MyViewHolder> {

    private Context context;
    private ArrayList noteId, noteTitle, noteText;


    public CustomNoteRvAdapter(Context context, ArrayList noteId, ArrayList noteTitle, ArrayList noteText)
    {
        this.context=context;
        this.noteId=noteId;
        this.noteTitle=noteTitle;
        this.noteText=noteText;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.note_rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.noteId.setText(String.valueOf(noteId.get(position)));
        holder.noteTitle.setText(String.valueOf(noteTitle.get(position)));
        holder.noteText.setText(String.valueOf(noteText.get(position)));
        holder.rvItemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateNoteActivity.class);
                intent.putExtra("noteId",String.valueOf(noteId.get(position)));
                intent.putExtra("noteTitle",String.valueOf(noteTitle.get(position)));
                intent.putExtra("noteText",String.valueOf(noteText.get(position)));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView noteId, noteTitle, noteText;
        LinearLayout rvItemLinearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            noteId=itemView.findViewById(R.id.tvNoteId);
            noteTitle=itemView.findViewById(R.id.tvNoteTitle);
            noteText=itemView.findViewById(R.id.tvNoteText);
            rvItemLinearLayout=itemView.findViewById(R.id.rv_item_linear_layout);
        }
    }
}