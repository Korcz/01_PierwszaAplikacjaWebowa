package com.korczak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Korczi on 2017-01-27.
 */
@Controller
@RequestMapping("/test")
public class TestController
{
    @RequestMapping
    public String test(Model m)
    {
        String nap = "ala ma kota";
        m.addAttribute("napis", nap);

        Integer age = 45;
        m.addAttribute("age", age);

        return "welcome";
    }

    @RequestMapping({"/example1", "/example2"})
    public String test2()
    {
        return "welcome2";
    }
}
