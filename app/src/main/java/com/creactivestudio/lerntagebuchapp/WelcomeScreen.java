package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Diese Activity ist die erste Activity, die der Benutzer sieht.
 */
public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }


    /**
     * Der Benutzer kann sich zu Create Study Plan Activity navigieren, wo er sein Lern Plan erstellen bzw. verwalten kann.
     * @param view
     */
    public void createStudyPlan (View view)
    {
        Intent intent = new Intent(WelcomeScreen.this, CreateStudyPlan.class);
        startActivity(intent);
    }
}