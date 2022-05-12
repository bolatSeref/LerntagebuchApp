package com.creactivestudio.lerntagebuchapp.goals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.creactivestudio.lerntagebuchapp.R;

public class DatabaseHelperLearningGoals extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "LernZiel.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_NAME = "themen";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_THEMA_NAME = "thema_name";
    private static final String COLUMN_ZIEL_ZEIT = "ziel_zeit";

    public DatabaseHelperLearningGoals(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_THEMA_NAME + " TEXT, " +
                        COLUMN_ZIEL_ZEIT + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNote (String themaName, String zielZeit) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_THEMA_NAME, themaName);
        cv.put(COLUMN_ZIEL_ZEIT, zielZeit);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result ==-1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor readAllData ()
    {
        String query = "SELECT * FROM " + TABLE_NAME; // Bring alle Daten
        SQLiteDatabase db=this.getReadableDatabase(); // Lesende zugriff

        Cursor cursor = null;
        if (db!=null)
        {
            cursor=db.rawQuery(query, null);

        }
        return cursor;
    }


    public void updateData(String goalId, String goalTheme, String goalTime)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_ID, goalId);
        cv.put(COLUMN_THEMA_NAME, goalTheme);
        cv.put(COLUMN_ZIEL_ZEIT, goalTime);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{goalId});
        if(result==-1) // Wenn ein Fehler aufgetreten ist
        {
            Toast.makeText(context, context.getString(R.string.update_failed), Toast.LENGTH_SHORT).show();
        }
        else  // Wenn kein Fehler gibt und Problemlos aktualisiert ist
        {
            Toast.makeText(context, context.getString(R.string.successfully_updated), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow (String row_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result==-1) // An error
        {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Gib gesamte Lern Ziel in Minuten zurück.
     * @return
     */
    public int getLearningGoalTime ()
    {
        String query = "SELECT SUM(" + DatabaseHelperLearningGoals.COLUMN_ZIEL_ZEIT + ") as Total FROM " + TABLE_NAME; // Bring alle Daten
        SQLiteDatabase db=this.getReadableDatabase(); // Lesende zugriff
        int total=0;
        Cursor cursor = null;
        if (db!=null)
        {
            cursor=db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                total = cursor.getInt(cursor.getColumnIndex("Total"));
            }
        }

        return total;
    }


}
