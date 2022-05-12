package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StatisticsActivity extends AppCompatActivity {

    ImageView imgStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        initViews();
    }

    public void initViews()
    {
        imgStatistics =findViewById(R.id.imgStatistics);

    }

    public void switchWeeklyView (View view) {
        if (view.createAccessibilityNodeInfo().isChecked())
        {
            imgStatistics.setImageResource(R.drawable.statistik2);
        }
        else
        {
            imgStatistics.setImageResource(R.drawable.statistik);
        }
    }

}