package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.creactivestudio.lerntagebuchapp.note.ViewAllNotesActivity;

/**
 *  Ab dem zweiten öffnung der App, sieht der Benutzer diese Activity. Da kann er sich
 *  zu andere Activities navigieren und die Lernfortschritte sehen.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    /**
     * Wenn der Benutzer diese Button Klicked, dann landet er zu Lern Ziel Wahl Activity,
     * wo er der Ziel wählen kann.
     * @param view
     */
    public void startLearning(View view)
    {
        Intent intent=new Intent(MainActivity.this, SelectLearnPlan.class);
        startActivity(intent);
    }


    /**
     * Der Benutzer navigiert sich zu View All Notes Activity, da kann er besthende Notizen verwalten und neue Notizen verfassen.
     * @param view
     */
    public void goToNotes (View view)
    {
        Intent intent = new Intent(MainActivity.this, ViewAllNotesActivity.class);
        startActivity(intent);
    }


    /**
     * Der Benutzer navigiert sich zu Statistics Activity, da kann er alle Statistiken sehen und sich einschätzen.
     * @param view
     */
    public void myStatistics (View view)
    {
        Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
        startActivity(intent);
    }

    /**
     * Der Benutzer navigiert sich zu Evaluate Your Self Activity, da kann er sich bewerten.
     * @param view
     */
    public void evaluateYourSelf(View view)
    {
        startActivity(new Intent(MainActivity.this, EvaluateYourSelfActivity.class));
    }
}