package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {
    public final UserService userService;
    public final NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/addnote")
    public String addNote(Authentication authentication,Note note, Model model){
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();
        note.setUserId(userId);
        noteService.addNote(note);
        model.addAttribute("notes", noteService.getNotes());
        return "redirect:/home";
    }

}
