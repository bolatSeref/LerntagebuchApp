package com.creactivestudio.lerntagebuchapp.evaluate_your_self;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.creactivestudio.lerntagebuchapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * In diese Activity kann der Benutzer sich einschätzen.
 */
public class EvaluateYourSelfActivity extends AppCompatActivity {

    private EvaluateYourSelfRvAdapter evaluateYourSelfRvAdapter;
    private RecyclerView rvEvaluation;
    private List<String> themenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_your_self);

        init(); // Initialisierung

        setThemesArrayFromXML(); // Hole Mathe Themen aus XML Datei ein

        evaluateYourSelfRvAdapter=new EvaluateYourSelfRvAdapter(this, this
        , themenList); // Recycler View Adapter ist initialisiert
        rvEvaluation.setAdapter(evaluateYourSelfRvAdapter);
        rvEvaluation.setLayoutManager(new LinearLayoutManager(this));


        // Ändere ActionBar Name
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Selbsteinschätzung");

    }

    /**
     * Initialisiere Views usw.
     */
    public void init() {
        rvEvaluation = findViewById(R.id.rvEvaluation); // Recyclerview
        themenList =new ArrayList<>();
    }

    /**
     *  Nim die Mathe 2 Themen vom XML und set die Daten als ein List
     */
    public void setThemesArrayFromXML()
    {
       themenList = Arrays.asList(getResources().getStringArray(R.array.mathe_themen_without_title));
    }
}
