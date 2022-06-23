package com.creactivestudio.lerntagebuchapp.goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.creactivestudio.lerntagebuchapp.Helper;
import com.creactivestudio.lerntagebuchapp.R;

import java.util.ArrayList;

public class SelectThemeToLearn extends AppCompatActivity {

    Helper helper;
    AllThemesRecyclerViewAdapter allThemesRecyclerViewAdapter;
    RecyclerView rvAllThemes;
    DatabaseHelperLearningGoals databaseHelperLearningGoals;
    ArrayList<String> goalIdList, goalThemeList, goalTimeList; // Die Werte bekommen wir von SQLite Datenbank dann ordnen wir zu dieser Array Listen ein.
    ArrayList<Integer> learnTimeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_learn_plan);


        init();// Initialition

        storeDataInArrays(); // Wir speichern alle Daten von Sqlite zu oben besreibene Array Lists ein.
        rvAllThemes.setAdapter(allThemesRecyclerViewAdapter);
        rvAllThemes.setLayoutManager(new GridLayoutManager(SelectThemeToLearn.this, 2));

    }

    /**
     * Initialition von Views
     */
    public void init()
    {
        databaseHelperLearningGoals=new DatabaseHelperLearningGoals(SelectThemeToLearn.this);
        rvAllThemes=findViewById(R.id.rvAllThemes);
        helper=new Helper(this);
        goalIdList =new ArrayList<>();
        goalThemeList =new ArrayList<>();
        goalTimeList =new ArrayList<>();
        learnTimeList =new ArrayList<>();

        allThemesRecyclerViewAdapter=new AllThemesRecyclerViewAdapter(this, this, goalIdList, goalThemeList, goalTimeList, learnTimeList);


    }


    /**
     * Wir speichern alle Daten von Sqlite zu oben beschriebene Array Lists ein.
     */
    public void storeDataInArrays ()
    {
        Cursor cursor= databaseHelperLearningGoals.readAllData(); // Cursor geht alle Columns schritt für schritt vor.
        if(cursor.getCount()==0) // Wenn kein Data gibt dann informiere den Benutzer.
        {
            helper.showToast(getString(R.string.no_data), Helper.TOAST_MESSAGE_TYPE_ERROR);
        }
        else
        {
            while(cursor.moveToNext()) // Geh weiter bis data gibt
            {
                goalIdList.add(cursor.getString(0));
                goalThemeList.add(cursor.getString(1));
                goalTimeList.add(cursor.getString(2));
                learnTimeList.add(cursor.getInt(3));

                //goalsRecyclerViewAdapter.notifyDataSetChanged();

            }
        }
    }

    /**
     * Nach dem auswählen das Thema, fängt der Benutzer zu lernen.
     * @param view
     */
    /*
    public void startLearning (View view)
    {
        Intent intent=new Intent(SelectLearnPlan.this, StatusActivity.class);
        startActivity(intent);
    }
    */

}