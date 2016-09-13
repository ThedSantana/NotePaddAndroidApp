package com.dharmenn.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditNoteActivity extends Activity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        createDatabase();
    }

    public void saveToDo(View view){
//        Intent intent = new Intent(this, TodoListActivity.class);
        String todoTitle = ((EditText) findViewById(R.id.todoTitle)).getText().toString();
        String todoText = ((EditText) findViewById(R.id.todoText)).getText().toString();
        insertIntoDB(todoTitle, todoText);
//        startActivity(intent);
    }

    protected void createDatabase(){
        db=openOrCreateDatabase("PersonDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS persons(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, todoTitle VARCHAR,todoText VARCHAR);");
    }

    protected void insertIntoDB(String title, String text){
        String query = "INSERT INTO persons (todoTitle,todoText) VALUES('"+title+"', '"+text+"');";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
    }

}
