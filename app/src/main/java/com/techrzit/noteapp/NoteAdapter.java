package com.techrzit.noteapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {

    Context context;
    ArrayList<Note> arrayList;
    int noteid;
    MyDatabaseHelper DB;
    public NoteAdapter(Context context, ArrayList<Note> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_note_row, parent, false);

        TextView title = rowView.findViewById(R.id.lectitle);
        TextView courseid = rowView.findViewById(R.id.lecid);
        TextView lecdate = rowView.findViewById(R.id.lecdate);
        title.setOnClickListener(v->existingNote());


        Note note = arrayList.get(position);

        noteid = note.getNoteid();

        title.setText(note.getTitle());
        courseid.setText(note.getCourseid());
        lecdate.setText(note.getDateoflecture());

        return rowView;
    }

    private void existingNote() {
        Intent intent = new Intent(context, noteShow.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("noteid",noteid);
        System.out.println("activity sent");
        context.startActivity(intent);
    }
}
