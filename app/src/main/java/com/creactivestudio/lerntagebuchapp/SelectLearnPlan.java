package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SelectLearnPlan extends AppCompatActivity {

    Spinner spinnerThemes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_learn_plan);

        // Initialition von Views
        initViews();

        // Stelle data zu Spinner ein.
        setSpinner();
    }

    /**
     * Initialition von Views
     */
    public void initViews ()
    {
        spinnerThemes =findViewById(R.id.spinnerGewaelteThemen);
    }

    /**
     * Stelle data zu Spinner ein. Die Datas kommt von XML Datei.
     */
    public void setSpinner()
    {
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.mathe_themen, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerThemes.setAdapter(adapter);
    }

    /**
     * Nach dem auswählen das Thema, fängt der Benutzer zu lernen.
     * @param view
     */
    public void startLearning (View view)
    {
        Intent intent=new Intent(SelectLearnPlan.this, StatusActivity.class);
        startActivity(intent);
    }
}