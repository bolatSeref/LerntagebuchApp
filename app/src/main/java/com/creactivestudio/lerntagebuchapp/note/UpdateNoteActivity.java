package com.creactivestudio.lerntagebuchapp.note;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.creactivestudio.lerntagebuchapp.R;
import com.creactivestudio.lerntagebuchapp.sqlite.DatabaseHelper;

public class UpdateNoteActivity extends AppCompatActivity {

    EditText etNoteTitleUpdate, etNoteTextUpdate;
    Button btnUpdate;
    String noteId, noteTitle, noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);



        initViews();



        getAndSetIntentData();

        // to change Actionbar name
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Update: " + noteTitle);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper=new DatabaseHelper(UpdateNoteActivity.this);
                databaseHelper.updateData(noteId, noteTitle,noteText);
            }
        });


    }

    public void initViews()
    {
        etNoteTextUpdate=findViewById(R.id.etNoteTextUpdate);
        etNoteTitleUpdate=findViewById(R.id.etNoteTitleUpdate);
        btnUpdate=findViewById(R.id.btnUpdateNote);
    }

    public void getAndSetIntentData() {
        if(getIntent().hasExtra("noteId") && getIntent().hasExtra("noteTitle") && getIntent().hasExtra("noteText"))
        {
            noteId=getIntent().getStringExtra("noteId");
            noteTitle=getIntent().getStringExtra("noteTitle");
            noteText=getIntent().getStringExtra("noteText");

            etNoteTitleUpdate.setText(noteTitle);
            etNoteTextUpdate.setText(noteText);
        }
        else
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmDialog ()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

    }
}