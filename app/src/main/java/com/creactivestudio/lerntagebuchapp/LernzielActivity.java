package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LernzielActivity extends AppCompatActivity {

    Spinner spinnerThemen; // Spinner für Mathe Themen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lernziel);

        init();
        setDataForSpinner();
    }

    /**
     * Initialition von views
     */
    public void init ()
    {
        spinnerThemen=findViewById(R.id.spinnerThemen);

    }

    public void setDataForSpinner ()
    {
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.mathe_themen, R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerThemen.setAdapter(adapter);
    }

    public void btnSpeichernClicked (View view)
    {
        Intent intent = new Intent(LernzielActivity.this, MainActivity.class);
        startActivity(intent);
    }
}