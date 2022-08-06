package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ExceptionController implements ErrorController {

    @RequestMapping("/error")
    public String showErrorPage(HttpServletResponse response, Model model) {
        model.addAttribute("errMessage", "Internal Server Error Appear" + response.getStatus());
        return "result";
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
