package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.creactivestudio.lerntagebuchapp.note.ViewAllNotesActivity;

public class StatusActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;

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
            if (goalTime==(millis/1000)/60) Toast.makeText(StatusActivity.this, getString(R.string.du_hast_dein_ziel_erreicht), Toast.LENGTH_SHORT).show();
           // if (goalTime==millis) Toast.makeText(StatusActivity.this, getString(R.string.du_hast_dein_ziel_erreicht), Toast.LENGTH_SHORT).show();
// TODO: 26.05.22 musik wenn der Benutzer sein Ziel erreicht hat

                tvTimer.setText(String.format("%d:%02d", minutes, seconds));

                timerHandler.postDelayed(this, 500);



        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);


        initViews(); // Initialition von View-Objekts
        
        timerStart();
        getGoalTime();
       // imgDoNotDisturbOff.setVisibility(View.INVISIBLE);
        

    }

    public void stopTheTimer () {

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
    //    Toast.makeText(this, goalTime, Toast.LENGTH_SHORT).show();
        return Integer.parseInt(goalTime);
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
       // startTime=startTime+(System.currentTimeMillis() - startTime);
        timerHandler.postDelayed(timerRunnable,0);

        timerPaused=false;

    }

    /**
     * Benutzer kann der Timer pausieren:
     * @param view
     */
    public void pauseTimer(View view)
    {
        timerHandler.removeCallbacks(timerRunnable);

        timerPaused=true;
       // timerHandler.removeCallbacks(timerRunnable);

    }

    /**
     * Initialition von Views
     */
    public void initViews()
    {
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
     * Der benutzer kann Nicht St??ren Modus aktivieren
     * @param view
     */
    public void doNotDisturbOn(View view)
    {
        changeInterruptionFiler(NotificationManager.INTERRUPTION_FILTER_NONE);
        rootLayout.setBackgroundColor(Color.BLACK);
        Toast.makeText(this, getString(R.string.nicht_stoeren_modus_an), Toast.LENGTH_SHORT).show();
        tvTimer.setTextColor(Color.WHITE);
       // imgDoNotDisturb.setImageResource(R.drawable.icon_do_not_disturb_on);
      //  imgDoNotDisturbOff.setVisibility(View.INVISIBLE);

        // turn off dnd mode

        //NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
// TODO: 12.05.22


        /*
        int currentTimeInMinutes=(int) (System.currentTimeMillis()/(1000*60))%60;
        int startTimeInMinutes=(int) (startTime/(1000*60))%60;
        
        if(currentTimeInMinutes-startTimeInMinutes>getGoalTime())
        {
            Toast.makeText(this, "Du hast dein Ziel erreicht", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Du hast dein Ziel noch nicht erreicht", Toast.LENGTH_SHORT).show();
        */
    }

    /**
     * Der benutzer kann Nicht St??ren Modus deaktivieren
     * @param view
     */
    public void doNotDisturbOff (View view)
    {
        changeInterruptionFiler(NotificationManager.INTERRUPTION_FILTER_ALL);
        rootLayout.setBackgroundColor(Color.WHITE);
        tvTimer.setTextColor(Color.BLACK);
        Toast.makeText(this, getString(R.string.nicht_stoeren_modus_aus), Toast.LENGTH_SHORT).show();
       // imgDoNotDisturb.setVisibility(View.INVISIBLE);
    }


    /**
     * Der Benutzer navigiert sich zu View All Notes Activity, da kann er besthende Notizen verwalten und neue Notizen verfassen.
     * @param view
     */

    public void goToNotes (View view)
    {
        Intent intent =new Intent(StatusActivity.this, ViewAllNotesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

}