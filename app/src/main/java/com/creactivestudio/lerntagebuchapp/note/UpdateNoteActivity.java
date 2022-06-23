package com.creactivestudio.lerntagebuchapp.note;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.creactivestudio.lerntagebuchapp.Helper;
import com.creactivestudio.lerntagebuchapp.R;

public class UpdateNoteActivity extends AppCompatActivity {

    private EditText etNoteTitleUpdate, etNoteTextUpdate;
    private String noteId, noteTitle, noteText;
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

    /**
     * Note id bekommen wir durch Intent dann senden wir neue Eingaben zu Datenbank
     * @param view
     */
    public void updateNote (View view)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(UpdateNoteActivity.this);
        noteTitle=etNoteTitleUpdate.getText().toString();
        noteText=etNoteTextUpdate.getText().toString();
        databaseHelper.updateData(noteId, noteTitle,noteText);
    }

    /**
     * Initialiesiere Views usw.
     */
    public void init()
    {
        etNoteTextUpdate=findViewById(R.id.etNoteTextUpdate);
        etNoteTitleUpdate=findViewById(R.id.etNoteTitleUpdate);
        helper=new Helper(this);
     }

    /**
     * Nim die daten durch Intent, um die richtige Notizen aktualiesieren zu können.
     */
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


}