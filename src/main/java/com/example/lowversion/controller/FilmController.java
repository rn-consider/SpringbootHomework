package com.example.lowversion.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FilmController {

    @GetMapping("/detail/{type}/{path}")
    public String toDetail(@PathVariable("type")String type,
                           @PathVariable("path")String path) {
        return "detail/"+type+"/"+path;
    }
    @GetMapping("/userLogin")
    public String toLoginPage() {
        return "login/login";
    }
    @GetMapping("/getuserByContext")
    @ResponseBody
    public void getUser2() {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("userDetails: "+context);
        Authentication authentication = context.getAuthentication();
        UserDetails principal =
                (UserDetails)authentication.getPrincipal();
        System.out.println(principal);
        System.out.println("username: "+principal.getUsername());
    }
}
