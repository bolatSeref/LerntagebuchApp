package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {

    long startTime=0;
    TextView tvTimer;

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

        initViews();

        startTime=System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable,0);

    }

    public void stopTimer (View view)
    {
        timerHandler.removeCallbacks(timerRunnable);
    }

    public void startTimer (View view)
    {
        startTime=System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable,0);
    }

    public void pausiereTimer (View view)
    {

    }

    public void initViews()
    {
        tvTimer=findViewById(R.id.tvTimer);
    }

    public void imgDoNotDisturbClicked (View view)
    {

    }

    public void notizenVerfassen (View view)
    {
        Intent intent =new Intent(StatusActivity.this, NotizenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

}