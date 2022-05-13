package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.creactivestudio.lerntagebuchapp.note.ViewAllNotesActivity;

public class StatusActivity extends AppCompatActivity {


    long startTime=0;
    TextView tvTimer; // Timer ist angezeigt.

    Handler timerHandler=new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            tvTimer.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);


        initViews(); // Initialition von View-Objekts

        startTime=System.currentTimeMillis(); //  Beginnt mit System Time
        timerHandler.postDelayed(timerRunnable,0);


        Intent intent=getIntent();
        String id=intent.getStringExtra("goalId");
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

    }

    /**
     * Benutzer kann den Timer stoppen.
     * @param view
     */
    public void stopTimer (View view)
    {
        timerHandler.removeCallbacks(timerRunnable);
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

    /**
     * Benutzer kann der Timer pausieren:
     * @param view
     */
    public void pauseTimer(View view)
    {

    }

    /**
     * Initialition von Views
     */
    public void initViews()
    {
        tvTimer=findViewById(R.id.tvTimer);
    }


    /**
     * Der benutzer kann Nicht Stören Modus aktivieren und deaktivieren.
     * @param view
     */
    public void doNotDisturb(View view)
    {
// TODO: 12.05.22
    }

    /**
     * Der Benutzer navigiert sich zu View All Notes Activity, da kann er besthende Notizen verwalten und neue Notizen verfassen.
     * @param view
     */

    public void addNote (View view)
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