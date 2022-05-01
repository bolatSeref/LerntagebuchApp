package com.creactivestudio.lerntagebuchapp.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.creactivestudio.lerntagebuchapp.R;
import com.creactivestudio.lerntagebuchapp.sqlite.DatabaseHelper;

import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity {

    EditText etNoteTitle, etNoteText;
    Button btnSaveNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        initViews();

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db=new DatabaseHelper(AddNoteActivity.this);
                db.addNote(etNoteTitle.getText().toString().trim(), etNoteText.getText().toString().trim());
            }
        });
    }

    public void initViews()
    {
        etNoteText=findViewById(R.id.etNoteText);
        etNoteTitle=findViewById(R.id.etNoteTitle);
        btnSaveNote=findViewById(R.id.btnSaveNote);
    }
}