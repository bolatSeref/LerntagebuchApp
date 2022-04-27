package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        initViews();
    }

    public void initViews()
    {

    }

    public void imgDoNotDisturbClicked (View view)
    {

    }

    public void notizenVerfassen (View view)
    {
        Intent intent =new Intent(StatusActivity.this, NotizenActivity.class);
        startActivity(intent);
    }
}