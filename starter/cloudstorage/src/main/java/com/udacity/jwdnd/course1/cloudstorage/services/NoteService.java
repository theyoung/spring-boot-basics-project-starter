package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper mapper) {
        this.noteMapper = mapper;
    }

    public void addNote(Note note){
        noteMapper.insertNote(note);
    }

    public List<Note> getNotes(){
        return noteMapper.getNotes();
    }
}
