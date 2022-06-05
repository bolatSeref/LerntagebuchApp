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
import android.widget.TextView;
import android.widget.Toast;

import com.creactivestudio.Helper;
import com.creactivestudio.lerntagebuchapp.goals.DatabaseHelperLearningGoals;
import com.creactivestudio.lerntagebuchapp.goals.GoalsRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Hier erstellt der Benutzer sein Lernziele bzw. die Themen die er lernen will, und der Zeit dass er so lange
 * arbeiten will.
 */
public class CreateStudyPlan extends AppCompatActivity {
    Helper helper;
    GoalsRecyclerViewAdapter goalsRecyclerViewAdapter; // Adapter benötigt für Recycler View.
    RecyclerView rvGoals; // Da sieht der Benutzer bereitgestelltte Lern Ziele
    DatabaseHelperLearningGoals databaseHelperLearningGoals;
    EditText etGoalTime; // Gib der Benutzer gewünschte Lern Ziel Zeit ein.
    Spinner spinnerThemen; // Spinner für Mathe2 Themen
    ArrayList<String> goalIdList, goalThemeList, goalTimeList; // Die Werte bekommen wir von SQLite Datenbank dann ordnen wir zu dieser Array Listen ein.
    TextView tvTotalGoalTime; // Wie viel Minuten t

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_study_plan);

        init(); // Initialition von View-Objekts
        setDataForSpinner(); // Wir weisen die Daten zu Spinner vom XML Datei. 

        storeDataInArrays(); // Wir speichern alle Daten von Sqlite zu oben besreibene Array Lists ein. 


        // Recycler View einstellungen
        rvGoals.setAdapter(goalsRecyclerViewAdapter);
       // rvGoals.setLayoutManager(new LinearLayoutManager(CreateStudyPlan.this));
        rvGoals.setLayoutManager(new GridLayoutManager( CreateStudyPlan.this, 2));
        // TODO: 12.05.22 grid layout test

        setTotalGoalTime();

    }

    /**
     * Initialition von views
     */
    public void init()
    {
        spinnerThemen=findViewById(R.id.spinnerThemen);
        etGoalTime =findViewById(R.id.etZielZeit);
        databaseHelperLearningGoals =new DatabaseHelperLearningGoals(CreateStudyPlan.this);
        tvTotalGoalTime=findViewById(R.id.tvTotalGoalTime);
        rvGoals =findViewById(R.id.rvZiele);
        goalIdList =new ArrayList<>();
        goalThemeList =new ArrayList<>();
        goalTimeList =new ArrayList<>();
        helper=new Helper(this);
        goalsRecyclerViewAdapter=new GoalsRecyclerViewAdapter(CreateStudyPlan.this, this, goalIdList, goalThemeList, goalTimeList);


    }

    /**
     * Der Benutzer sieht wie lang sein Lern Ziel ist. Gesamte Lern Ziel Zeit in Minuten.
     */
    public void setTotalGoalTime()
    {
        DatabaseHelperLearningGoals databaseHelperLearningGoals=new DatabaseHelperLearningGoals(this);
        int totalTime=databaseHelperLearningGoals.getLearningGoalTime();
        tvTotalGoalTime.setText("Gesamte Zeit:  " + totalTime +" Minuten");
    }


    /**
     * Wir speichern alle Daten von Sqlite zu oben beschriebene Array Lists ein.
     */
    public void storeDataInArrays ()
    {
        Cursor cursor= databaseHelperLearningGoals.readAllData(); // Cursor geht alle Columns schritt für schritt vor.
        if(cursor.getCount()==0) // Wenn kein Data gibt dann informiere den Benutzer.
        {
            helper.showToast(getString(R.string.du_hast_noch_nicht_dein_lernplan_erstellt), Helper.TOAST_MESSAGE_TYPE_ERROR);
           // Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_SHORT).show();
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
     * Wenn der Benutzer mit der eingaben fertig ist dann speichert er sein Ziel.
     * @param view
     */
    public void addGoal(View view)
    {
        // TODO: 12.05.22 kontrolliere ob der benutzer edit text timer etwas gegeben hat.
        // TODO: 12.05.22 finde eine bessere Lösung ohne zu recreate der Activity

        if (spinnerThemen.getSelectedItemPosition()==0) // Kontrolliere zunächst ob ein Thema ausgewählt ist, wenn nicht informiere den Benutzer
        {
            helper.showToast(getString(R.string.bitte_waehle_ein_thema), Helper.TOAST_MESSAGE_TYPE_ERROR);
        }
        else  // Wenn der Benutzer hat ein Thema ausgewählt dann speicher die Daten
        {
            if(etGoalTime.getText().toString().equals("")) // Kontrolliere ob der Benutzer Zeit Eingabe ausgefüllt hat.
            {   // Wenn nicht gib eine Meldung
                helper.showToast(getString(R.string.fuelle_die_zeit_eingabe_ein), Helper.TOAST_MESSAGE_TYPE_ERROR);
            }
            else {  // Wenn alle eingaben richtig ausgefüllt ist dann speiche die Daten zu SQLite Datenbank.
                String selectedTheme = spinnerThemen.getSelectedItem().toString();
                DatabaseHelperLearningGoals db = new DatabaseHelperLearningGoals(CreateStudyPlan.this);
                db.addNote(selectedTheme, etGoalTime.getText().toString().trim());
                // goalsRecyclerViewAdapter.notifyItemInserted(goalIdList.size());
                // goalsRecyclerViewAdapter.notifyDataSetChanged();
                etGoalTime.setText("");// Leere Edit Text nach dem einfügen
                recreate();
            }
        }
    }

    /**
     * Alle Daten kommen von XML Datei.
     * Stil einstellungen für Spinner.
     */
    public void setDataForSpinner ()
    {
       // ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.mathe_themen, R.layout.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.mathe_themen, R.layout.spinner);
        //adapter.setDropDownViewResource(R.layout.spinner);
        spinnerThemen.setAdapter(adapter);
        spinnerThemen.setPopupBackgroundResource(android.R.color.holo_blue_light);// Ändere Spinner Background Farbe
    }


    /**
     * Wenn der Benutzer alle Lernziele eingegeben ist dann speichert er sein Wöchentliche Lernziel.
     * @param view
     */
    public void saveGoal (View view)
    {
        DatabaseHelperLearningGoals databaseHelperLearningGoals=new DatabaseHelperLearningGoals(this);// Instanzierung DatabaseHelper-Klasse
        if(databaseHelperLearningGoals.isThemenSaved()) // kontrolliere ob der Benutzer thema hinzugefügt hat wenn ja dann intent ist möglich
        {
            Intent intent = new Intent(CreateStudyPlan.this, MainActivity.class);
            startActivity(intent);
        }
        else // wenn der Benutzer kein Thema hinzugefügt hat dann gib eine Nachricht
        {
            helper.showToast(getString(R.string.please_save_theme), Helper.TOAST_MESSAGE_TYPE_ERROR);
        }

    }
}