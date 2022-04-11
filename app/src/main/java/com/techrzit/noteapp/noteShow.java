package com.techrzit.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class noteShow extends AppCompatActivity {

    Button save,delete, back;
    MyDatabaseHelper DB;
    EditText courseid, title, dateoflecture, description;
    int noteid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_show);
        courseid = findViewById(R.id.courseid);
        title = findViewById(R.id.title);
        dateoflecture = findViewById(R.id.dateoflecture);
        description = findViewById(R.id.description);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);
        delete = findViewById(R.id.delete);
        delete.setVisibility(View.INVISIBLE);
        DB= new MyDatabaseHelper(this);

        save.setOnClickListener(v->saveInDB());
        back.setOnClickListener(v->onBackPressed());
        delete.setOnClickListener(v->{
            Boolean noError = DB.deleteNote(noteid);
            if(noError){
                System.out.println("Successful");
            }
            else{
                System.out.println("Unsuccessful");
            }
            initializeFormWithExistingData();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            noteid = extras.getInt("noteid");
            delete.setVisibility(View.VISIBLE);
        }
        //System.out.println(noteid);
        initializeFormWithExistingData();
    }

    private void initializeFormWithExistingData() {
        String value[] = DB.getNotebyid(noteid);
        //System.out.println(value[1]);

        if (value != null) {
            String course_id = value[1];
            String titles = value[2];
            String dateof_lecture = value[3];
            String desc = value[4];

            courseid.setText(course_id);
            title.setText(titles);
            dateoflecture.setText(dateof_lecture);
            description.setText(desc);
        }
    }
    private void saveInDB() {
        String leccourseid = courseid.getText().toString().trim();
        String lectitle = title.getText().toString().trim();
        String lecdateoflecture = dateoflecture.getText().toString().trim();
        String lecdescription = description.getText().toString().trim();
        Boolean noError = DB.inserNote(leccourseid, lectitle, lecdateoflecture, lecdescription);
        if(noError){
            mainActivity();
        }
        else System.out.println("Data Not Added");
    }
    void mainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}