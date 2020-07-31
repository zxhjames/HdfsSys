package com.james.imgsys.group.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Index {
    @GetMapping("/index")
    public String show(Model model){
        model.addAttribute("msg","123456789");
        return "index";
    }

    @GetMapping("/")
    public String Index(Model model){
        return "index";
    }
}
