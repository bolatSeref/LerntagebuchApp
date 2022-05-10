package com.creactivestudio.lerntagebuchapp.note;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creactivestudio.lerntagebuchapp.R;

import java.util.ArrayList;

public class CustomNoteRvAdapter extends RecyclerView.Adapter <CustomNoteRvAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList noteId, noteTitle, noteText;


    public CustomNoteRvAdapter(Activity activity, Context context, ArrayList noteId, ArrayList noteTitle, ArrayList noteText)
    {
        this.activity=activity;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder,  int position) {

        holder.noteId.setText(String.valueOf(noteId.get(position)));
        holder.noteTitle.setText(String.valueOf(noteTitle.get(position)));
        holder.noteText.setText(String.valueOf(noteText.get(position)));
        holder.imgDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Your note will be deleted", Toast.LENGTH_SHORT).show();
            }
        });
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateNoteActivity.class);
                intent.putExtra("noteId",String.valueOf(noteId.get(position)));
                intent.putExtra("noteTitle",String.valueOf(noteTitle.get(position)));
                intent.putExtra("noteText",String.valueOf(noteText.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView noteId, noteTitle, noteText;
        ImageView imgUpdate, imgDeleteNote;
        LinearLayout rvItemLinearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            noteId=itemView.findViewById(R.id.tvZielId);
            noteTitle=itemView.findViewById(R.id.tvZielThema);
            noteText=itemView.findViewById(R.id.tvZielZeit);
            rvItemLinearLayout=itemView.findViewById(R.id.rv_item_linear_layout);
            imgUpdate=itemView.findViewById(R.id.imgUpdateZiel);
            imgDeleteNote=itemView.findViewById(R.id.imgDeleteZiel);
        }
    }
}
