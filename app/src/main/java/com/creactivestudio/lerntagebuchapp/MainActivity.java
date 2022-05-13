package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.creactivestudio.lerntagebuchapp.goals.DatabaseHelperLearningGoals;
import com.creactivestudio.lerntagebuchapp.note.ViewAllNotesActivity;

/**
 *  Ab dem zweiten öffnung der App, sieht der Benutzer diese Activity. Da kann er sich
 *  zu andere Activities navigieren und die Lernfortschritte sehen.
 */
public class MainActivity extends AppCompatActivity {

    TextView tvWeeklyGoalTime, tvLearnStatus;
    ProgressBar progressBarWeeklyGoals;
    int totalGoalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        initViews(); // initializiere Views
        setTvWeeklyGoalTime();
        setTvLearnStatus();
        // TODO: 13.05.22 progress wert wird vom Datenbank abgeholt
        setProgressBarWeeklyGoals(totalGoalTime, totalGoalTime/3);
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
     * initializiere Views
     */
    public void initViews()
    {
        tvWeeklyGoalTime=findViewById(R.id.tvWeeklyGoalTime);
        progressBarWeeklyGoals=findViewById(R.id.progressBarWeeklyGoal);
        tvLearnStatus=findViewById(R.id.tvLearnStatus);
    }

    public void setProgressBarWeeklyGoals (int maxValue, int progressValue)
    {
        progressBarWeeklyGoals.setMax(maxValue);
        progressBarWeeklyGoals.setProgress(progressValue);
        progressBarWeeklyGoals.setScaleY(5f);
    }

    /**
     *
     */
    public void setTvWeeklyGoalTime ()
    {
        DatabaseHelperLearningGoals databaseHelperLearningGoals=new DatabaseHelperLearningGoals(this);
        totalGoalTime=databaseHelperLearningGoals.getLearningGoalTime();
        tvWeeklyGoalTime.setText("Dein Ziel ist " + totalGoalTime + " Minuten");
    }

    public void setTvLearnStatus ()
    {
        tvLearnStatus.setText("Du hast " + "20" + " Minuten gelernt.");
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