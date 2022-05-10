package com.creactivestudio.lerntagebuchapp.lernziel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelperLernZiel extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "LernZiel.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_NAME = "themen";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_THEMA_NAME = "thema_name";
    private static final String COLUMN_ZIEL_ZEIT = "ziel_zeit";

    public DatabaseHelperLernZiel (@Nullable Context context) {
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
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor = null;
        if (db!=null)
        {
            cursor=db.rawQuery(query, null);

        }
        return cursor;
    }

    public void updateData(String noteId, String noteTitle, String noteText)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_ID, noteId);
        cv.put(COLUMN_THEMA_NAME, noteTitle);
        cv.put(COLUMN_ZIEL_ZEIT, noteText);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{noteId});
        if(result==-1)
        {
            Toast.makeText(context, "Failed Updated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }


}
