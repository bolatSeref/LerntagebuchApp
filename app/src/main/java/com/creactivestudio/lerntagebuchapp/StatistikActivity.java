package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StatistikActivity extends AppCompatActivity {

    ImageView imgStatistikBild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);

        initViews();
    }

    public void initViews()
    {
        imgStatistikBild=findViewById(R.id.imgStatistikBild);

    }

    public void switchWoechentlich (View view) {
        if (view.createAccessibilityNodeInfo().isChecked())
        {
            imgStatistikBild.setImageResource(R.drawable.statistik2);
        }
        else
        {
            imgStatistikBild.setImageResource(R.drawable.statistik);
        }
    }

}