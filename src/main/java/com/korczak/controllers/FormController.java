package com.korczak.controllers;

import com.korczak.domain.Gender;
import com.korczak.domain.Nationality;
import com.korczak.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by Korczi on 2017-03-03.
 */
@Controller
@RequestMapping("/form")
public class FormController
{
    @RequestMapping(value = "" , method = RequestMethod.GET)
    public String formGet(Model m)
    {
        Person p = new Person();
        List<Nationality> nationalities = new ArrayList<>(Arrays.asList(Nationality.values()));
        List<Gender> genders = new ArrayList<>(Arrays.asList(Gender.values()));
        List<Integer> insurancePeriod = new ArrayList<>();
        Collections.addAll(insurancePeriod,1,2,3,4);
        m.addAttribute("person", p);
        m.addAttribute("nationalities", nationalities);
        m.addAttribute("genders", genders);
        m.addAttribute("insurancePeriod", insurancePeriod);
        return "form";
    }

    @RequestMapping(value = "" , method = RequestMethod.POST)
    public String formPost(@ModelAttribute @Valid Person p, BindingResult result, HttpServletRequest request, Model model) //dzieki @ModelAttribute przechwycimy obiekt klasy Person z formularza
    {
        if (result.hasErrors()) {

            System.out.println("================BINDING RESULTS=====================");
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
            System.out.println("====================================================");

            List<Nationality> nationalities = new ArrayList<>();
            Collections.addAll(nationalities, Nationality.FR, Nationality.GER, Nationality.UK, Nationality.PL);
            model.addAttribute("nationalities", nationalities);

            List<Gender> genderList = new ArrayList<>();
            Collections.addAll(genderList, Gender.MALE, Gender.FEMALE);
            model.addAttribute("genders", genderList);

            List<Integer> insurancePeriod = new ArrayList<>();
            Collections.addAll(insurancePeriod, 1, 2, 3, 4);
            model.addAttribute("insurancePeriod",insurancePeriod);

            Person p1 = new Person();
            model.addAttribute("person", p1);

            return "form";
        }

        String realPath = request.getSession().getServletContext().getRealPath("\\");
        String imagePath = "\\static\\images\\";
        String fileName = p.getMultipartFile().getOriginalFilename();
        System.out.println(p);

        try {
            FileCopyUtils.copy(p.getMultipartFile().getBytes(), new File(realPath + imagePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/test";
    }
}
