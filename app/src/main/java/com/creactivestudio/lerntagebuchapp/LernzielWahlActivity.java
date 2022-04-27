package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LernzielWahlActivity extends AppCompatActivity {

    Spinner spinnerGewaelteThemen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lernziel_wahl);

        initViews();

        setSpinner();
    }

    public void initViews ()
    {
        spinnerGewaelteThemen=findViewById(R.id.spinnerGewaelteThemen);
    }

    public void setSpinner()
    {
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.mathe_themen, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerGewaelteThemen.setAdapter(adapter);
    }

    public void btnStartClicked (View view)
    {
        Intent intent=new Intent(LernzielWahlActivity.this, StatusActivity.class);
        startActivity(intent);
    }
}