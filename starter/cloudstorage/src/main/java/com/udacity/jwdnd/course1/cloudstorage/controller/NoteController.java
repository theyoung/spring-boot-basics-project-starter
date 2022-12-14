package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {
    public final UserService userService;
    public final NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping("/deletenote/{noteId}")
    public String deleteNote(@PathVariable String noteId, Model model){
        int result = noteService.deleteNote(noteId);

        if(result == 1){
            model.addAttribute("success", true);
        } else {
            model.addAttribute("success", false);
        }

        return "result";
    }

    @PostMapping("/addnote")
    public String doNote(Authentication authentication,Note note, Model model){
        int result = 0;

        String name = authentication.getName();
        User user = userService.getUser(name);

        if(user == null){
            model.addAttribute("errMessage", true);
            return "result";
        } else {
            note.setUserId(user.getUserId());
        }

        if(note.getNoteId() == null){
            result = noteService.addNote(note);
        } else {
            result = noteService.updateNote(note);
        }

        if(result != 0){
            model.addAttribute("success", true);
        } else {
            model.addAttribute("success", false);
        }

        return "result";
    }


}
