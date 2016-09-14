package com.dharmenn.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MyNotes";
    public static final String TABLE_NAME = "Notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE ="title";
    public static final String COLUMN_TEXT = "text";



    private static final int DB_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +TABLE_NAME
                +"(" +COLUMN_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_TITLE+
                " VARCHAR, " +COLUMN_TEXT+
                " VARCHAR);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addNote(String title, String text){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE,title);
        contentValues.put(COLUMN_TEXT, text);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public Cursor getNote(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE id="+id+";";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor getAllNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME;
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
}