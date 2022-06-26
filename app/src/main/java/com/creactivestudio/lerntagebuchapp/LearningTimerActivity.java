package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.creactivestudio.lerntagebuchapp.goals.DatabaseHelperLearningGoals;
import com.creactivestudio.lerntagebuchapp.note.ViewAllNotesActivity;

public class LearningTimerActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;
    Helper helper;
    private boolean timerPaused;
    ImageView imgDoNotDisturb, imgDoNotDisturbOff;
    long startTime=0;
    long millis;
    TextView tvTimer; // Timer ist angezeigt.
    ConstraintLayout rootLayout;
    Handler timerHandler=new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            long goalTime=getGoalTime();
           // goalTime=goalTime*1000*60;

            // Wenn der Benutzer sein Ziel erreicht hat dann informiere den Benutzer
            if (goalTime==(millis/1000)/60) helper.showToast(getString(R.string.du_hast_dein_ziel_erreicht), Helper.TOAST_MESSAGE_TYPE_SUCCESS);

                tvTimer.setText(String.format("%d:%02d", minutes, seconds));

                timerHandler.postDelayed(this, 500);



        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);


        init(); // Initialition
        
        timerStart(); // Starte den Timer
        getGoalTime();

        // Ändere ActionBar Name, Informiere den Benutzer über sein Ziel
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Du lernst: " + getGoalTheme() + " - Dein Ziel ist: " + getGoalTime() + " Minuten");

    }

    public void timerStart()
    {
        startTime=System.currentTimeMillis(); //  Beginnt mit System Time
        timerHandler.postDelayed(timerRunnable,0);
    }


    /**
     * Bekomme Goalzeit vom intent
     * @return
     */
    public int getGoalTime () 
    {
        Intent intent=getIntent();
        String goalTime=intent.getStringExtra("goalTime");
        return Integer.parseInt(goalTime);
    }

    /**
     * Bekomme Goaltheme vom intent
     * @return
     */
    public String getGoalTheme ()
    {
        Intent intent=getIntent();
        String goalTheme=intent.getStringExtra("goalTheme");
        return goalTheme;
    }

    /**
     * Benutzer kann den Timer stoppen.
     * @param view
     */
    public void stopTimer (View view)
    {
        startTime=System.currentTimeMillis();
    }
    

    /**
     * Benutzer startet den Timer.
     * @param view
     */
    public void startTimer (View view)
    {
        startTime=System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable,0);
    }

    public void resumeTimer (View view)
    {
        timerHandler.postDelayed(timerRunnable,0);
        timerPaused=false;
    }

    /**
     * Speiche die Zeit, die der Benutzer gelernt hat.
     * @param view
     */
    public void saveTime (View view)
    {
        Intent intent=getIntent();
        String goalId=intent.getStringExtra("goalId");

        int learnTime= (int) (millis/1000)/60; // Gelernte Zeit in Minuten
        if(learnTime<=0) // Der Benutzer hat noch nicht genügend gelernt.
        {
            helper.showToast(getString(R.string.du_musst_mindestens_1_min_lernen_um_zu_speichern), Helper.TOAST_MESSAGE_TYPE_ERROR);
        }
        else { // Der Benutzer hat genügend gelernt, speicher die Daten
            DatabaseHelperLearningGoals databaseHelperLearningGoals = new DatabaseHelperLearningGoals(this);
            databaseHelperLearningGoals.saveTimeToSql(goalId, learnTime);

            Cursor cursor = databaseHelperLearningGoals.readAllData();
            if (cursor.getCount() == 0) {
                helper.showToast(getString(R.string.no_data), Helper.TOAST_MESSAGE_TYPE_ERROR);
            } else {
                while (cursor.moveToNext()) {
                    Toast.makeText(this, cursor.getString(3), Toast.LENGTH_SHORT).show();
                    System.out.println(cursor.getString(3));
                    System.out.println(cursor.getString(2));
                    System.out.println(cursor.getString(1));
                    System.out.println(cursor.getString(0));
                }
                helper.showToast(getString(R.string.dein_lern_zeit_ist_gespeichert) + "\n\nDu hast " + learnTime + " Minuten gelernt.", Helper.TOAST_MESSAGE_TYPE_SUCCESS);
                stopTimer(view);
                timerHandler.removeCallbacks(timerRunnable);//stop timer

                Handler handler = new Handler(); // Warte 2 Sekunden dann geh weiter zu MainActivity
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        startActivity(new Intent(LearningTimerActivity.this, MainActivity.class));
                    }
                }, 2000);


            }
        }
    }


    /**
     * Benutzer kann der Timer pausieren:
     * @param view
     */
    public void pauseTimer(View view)
    {
        timerHandler.removeCallbacks(timerRunnable);
        timerPaused=true;
    }

    /**
     * Initialition von Views
     */
    public void init()
    {
        helper=new Helper(this);
        tvTimer=findViewById(R.id.tvTimer);
        rootLayout=findViewById(R.id.rootLayout);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        imgDoNotDisturb=findViewById(R.id.imgDoNotDisturbOff);
        imgDoNotDisturbOff=findViewById(R.id.imgDoNotDisturbOn);
    }

    protected void changeInterruptionFiler(int interruptionFilter) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // If api level minimum 23
            /*
                boolean isNotificationPolicyAccessGranted ()
                    Checks the ability to read/modify notification policy for the calling package.
                    Returns true if the calling package can read/modify notification policy.
                    Request policy access by sending the user to the activity that matches the
                    system intent action ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS.

                    Use ACTION_NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED to listen for
                    user grant or denial of this access.

                Returns
                    boolean

            */
            // If notification policy access granted for this package
            if (mNotificationManager.isNotificationPolicyAccessGranted()) {
                /*
                    void setInterruptionFilter (int interruptionFilter)
                        Sets the current notification interruption filter.

                        The interruption filter defines which notifications are allowed to interrupt
                        the user (e.g. via sound & vibration) and is applied globally.

                        Only available if policy access is granted to this package.

                    Parameters
                        interruptionFilter : int
                        Value is INTERRUPTION_FILTER_NONE, INTERRUPTION_FILTER_PRIORITY,
                        INTERRUPTION_FILTER_ALARMS, INTERRUPTION_FILTER_ALL
                        or INTERRUPTION_FILTER_UNKNOWN.
                */

                // Set the interruption filter
                mNotificationManager.setInterruptionFilter(interruptionFilter);
            } else {
                /*
                    String ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS
                        Activity Action : Show Do Not Disturb access settings.
                        Users can grant and deny access to Do Not Disturb configuration from here.

                    Input : Nothing.
                    Output : Nothing.
                    Constant Value : "android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"
                */
                // If notification policy access not granted for this package
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivity(intent);
            }
        }
    }

    /**
     * Der benutzer kann Nicht Stören Modus aktivieren
     * @param view
     */
    public void doNotDisturbOn(View view)
    {
        changeInterruptionFiler(NotificationManager.INTERRUPTION_FILTER_NONE);
        rootLayout.setBackgroundColor(Color.BLACK);
        helper.showToast(getString(R.string.nicht_stoeren_modus_an),Helper.TOAST_MESSAGE_TYPE_SUCCESS);

    }

    /**
     * Der benutzer kann Nicht Stören Modus deaktivieren
     * @param view
     */
    public void doNotDisturbOff (View view)
    {
        changeInterruptionFiler(NotificationManager.INTERRUPTION_FILTER_ALL);
        rootLayout.setBackgroundColor(Color.WHITE);
        tvTimer.setTextColor(Color.BLACK);
        helper.showToast(getString(R.string.nicht_stoeren_modus_aus), Helper.TOAST_MESSAGE_TYPE_ERROR);
    }


    /**
     * Der Benutzer navigiert sich zu View All Notes Activity, da kann er besthende Notizen verwalten und neue Notizen verfassen.
     * @param view
     */

    public void goToNotes (View view)
    {
        Intent intent =new Intent(LearningTimerActivity.this, ViewAllNotesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

}