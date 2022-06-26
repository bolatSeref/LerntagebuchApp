package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.creactivestudio.lerntagebuchapp.goals.CreateStudyPlan;
import com.creactivestudio.lerntagebuchapp.goals.DatabaseHelperLearningGoals;

/**
 * Diese Activity ist die erste Activity, die der Benutzer sieht.
 */
public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        getSupportActionBar().hide(); // Um die gesamte Bildschirm benutzen zu können, schließen wir ActionBar.

        // Wenn der Benutzer sein Ziel gespeichert hat dann beginnt der App vom Main Activity,
        // wenn nicht dann erste Activity der App ist WelcomeScreen activity
        DatabaseHelperLearningGoals databaseHelperLearningGoals=new DatabaseHelperLearningGoals(this);
        if(databaseHelperLearningGoals.isThemenSaved())
        {
            startActivity(new Intent(WelcomeScreen.this, MainActivity.class));
        }

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