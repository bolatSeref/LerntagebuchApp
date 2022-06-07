package com.creactivestudio.lerntagebuchapp.evaluate_your_self;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.creactivestudio.lerntagebuchapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * In diese Activity kann der Benutzer sich einschätzen.
 */
public class EvaluateYourSelfActivity extends AppCompatActivity {

    EvaluateYourSelfRvAdapter evaluateYourSelfRvAdapter;
    private ArrayList themeList;
    private RecyclerView rvEvaluation;
    private List<String> testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_your_self);

        init(); // Initialisierung

        setThemesArrayFromXML(); // Hole Mathe Themen aus XML Datei ein

        evaluateYourSelfRvAdapter=new EvaluateYourSelfRvAdapter(this, this
        , testList); // Recycler View Adapter ist initialisiert
        rvEvaluation.setAdapter(evaluateYourSelfRvAdapter);
        rvEvaluation.setLayoutManager(new LinearLayoutManager(this));


    }

    /**
     * Initialisiere Views usw.
     */
    public void init() {
        rvEvaluation = findViewById(R.id.rvEvaluation);
        themeList = new ArrayList();
        testList=new ArrayList<>();
    }

    /**
     *
     */
    public void setThemesArrayFromXML()
    {
       testList= Arrays.asList(getResources().getStringArray(R.array.mathe_themen_without_title));
    }
}
