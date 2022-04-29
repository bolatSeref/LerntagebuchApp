package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void btnStartClicked (View view)
    {
        Intent intent=new Intent(MainActivity.this, LernzielWahlActivity.class);
        startActivity(intent);
    }


    /**
     * Zur Notizen Activity
     * @param view
     */
    public void notizenVerfassen (View view)
    {
        Intent intent = new Intent(MainActivity.this, NotizenActivity.class);
        startActivity(intent);
    }


    public void imgStatistikClick (View view)
    {
        Intent intent = new Intent(MainActivity.this, StatistikActivity.class);
        startActivity(intent);
    }

    public void bewertung (View view)
    {
        startActivity(new Intent(MainActivity.this,BewertungActivity.class));
    }
}