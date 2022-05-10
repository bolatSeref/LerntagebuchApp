package com.creactivestudio.lerntagebuchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.creactivestudio.lerntagebuchapp.lernziel.DatabaseHelperLernZiel;
import com.creactivestudio.lerntagebuchapp.lernziel.ZielRvAdapter;

import java.util.ArrayList;

public class LernzielActivity extends AppCompatActivity {

    ZielRvAdapter zielRvAdapter;
    RecyclerView rvZiele;
    DatabaseHelperLernZiel databaseHelperLernZiel;
    EditText etZielZeit;
    Spinner spinnerThemen; // Spinner für Mathe Themen
    ArrayList<String> zielIdList, zielThemaList, zielZeitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lernziel);

        initViews();
        setDataForSpinner();

        storeDataInArrays();

        zielRvAdapter=new ZielRvAdapter(LernzielActivity.this, this, zielIdList, zielThemaList, zielZeitList);
        rvZiele.setAdapter(zielRvAdapter);
        rvZiele.setLayoutManager(new LinearLayoutManager(LernzielActivity.this));


    }

    /**
     * Initialition von views
     */
    public void initViews()
    {
        spinnerThemen=findViewById(R.id.spinnerThemen);
        etZielZeit=findViewById(R.id.etZielZeit);
        databaseHelperLernZiel=new DatabaseHelperLernZiel(LernzielActivity.this);

        rvZiele=findViewById(R.id.rvZiele);
        zielIdList=new ArrayList<>();
        zielThemaList=new ArrayList<>();
        zielZeitList=new ArrayList<>();

    }

    public void storeDataInArrays ()
    {
        Cursor cursor=databaseHelperLernZiel.readAllData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                zielIdList.add(cursor.getString(0));
                zielThemaList.add(cursor.getString(1));
                zielZeitList.add(cursor.getString(2));
            }
        }
    }

    public void addZiel (View view)
    {
        DatabaseHelperLernZiel db=new DatabaseHelperLernZiel(LernzielActivity.this);
        db.addNote("grundlagen", etZielZeit.getText().toString().trim());
    }

    public void setDataForSpinner ()
    {
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.mathe_themen, R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerThemen.setAdapter(adapter);
    }

    public void btnSpeichernClicked (View view)
    {
        Intent intent = new Intent(LernzielActivity.this, MainActivity.class);
        startActivity(intent);
    }
}