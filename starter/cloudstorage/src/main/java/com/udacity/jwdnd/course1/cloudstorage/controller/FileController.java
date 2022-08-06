package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Controller
public class FileController {
    public final UserService userService;
    public final FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/deletefile/{fileId}")
    public String deleteNote(@PathVariable String fileId, Model model){
        int result = fileService.deleteFile(fileId);

        if(result == 1){
            model.addAttribute("success", true);
        } else {
            model.addAttribute("success", false);
        }

        return "result";
    }

    @PostMapping("/upload")
    public String upload(Authentication authentication, @RequestParam("fileUpload") MultipartFile upLoadedFile, Model model) throws IOException {
        int result = 0;

        String name = authentication.getName();
        User user = userService.getUser(name);

        File file = new File();
        file.setFileName(upLoadedFile.getOriginalFilename());
        file.setFileSize(String.valueOf(upLoadedFile.getSize()));
        file.setContentType(upLoadedFile.getContentType());
        file.setFiledata(upLoadedFile.getBytes());

        if(user == null){
            model.addAttribute("errMessage", true);
            return "result";
        } else {
            file.setUserId(user.getUserId());
        }

        result = fileService.addFile(file);

        if(result != 0){
            model.addAttribute("success", true);
        } else {
            model.addAttribute("success", false);
        }

//        File file1 = fileService.findFileById("1");
//        System.out.println(file1.getFiledata());
        return "result";
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<ByteArrayResource> viewFile(@PathVariable("fileId") String fileId){
        File file=fileService.findFileById(fileId);
        System.out.println(Arrays.toString(file.getFiledata()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getFiledata()));
    }

}
