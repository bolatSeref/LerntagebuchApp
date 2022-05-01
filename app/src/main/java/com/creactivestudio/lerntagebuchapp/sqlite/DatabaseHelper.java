package com.creactivestudio.lerntagebuchapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "LernTagebuch.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "note_title";
    private static final String COLUMN_NOTE_TEXT = "note_text";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_NOTE_TEXT + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNote (String noteTitle, String noteText) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_TITLE, noteTitle);
        cv.put(COLUMN_NOTE_TEXT, noteText);
        
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
    
    
}
