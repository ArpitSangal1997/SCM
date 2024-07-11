package com.scm.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm.entities.User;
import com.scm.scm.forms.UserForm;
import com.scm.scm.helpers.Message;
import com.scm.scm.helpers.MessageType;
import com.scm.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PageController {
   
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    } 
    
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("home page handler");
        model.addAttribute("name", "arpit sangal ");
        model.addAttribute("yotubeChannel", "learn springboot");
        return "home";
    }
    @RequestMapping("/about")
    public String about() {
        System.out.println("about page loading");
        return "about";
    }

    @RequestMapping("/services")
    public String services() {
        System.out.println("services page loading");
        return "services";
    }
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rbindingResult, HttpSession session){
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://www.freepik.com/free-psd/3d-illustration-person-with-sunglasses_27470334.htm#query=default%20user&position=2&from_view=keyword&track=ais_user&uuid=7399a2ba-623f-4bb9-98d0-023e5bbe6354")
        // .build();

        //validate form data
        if (rbindingResult.hasErrors()) {
            return "register";
        }

        User user = new User();

        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://www.freepik.com/free-psd/3d-illustration-person-with-sunglasses_27470334.htm#query=default%20user&position=2&from_view=keyword&track=ais_user&uuid=7399a2ba-623f-4bb9-98d0-023e5bbe6354");
        userService.saveUser(user);
        
        //messsage here
        Message message = Message.builder().content("Registration Successfull").type(MessageType.blue).build();
        session.setAttribute("message", message);

        return "redirect:/register";
    }
    
    
}
