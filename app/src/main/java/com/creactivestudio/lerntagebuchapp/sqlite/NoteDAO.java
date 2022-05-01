package com.creactivestudio.lerntagebuchapp.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class NoteDAO {

    public void addNote (DatabaseHelper db, String tableName, String noteText )
    {
        SQLiteDatabase database=db.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("noteText", noteText);
        database.insertOrThrow(tableName, null, contentValues);
        database.close();
    }

    public ArrayList<HashMap<String, String>> allNotes (DatabaseHelper databaseHelper)
    {
        ArrayList<Note> notesArrayList=new ArrayList<>();
        SQLiteDatabase database=databaseHelper.getWritableDatabase();
        Cursor c=database.rawQuery("SELECT * FROM notes", null);

        ArrayList<HashMap<String, String>> notesArrayL = new ArrayList<HashMap<String, String>>();

        if (c.moveToFirst())
        {
            do {
                HashMap<String, String> map= new HashMap<>();
                for(int i=0; i<c.getColumnCount();i++)
                {
                    map.put(c.getColumnName(i), c.getString(i));
                }
                notesArrayL.add(map);
            }
            while (c.moveToNext());
        }
       /*
        while (c.moveToNext())
        {
            Note note=new Note(c.getInt(c.getColumnIndex("noteId"))
            ,c.getString(c.getColumnIndex("noteText")));
            notesArrayList.add(note);
        }
        */

        return notesArrayL;
    }

}
