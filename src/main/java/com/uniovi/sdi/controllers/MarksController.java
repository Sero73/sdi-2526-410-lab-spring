package com.uniovi.sdi.controllers;

import com.uniovi.sdi.entities.Mark;
import com.uniovi.sdi.services.MarksService;
import com.uniovi.sdi.services.UsersService;
import com.uniovi.sdi.validators.AddMarkValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MarksController {
    private final MarksService marksService;
    private final UsersService usersService;
    private final AddMarkValidator addMarkValidator;

    public MarksController(MarksService marksService, UsersService usersService, AddMarkValidator addMarkValidator) {
        this.marksService = marksService;
        this.usersService = usersService;
        this.addMarkValidator = addMarkValidator;
    }

    @GetMapping(value = "/mark/add")
    public String getMark(Model model) {
        model.addAttribute("usersList", usersService.getUsers());
        model.addAttribute("mark", new Mark());
        return "mark/add";
    }

    @PostMapping("/mark/add")
    public String setMark(@Validated Mark mark, BindingResult result, Model model) {
        addMarkValidator.validate(mark, result);
        if (result.hasErrors()) {
            model.addAttribute("usersList", usersService.getUsers());
            return "mark/add";
        }

        marksService.addMark(mark);
        return "redirect:/mark/list";
    }

    @GetMapping ("/mark/list")
    public String getList(Model model) {
        model.addAttribute("marksList", marksService.getMarks());
        return "mark/list";
    }

    @GetMapping(value = "/mark/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return  "mark/details";
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @GetMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/edit";
    }

    @PostMapping(value = "/mark/edit/{id}")
    public String setEdit(@ModelAttribute Mark mark, @PathVariable Long id) {
        Mark originalMark = marksService.getMark(id);
        originalMark.setScore(mark.getScore());
        originalMark.setDescription(mark.getDescription());
        marksService.addMark(originalMark);
        return "redirect:/mark/details/" + id;
    }

    @GetMapping("/mark/list/update")
    public String updateList(Model model){
        model.addAttribute("marksList", marksService.getMarks() );
        return "mark/list :: marksTable";
    }


}
