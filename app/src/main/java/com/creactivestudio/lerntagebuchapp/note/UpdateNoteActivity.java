package com.creactivestudio.lerntagebuchapp.note;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.creactivestudio.Helper;
import com.creactivestudio.lerntagebuchapp.R;
import com.creactivestudio.lerntagebuchapp.sqlite.DatabaseHelper;

public class UpdateNoteActivity extends AppCompatActivity {

    EditText etNoteTitleUpdate, etNoteTextUpdate;
     String noteId, noteTitle, noteText;
    Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        init();
        getAndSetIntentData();

        // to change Actionbar name
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Update: " + noteTitle);

    }

    public void updateNote (View view)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(UpdateNoteActivity.this);
        databaseHelper.updateData(noteId, noteTitle,noteText);
    }

    public void init()
    {
        etNoteTextUpdate=findViewById(R.id.etNoteTextUpdate);
        etNoteTitleUpdate=findViewById(R.id.etNoteTitleUpdate);
        helper=new Helper(this);
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
            helper.showToast(getString(R.string.no_data),Helper.TOAST_MESSAGE_TYPE_ERROR);
        }
    }

    public void confirmDialog ()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

    }
}