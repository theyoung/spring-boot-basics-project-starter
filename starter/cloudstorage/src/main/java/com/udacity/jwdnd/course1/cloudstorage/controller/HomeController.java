package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    public final NoteService noteService;

    public HomeController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping("/home")
    public String getHome(Model model){

        model.addAttribute("notes", noteService.getNotes());
        return "home";
    }

}
