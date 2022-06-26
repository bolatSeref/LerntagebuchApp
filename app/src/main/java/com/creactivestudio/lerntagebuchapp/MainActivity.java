package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.creactivestudio.lerntagebuchapp.evaluate_your_self.EvaluateYourSelfActivity;
import com.creactivestudio.lerntagebuchapp.goals.CreateStudyPlan;
import com.creactivestudio.lerntagebuchapp.goals.DatabaseHelperLearningGoals;
import com.creactivestudio.lerntagebuchapp.goals.SelectThemeToLearn;
import com.creactivestudio.lerntagebuchapp.note.ViewAllNotesActivity;

/**
 *  Ab dem zweiten öffnung der App, sieht der Benutzer diese Activity. Da kann er sich
 *  zu andere Activities navigieren und die Lernfortschritte sehen.
 */
public class MainActivity extends AppCompatActivity {

    TextView tvWeeklyGoalTime, tvLearnStatus, tvCongratulation;
    ProgressBar progressBarWeeklyGoals;
    int totalGoalTime, learnTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        init(); // initialisierung
        setTextViews();

        setProgressBarWeeklyGoals(totalGoalTime, learnTime);
    }

    /**
     * Wenn der Benutzer diese Button Klicked, dann landet er zu Lern Ziel Wahl Activity,
     * wo er der Ziel wählen kann.
     * @param view
     */
    public void startLearning(View view)
    {
        Intent intent=new Intent(MainActivity.this, SelectThemeToLearn.class);
        startActivity(intent);
    }

    /**
     * Der Benutzer kann sich CreateStudyPlan Activity navigieren, um sein Lernplan ändern zu können.
     * @param view
     */
    public void settings (View view)
    {
        startActivity(new Intent(MainActivity.this, CreateStudyPlan.class));
    }

    /**
     * initializiere Views
     */
    public void init()
    {
        tvWeeklyGoalTime=findViewById(R.id.tvWeeklyGoalTime);
        progressBarWeeklyGoals=findViewById(R.id.progressBarWeeklyGoal);
        tvLearnStatus=findViewById(R.id.tvLearnStatus);
        tvCongratulation=findViewById(R.id.tvCongratulation);
    }

    /**
     * Setze Werte für ProgressBar
     * @param maxValue Ziel der Benutzer
     * @param progressValue  Die Zeit, die der Benutzer schon gelernt hat
     */
    public void setProgressBarWeeklyGoals (int maxValue, int progressValue)
    {
        progressBarWeeklyGoals.setMax(maxValue);
        progressBarWeeklyGoals.setProgress(progressValue);
        progressBarWeeklyGoals.setScaleY(5f);
    }

    /**
     *  Informiere den Benutzer über seine Fortschritte
     */
    public void setTextViews()
    {
        DatabaseHelperLearningGoals databaseHelperLearningGoals=new DatabaseHelperLearningGoals(this);
        totalGoalTime=databaseHelperLearningGoals.getLearningGoalTime();
        tvWeeklyGoalTime.setText("Dein Ziel ist " + totalGoalTime + " Minuten");
        learnTime=databaseHelperLearningGoals.getLearnTime();

        tvLearnStatus.setText("Du hast " + learnTime + " Minuten gelernt.");

        if(learnTime>totalGoalTime) // Wenn der Benutzer sein Ziel erreicht hat, gratuliere den Benutzer.
        {
            tvCongratulation.setText(getString(R.string.du_hast_dein_ziel_erreicht));
        }

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
     * Der Benutzer navigiert sich zu Evaluate Your Self Activity, da kann er sich bewerten.
     * @param view
     */
    public void evaluateYourSelf(View view)
    {
        startActivity(new Intent(MainActivity.this, EvaluateYourSelfActivity.class));
    }
}