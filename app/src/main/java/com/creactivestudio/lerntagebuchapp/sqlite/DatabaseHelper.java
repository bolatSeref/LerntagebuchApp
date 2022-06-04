package com.creactivestudio.lerntagebuchapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.creactivestudio.Helper;
import com.creactivestudio.lerntagebuchapp.R;

import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    Helper helper;
    private static final String DATABASE_NAME = "LernTagebuch.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "note_title";
    private static final String COLUMN_NOTE_TEXT = "note_text";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        helper=new Helper(context);
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
            helper.showToast(context.getString(R.string.failed), Helper.TOAST_MESSAGE_TYPE_ERROR);
        }
        else 
        {
            helper.showToast(context.getString(R.string.added_successfully), Helper.TOAST_MESSAGE_TYPE_SUCCESS);
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
        cv.put(COLUMN_TITLE, noteTitle);
        cv.put(COLUMN_NOTE_TEXT, noteText);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{noteId});
        if(result==-1)
        {
            helper.showToast(context.getString(R.string.update_failed), Helper.TOAST_MESSAGE_TYPE_ERROR);
        }
        else
        {
            helper.showToast(context.getString(R.string.successfully_updated), Helper.TOAST_MESSAGE_TYPE_SUCCESS);
        }
    }


    /**
     * To delete one row from database
     * @param row_id
     */
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
    
    
}
