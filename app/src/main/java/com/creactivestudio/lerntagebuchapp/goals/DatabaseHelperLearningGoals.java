package com.creactivestudio.lerntagebuchapp.goals;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.creactivestudio.Helper;
import com.creactivestudio.lerntagebuchapp.R;
import com.creactivestudio.lerntagebuchapp.note.ViewAllNotesActivity;

public class DatabaseHelperLearningGoals extends SQLiteOpenHelper {

    Helper helper;
    private Context context;
    private static final String DATABASE_NAME = "LernZiel.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_NAME = "themen";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_THEMA_NAME = "thema_name";
    private static final String COLUMN_ZIEL_ZEIT = "ziel_zeit";
    private static final String COLUMN_GELERNTE_ZEIT = "gelernte_zeit";

    public DatabaseHelperLearningGoals(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        helper=new Helper(context);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       /*
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_THEMA_NAME + " TEXT, " +
                        COLUMN_ZIEL_ZEIT + " TEXT);";
        sqLiteDatabase.execSQL(query);

        */
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_THEMA_NAME + " TEXT, " +
                        COLUMN_ZIEL_ZEIT + " TEXT, " +
                        COLUMN_GELERNTE_ZEIT + " INTEGER);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void saveTimeToSql (String themeId, int learnTime)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COLUMN_GELERNTE_ZEIT, learnTime);

        long result=database.update(TABLE_NAME, contentValues, "_id = ?", new String[]{themeId});

        if(result==-1)
        {
            helper.showToast(context.getString(R.string.update_failed), Helper.TOAST_MESSAGE_TYPE_ERROR);
        }
        else
        {
            helper.showToast(context.getString(R.string.successfully_updated), Helper.TOAST_MESSAGE_TYPE_SUCCESS);
          //  context.startActivity(new Intent(context, ViewAllNotesActivity.class));

        }
    }

    public void addNote (String themaName, String zielZeit) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_THEMA_NAME, themaName);
        cv.put(COLUMN_ZIEL_ZEIT, zielZeit);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result ==-1)
        {
            helper.showToast(context.getString(R.string.failed), Helper.TOAST_MESSAGE_TYPE_ERROR);
        }
        else
        {
            helper.showToast(context.getString(R.string.added_successfully), Helper.TOAST_MESSAGE_TYPE_SUCCESS);
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

    public void getOneRow (String goalId)
    {
        String query="SELECT * FROM " + TABLE_NAME + " WHERE _id=goalId ";
        SQLiteDatabase db=this.getReadableDatabase();


        Cursor cursor=null;
        if(db!=null)
        {
            cursor=db.rawQuery(query, null);
        }


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
            helper.showToast(context.getString(R.string.update_failed), Helper.TOAST_MESSAGE_TYPE_ERROR);
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
            helper.showToast(context.getString(R.string.failed_to_delete), Helper.TOAST_MESSAGE_TYPE_ERROR);
        }
        else
        {
            helper.showToast(context.getString(R.string.deleted_successfully), Helper.TOAST_MESSAGE_TYPE_SUCCESS);
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

    /**
     * Kontrolliere ob der Benutzer mindestens einen Thema hinzugefügt hat.
     * @return  Wenn ja return true wenn nicht return false
     */
    public boolean isThemenSaved ()
    {
        boolean isThemenSaved=false;

        String query="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=null;
        if(database!=null)
        {
            cursor=database.rawQuery(query,null);
            if(cursor.moveToNext()) isThemenSaved=true;
            else isThemenSaved=false;
        }
        else
        {
            helper.showToast(context.getString(R.string.an_error_is_occured), Helper.TOAST_MESSAGE_TYPE_ERROR);
        }

        return isThemenSaved;
    }


}
