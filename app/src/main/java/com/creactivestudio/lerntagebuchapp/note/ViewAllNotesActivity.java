package com.creactivestudio.lerntagebuchapp.note;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.creactivestudio.lerntagebuchapp.Helper;
import com.creactivestudio.lerntagebuchapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewAllNotesActivity extends AppCompatActivity {

    private CustomNoteRvAdapter customNoteRvAdapter;
    private RecyclerView rvAllNotes;
    private FloatingActionButton btnAddNote;
    Helper helper;
    private DatabaseHelper databaseHelper;
    private ArrayList<String> noteId, noteTitle, noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_notes);

        init();
        storeDataInArrays();

        customNoteRvAdapter=new CustomNoteRvAdapter(ViewAllNotesActivity.this, this, noteId, noteTitle, noteText);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            recreate();
        }
    }

    public void init()
    {
        helper=new Helper(this);
        rvAllNotes=findViewById(R.id.rvNotes);
        btnAddNote=findViewById(R.id.btnAddNote);
        databaseHelper=new DatabaseHelper(ViewAllNotesActivity.this);
        noteId=new ArrayList<>(); // Notizen ID
        noteTitle=new ArrayList<>(); // Notizen Title
        noteText=new ArrayList<>(); // Notizen Text
    }

    /**
     * Speicher alle Daten vom SQLite zu ArrayListen
     */
    public void storeDataInArrays ()
    {
        Cursor cursor= databaseHelper.readAllData();
        if(cursor.getCount()==0)
        {
            helper.showToast(getString(R.string.no_data), Helper.TOAST_MESSAGE_TYPE_ERROR);
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