package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.creactivestudio.lerntagebuchapp.goals.DatabaseHelperLearningGoals;
import com.creactivestudio.lerntagebuchapp.goals.GoalsRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Hier erstellt der Benutzer sein Lernziele bzw. die Themen die er lernen will, und der Zeit dass er so lange
 * arbeiten will.
 */
public class CreateStudyPlan extends AppCompatActivity {

    GoalsRecyclerViewAdapter goalsRecyclerViewAdapter; // Adapter benötigt für Recycler View.
    RecyclerView rvGoals; // Da sieht der Benutzer bereitgestelltte Lern Ziele
    DatabaseHelperLearningGoals databaseHelperLearningGoals;
    EditText etGoalTime; // Gib der Benutzer gewünschte Lern Ziel Zeit ein.
    Spinner spinnerThemen; // Spinner für Mathe2 Themen
    ArrayList<String> goalIdList, goalThemeList, goalTimeList; // Die Werte bekommen wir von SQLite Datenbank dann ordnen wir zu dieser Array Listen ein.
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_study_plan);

        initViews(); // Initialition von View-Objekts
        setDataForSpinner(); // Wir weisen die Daten zu Spinner vom XML Datei. 

        storeDataInArrays(); // Wir speichern alle Daten von Sqlite zu oben besreibene Array Lists ein. 

        
        // Recycler View einstellungen
        goalsRecyclerViewAdapter=new GoalsRecyclerViewAdapter(CreateStudyPlan.this, this, goalIdList, goalThemeList, goalTimeList);
        rvGoals.setAdapter(goalsRecyclerViewAdapter);
       // rvGoals.setLayoutManager(new LinearLayoutManager(CreateStudyPlan.this));
        rvGoals.setLayoutManager(new GridLayoutManager( CreateStudyPlan.this, 2));
        // TODO: 12.05.22 grid layout test 
    }

    /**
     * Initialition von views
     */
    public void initViews()
    {
        spinnerThemen=findViewById(R.id.spinnerThemen);
        etGoalTime =findViewById(R.id.etZielZeit);
        databaseHelperLearningGoals =new DatabaseHelperLearningGoals(CreateStudyPlan.this);

        rvGoals =findViewById(R.id.rvZiele);
        goalIdList =new ArrayList<>();
        goalThemeList =new ArrayList<>();
        goalTimeList =new ArrayList<>();

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
            }
        }
    }

    /**
     * Wenn der Benutzer mit der eingaben fertig ist dann speichert er sein Ziel.
     * @param view
     */
    public void addGoal(View view)
    {
        // TODO: 12.05.22 kontrolliere ob der benutzer edit text timer etwas gegeben hat.
        // TODO: 12.05.22 finde eine bessere Lösung ohne zu recreate der Activity

        DatabaseHelperLearningGoals db=new DatabaseHelperLearningGoals(CreateStudyPlan.this);
        db.addNote("grundlagen", etGoalTime.getText().toString().trim());
       // goalsRecyclerViewAdapter.notifyItemInserted(goalIdList.size());
      //  goalsRecyclerViewAdapter.notifyDataSetChanged();
        recreate();
    }

    /**
     * Alle Daten kommen von XML Datei.
     */
    public void setDataForSpinner ()
    {
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.mathe_themen, R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerThemen.setAdapter(adapter);
    }

    /**
     * Wenn der Benutzer alle Lernziele eingegeben ist dann speichert er sein Wöchentliche Lernziel.
     * @param view
     */
    public void saveGoal (View view)
    {
        Intent intent = new Intent(CreateStudyPlan.this, MainActivity.class);
        startActivity(intent);
    }
}