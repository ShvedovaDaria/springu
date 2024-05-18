package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class YController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping ("/home")
    @ResponseBody
    public String home() {
        return "<a href='/'>go to main page</a><br><button>ok</button>";
    }
}