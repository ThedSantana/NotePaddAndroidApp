package com.dharmenn.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MyNotes";
    public static final String TABLE_NAME = "NotesList";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE ="title";
    public static final String COLUMN_TEXT = "text";



    private static final int DB_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME
                +"(" +COLUMN_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_TITLE+
                " VARCHAR, " +COLUMN_TEXT+
                " VARCHAR, DATE VARCHAR);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addNote(String title, String text, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE,title);
        contentValues.put(COLUMN_TEXT, text);
        contentValues.put("DATE", date);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public Cursor getNote(String title){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE title="+title+";";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public List<String> getAllNotes(){
        List<String> notes = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);

        String name;
        if (cursor .moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                name = cursor.getString(cursor
                        .getColumnIndex(COLUMN_TITLE));
                notes.add(name);
                cursor.moveToNext();
            }
        }
        return notes;
    }
}