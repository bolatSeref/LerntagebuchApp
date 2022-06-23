package com.creactivestudio.lerntagebuchapp.note;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creactivestudio.lerntagebuchapp.Helper;
import com.creactivestudio.lerntagebuchapp.R;

import java.util.ArrayList;

public class CustomNoteRvAdapter extends RecyclerView.Adapter <CustomNoteRvAdapter.MyViewHolder> {

    private Context context;
    Helper helper;
    Activity activity;
    private ArrayList noteId, noteTitle, noteText;


    /**
     * Constructor
     * @param activity
     * @param context
     * @param noteId  Note ID
     * @param noteTitle Note Überschrift
     * @param noteText  Note text
     */
    public CustomNoteRvAdapter(Activity activity, Context context, ArrayList noteId, ArrayList noteTitle, ArrayList noteText)
    {
        this.activity=activity;
        this.context=context;
        this.noteId=noteId;
        this.noteTitle=noteTitle;
        this.noteText=noteText;
        helper=new Helper(context);
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
        holder.imgCopyNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

                ClipData clip = ClipData.newPlainText("label", "Title: " +String.valueOf(noteTitle.get(position)) + "\n\n" + "Note: " +String.valueOf(noteText.get(position)));
                clipboard.setPrimaryClip(clip);
                helper.showToast(context.getString(R.string.your_note_is_copied_to_clipboard), Helper.TOAST_MESSAGE_TYPE_SUCCESS);

            }
        });

        // Um den Note per andere installierte Apps teilen zu können.
        holder.imgShareNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = context.getString(R.string.check_my_note);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareBody);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Title: " +String.valueOf(noteTitle.get(position)) + "\n\n" + "Note: " +String.valueOf(noteText.get(position)));
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share_via)));

            }
        });

        // Löscht den Note
        holder.imgDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.showToast(context.getString(R.string.your_note_is_deleted), Helper.TOAST_MESSAGE_TYPE_SUCCESS );
                DatabaseHelper databaseHelper=new DatabaseHelper(context);
                databaseHelper.deleteOneRow(String.valueOf(noteId.get(position)));

                activity.recreate();

            }
        });

        // Aktualiesiere den Note aber in Update Note Activity, deswegen schicken wir
        // benötigte Daten.
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

    /**
     * Beschreibt die Anzahl von Views, die in Recycler View angezeigt werden.
     * @return size
     */
    @Override
    public int getItemCount() {
        return noteId.size();
    }

    /**
     * Initialisiere die Views
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView noteId, noteTitle, noteText;
        ImageView imgUpdate, imgDeleteNote, imgShareNote, imgCopyNote;
        LinearLayout rvItemLinearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            noteId=itemView.findViewById(R.id.tvGoalId);
            noteTitle=itemView.findViewById(R.id.tvGoalTheme);
            noteText=itemView.findViewById(R.id.tvGoalTime);
            rvItemLinearLayout=itemView.findViewById(R.id.rv_item_linear_layout);
            imgUpdate=itemView.findViewById(R.id.imgUpdateGoal);
            imgDeleteNote=itemView.findViewById(R.id.imgDeleteGoal);
            imgShareNote=itemView.findViewById(R.id.imgShareNote);
            imgCopyNote=itemView.findViewById(R.id.imgCopyNote);

        }
    }
}
