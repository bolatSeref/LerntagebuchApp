package com.creactivestudio.lerntagebuchapp.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.creactivestudio.lerntagebuchapp.MainActivity;
import com.creactivestudio.lerntagebuchapp.R;
import com.creactivestudio.lerntagebuchapp.sqlite.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewAllNotesActivity extends AppCompatActivity {

    private CustomNoteRvAdapter customNoteRvAdapter;
    RecyclerView rvAllNotes;
    FloatingActionButton btnAddNote;

    DatabaseHelper databaseHelper;
    ArrayList<String> noteId, noteTitle, noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_notes);

        initViews ();
        storeDataInArrays();

        customNoteRvAdapter=new CustomNoteRvAdapter(ViewAllNotesActivity.this,noteId, noteTitle, noteText);
        rvAllNotes.setAdapter(customNoteRvAdapter);
        rvAllNotes.setLayoutManager(new LinearLayoutManager(ViewAllNotesActivity.this));

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViewAllNotesActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initViews()
    {
        rvAllNotes=findViewById(R.id.rvNotes);
        btnAddNote=findViewById(R.id.btnAddNote);
        databaseHelper=new DatabaseHelper(ViewAllNotesActivity.this);
        noteId=new ArrayList<>();
        noteTitle=new ArrayList<>();
        noteText=new ArrayList<>();



    }

    public void storeDataInArrays ()
    {
        Cursor cursor= databaseHelper.readAllData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                noteId.add(cursor.getString(0));
                noteTitle.add(cursor.getString(1));
                noteText.add(cursor.getString(2));
            }
        }

    }
}