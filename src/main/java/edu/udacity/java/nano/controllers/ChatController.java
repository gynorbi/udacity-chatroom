package edu.udacity.java.nano.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class ChatController {

    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request) {
        HttpSession oldSession = request.getSession(false);
        if(oldSession == null)
            return new ModelAndView( new RedirectView("/login"));
        else{
            Object username = oldSession.getAttribute("USERNAME");
            return new ModelAndView("chat", "USERNAME", username);
        }
    }
}
