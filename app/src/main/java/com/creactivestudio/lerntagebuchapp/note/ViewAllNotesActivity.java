package com.creactivestudio.lerntagebuchapp.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.creactivestudio.lerntagebuchapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewAllNotesActivity extends AppCompatActivity {

    RecyclerView rvAllNotes;
    FloatingActionButton btnAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_notes);

        initViews ();

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

    }
}