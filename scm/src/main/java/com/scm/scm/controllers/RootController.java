package com.scm.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.scm.entities.User;
import com.scm.scm.helpers.Helper;
import com.scm.scm.services.UserService;

@ControllerAdvice
public class RootController {
     @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(RootController.class);
    
   
    
    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication){
        if (authentication == null) {
            return;
        }
        System.out.println("Adding logged in user information to the model");
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("user logged in: {}" + username);
        User user = userService.getUserByEmail(username);
        model.addAttribute("loggedInUser", user);
    }
}
