package com.dharmenn.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditNoteActivity extends Activity {
    private SQLiteDatabase db;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        ((TextView) findViewById(R.id.todoDate)).setText(simpleDateFormat.format(new Date()));
        dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.onCreate(dataBaseHelper.getWritableDatabase());
    }

    public void saveToDo(View view) {
        Intent intent = new Intent(this, NoteListActivity.class);
        String todoTitle = ((EditText) findViewById(R.id.noteTitle)).getText().toString();
        String todoText = ((EditText) findViewById(R.id.todoText)).getText().toString();
        String todoDate = ((TextView) findViewById(R.id.todoDate)).getText().toString();
        dataBaseHelper.addNote(todoTitle, todoText, todoDate);
        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

}
