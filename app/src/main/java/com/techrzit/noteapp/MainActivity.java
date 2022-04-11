package com.techrzit.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton add;
    ArrayList<Note> arrayList;
    NoteAdapter noteAdapter;
    ListView listView;
    MyDatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.imageButton);
        add.setOnClickListener(v-> addNote());
        listView = findViewById(R.id.listView);
        DB= new MyDatabaseHelper(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        existingNote();
    }
    private void addNote() {
            Intent intent = new Intent(this, noteShow.class);
            startActivity(intent);
    }
    public void existingNote(){
        arrayList = DB.getNote();
        noteAdapter = new NoteAdapter(this,arrayList);
        listView.setAdapter(noteAdapter);
        noteAdapter.notifyDataSetChanged();
    }
}