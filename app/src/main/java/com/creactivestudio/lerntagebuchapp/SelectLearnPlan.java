package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.creactivestudio.lerntagebuchapp.goals.DatabaseHelperLearningGoals;

import java.util.ArrayList;

public class SelectLearnPlan extends AppCompatActivity {

    AllThemesRecyclerViewAdapter allThemesRecyclerViewAdapter;
    RecyclerView rvAllThemes;
    DatabaseHelperLearningGoals databaseHelperLearningGoals;
    ArrayList<String> goalIdList, goalThemeList, goalTimeList; // Die Werte bekommen wir von SQLite Datenbank dann ordnen wir zu dieser Array Listen ein.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_learn_plan);


        initViews();// Initialition von Views

        storeDataInArrays(); // Wir speichern alle Daten von Sqlite zu oben besreibene Array Lists ein.
        rvAllThemes.setAdapter(allThemesRecyclerViewAdapter);
        rvAllThemes.setLayoutManager(new GridLayoutManager(SelectLearnPlan.this, 2));

    }

    /**
     * Initialition von Views
     */
    public void initViews ()
    {
        databaseHelperLearningGoals=new DatabaseHelperLearningGoals(SelectLearnPlan.this);
        rvAllThemes=findViewById(R.id.rvAllThemes);

        goalIdList =new ArrayList<>();
        goalThemeList =new ArrayList<>();
        goalTimeList =new ArrayList<>();

        allThemesRecyclerViewAdapter=new AllThemesRecyclerViewAdapter(this, this, goalIdList, goalThemeList, goalTimeList);


    }


    /**
     * Wir speichern alle Daten von Sqlite zu oben beschriebene Array Lists ein.
     */
    public void storeDataInArrays ()
    {
        Cursor cursor= databaseHelperLearningGoals.readAllData(); // Cursor geht alle Columns schritt für schritt vor.
        if(cursor.getCount()==0) // Wenn kein Data gibt dann informiere den Benutzer.
        {
            Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext()) // Geh weiter bis data gibt
            {
                goalIdList.add(cursor.getString(0));
                goalThemeList.add(cursor.getString(1));
                goalTimeList.add(cursor.getString(2));

                //goalsRecyclerViewAdapter.notifyDataSetChanged();

            }
        }
    }

    /**
     * Nach dem auswählen das Thema, fängt der Benutzer zu lernen.
     * @param view
     */
    public void startLearning (View view)
    {
        Intent intent=new Intent(SelectLearnPlan.this, StatusActivity.class);
        startActivity(intent);
    }
}