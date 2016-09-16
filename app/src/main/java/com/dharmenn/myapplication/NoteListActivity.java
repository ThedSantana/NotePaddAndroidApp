package com.dharmenn.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends Activity {

    private DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        populateNotesList();

    }

    private void populateNotesList() {
        ListView textView = (ListView) findViewById(R.id.note_list_view);
        List<String> allNotes = dataBaseHelper.getAllNotes();
        Log.d("myTag", allNotes.toString());
        ArrayAdapter<String> notesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, allNotes);
        textView.setAdapter(notesAdapter);
    }

    public void addNewNote(View view) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        startActivity(intent);
    }
}
