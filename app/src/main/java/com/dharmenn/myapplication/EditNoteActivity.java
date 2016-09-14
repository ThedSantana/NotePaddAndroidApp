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
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        dataBaseHelper = new DataBaseHelper(this);
    }

    public void saveToDo(View view) {
        Intent intent = new Intent(this, NoteListActivity.class);
        String todoTitle = ((EditText) findViewById(R.id.noteTitle)).getText().toString();
        String todoText = ((EditText) findViewById(R.id.todoText)).getText().toString();
        dataBaseHelper.addNote(todoTitle, todoText);
        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

}
