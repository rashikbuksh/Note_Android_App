package com.techrzit.noteapp;

public class Note {
    int noteid;
    String courseid, title, dateoflecture, description;



    public Note(int noteid , String courseid, String title , String dateoflecture, String description) {
        this.noteid = noteid;
        this.courseid = courseid;
        this.title = title;
        this.dateoflecture = dateoflecture;
        this.description = description;
    }

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateoflecture() {
        return dateoflecture;
    }

    public void setDateoflecture(String dateoflecture) {
        this.dateoflecture = dateoflecture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
