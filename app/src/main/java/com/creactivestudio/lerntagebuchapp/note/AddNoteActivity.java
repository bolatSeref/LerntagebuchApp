package com.creactivestudio.lerntagebuchapp.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        
    }

    public void initViews()
    {
        etNoteText=findViewById(R.id.etNoteText);
        etNoteTitle=findViewById(R.id.etNoteTitle);
        btnSaveNote=findViewById(R.id.btnSaveNote);
    }

    /**
     * Zunäcst kontrolliere EditTexts wenn den Benutzer Notizen eingeschrieben ist dann
     * speicher, wenn nicht gib eine Nachrict
     * @param view
     */
    public void saveNote (View view) {
        // TODO: 25.05.22 kontrolliere ob der eingaben richtig und vollständig sind und schreib ein method 

        if (checkEditText(etNoteText) & checkEditText(etNoteTitle)) // Kontrolliere ob EditText data hat wenn ja speicher
        {
            DatabaseHelper db=new DatabaseHelper(AddNoteActivity.this);
            db.addNote(etNoteTitle.getText().toString().trim(), etNoteText.getText().toString().trim());

            // Wenn der Notiz erfolgreich eingespeichert ist dann intent
            startActivity(new Intent(AddNoteActivity.this, ViewAllNotesActivity.class));
        }
        else // wenn nicht gib eine Nachricht
        {
            Toast.makeText(this, getText(R.string.bitte_vollständige_dein_notizen), Toast.LENGTH_SHORT).show();
        }

        
    }
    
    public boolean checkEditText (EditText editText)
    {
        return  editText.getText().toString().trim().length()>0; 
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddNoteActivity.this, ViewAllNotesActivity.class));
    }
}