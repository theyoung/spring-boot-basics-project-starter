package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    public final NoteService noteService;
    public final CredentialService credentialService;

    public final UserService userService;
    public final FileService fileService;

    public HomeController(NoteService noteService, CredentialService credentialService, UserService userService, FileService fileService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @RequestMapping("/home")
    public String getHome(Model model, Authentication authentication){
        User user =userService.getUser(authentication.getName());

        if (user == null) {
            return "redirect:/logout";
        }

        model.addAttribute("notes", noteService.getNotes(user.getUserId()));
        model.addAttribute("credentials", credentialService.getCredentials(user.getUserId()));
        model.addAttribute("files", fileService.getFiles(user.getUserId()));
        return "home";
    }

}
