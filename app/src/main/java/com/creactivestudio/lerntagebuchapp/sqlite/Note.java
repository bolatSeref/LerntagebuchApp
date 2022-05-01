package com.creactivestudio.lerntagebuchapp.sqlite;

public class Note {
    private String noteText;
    private int noteId;

    public Note ()
    {

    }
    public Note (int _noteId, String _noteText)
    {
        this.noteText=_noteText;
        this.noteId=_noteId;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
