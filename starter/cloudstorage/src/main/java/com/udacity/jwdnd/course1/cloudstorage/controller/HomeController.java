package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    public final NoteService noteService;
    public final CredentialService credentialService;

    public HomeController(NoteService noteService, CredentialService credentialService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @RequestMapping("/home")
    public String getHome(Model model, Authentication authentication){
        if (authentication == null || authentication.getName() == null) {
            return "redirect:/logout";
        }

        model.addAttribute("notes", noteService.getNotes());
        model.addAttribute("credentials", credentialService.getCredentials());
        return "home";
    }

}
