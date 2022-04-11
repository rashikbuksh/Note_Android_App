package com.techrzit.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String Database_name= "note";
    private static final int Version= 1;
    private int totalProblem;

    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            Toast.makeText(context,"Table Created ",Toast.LENGTH_LONG).show();
            db.execSQL("Create TABLE note (noteid INTEGER PRIMARY KEY AUTOINCREMENT, courseid varchar(20), title varchar(50), dateoflecture varchar(10), description varchar(500));");
            }
        catch (Exception e){
            Toast.makeText(context,"Error: "+e,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists note;");
        onCreate(db);
    }

    public Boolean inserNote(String courseid, String title, String dateoflecture, String description){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("courseid", courseid);
        contentValues.put("title", title);
        contentValues.put("dateoflecture", dateoflecture);
        contentValues.put("description", description);
        long result = DB.insert("note",null,contentValues);
        if(result==-1) return false;
        else return true;
    }

    public ArrayList<Note> getNote(){
        ArrayList<Note> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor getProblem = sqLiteDatabase.rawQuery("SELECT noteid, courseid, title, dateoflecture, description FROM note;",null);
        while(getProblem.moveToNext()){
            int noteid = getProblem.getInt(0);
            String courseid = getProblem.getString(1);
            String title = getProblem.getString(2);
            String dateoflecture = getProblem.getString(3);
            String description = getProblem.getString(4);

            Note note = new Note(noteid, courseid, title, dateoflecture, description);
            arrayList.add(note);
        }
        return arrayList;
    }

    public String[] getNotebyid(int id){
        String noteinfo[]= new String[5];
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor notes = sqLiteDatabase.rawQuery("SELECT * from note WHERE noteid='"+id+"';", null);
        if (notes.moveToFirst()) {
            for(int i=1;i<=4;i++){
                noteinfo[i] = notes.getString(i);
                System.out.println(noteinfo[i]);
            }
        }
        return noteinfo;
    }
}
