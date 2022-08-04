package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {

    public final UserService userService;
    public final CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @GetMapping("/deletecredential/{credentialId}")
    public String deleteCredential(@PathVariable String credentialId, Model model){

        int result = credentialService.deleteCredential(credentialId);

        if(result == 1){
            model.addAttribute("success", true);
        } else {
            model.addAttribute("success", false);
        }

        return "result";
    }

    @PostMapping("/addcredential")
    public String doCredential(Authentication authentication, Credential credential, Model model){
        int result = 0;

        String name = authentication.getName();
        User user = userService.getUser(name);

        if(user == null){
            model.addAttribute("errMessage", true);
            return "result";
        } else {
            credential.setUserId(user.getUserId());
        }

        if(credential.getCredentialId() == null){
            result = credentialService.addCredential(credential);
        } else {
            result = credentialService.updateCredential(credential);
        }

        if(result != 0){
            model.addAttribute("success", true);
        } else {
            model.addAttribute("success", false);
        }

        return "result";
    }

}
