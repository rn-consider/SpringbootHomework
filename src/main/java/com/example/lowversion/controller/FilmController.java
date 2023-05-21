package com.example.lowversion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FilmController {

    @GetMapping("/detail/{type}/{path}")
    public String toDetail(@PathVariable("type")String type,
                           @PathVariable("path")String path) {
        return "detail/"+type+"/"+path;
    }}
