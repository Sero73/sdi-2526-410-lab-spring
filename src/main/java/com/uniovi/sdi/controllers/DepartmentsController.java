package com.uniovi.sdi.controllers;

import com.uniovi.sdi.entities.Department;
import com.uniovi.sdi.services.DepartmentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    public DepartmentsController(DepartmentsService departmentsService){
        this.departmentsService = departmentsService;
    }

    @RequestMapping("/department/list")
    public String getList(Model departmentModel) {
        departmentModel.addAttribute("departmentsList", departmentsService.getDepartments());
        return "department/list";
    }

    @GetMapping(value  = "/department/add")
    public String getDepartment() {
        return "department/add";
    }

    @PostMapping(value = "/department/add")
    public String setDepartment(@ModelAttribute Department department) {
       departmentsService.addDepartment(department);
       return "redirect:/department/list";
    }

    @GetMapping("/department/details")
    public String getDetail(Model departmentModel, @RequestParam("id") Long id) {
        departmentModel.addAttribute("department", departmentsService.getDepartment(id));
        return "department/details";
    }

    @RequestMapping("/department/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentsService.deleteDepartment(id);
        return "redirect:/department/list";
    }

    @RequestMapping(value =  "/department/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("department", departmentsService.getDepartment(id));
        return "department/edit";
    }

    @PostMapping(value="/department/edit/{id}")
    public String setEdit(@ModelAttribute Department department, @PathVariable Long id){
        department.setId(id);
        departmentsService.addDepartment(department);
        return "redirect:/department/details?id="+ id;
    }


}
