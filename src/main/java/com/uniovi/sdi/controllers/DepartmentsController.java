package com.uniovi.sdi.controllers;

import com.uniovi.sdi.entities.Department;
import com.uniovi.sdi.services.DepartmentsService;
import com.uniovi.sdi.validators.AddDepartmentValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentsController {
    private final DepartmentsService departmentsService;
    private final AddDepartmentValidator  addDepartmentValidator;

    public DepartmentsController(DepartmentsService departmentsService, AddDepartmentValidator addDepartmentValidator) {
         this.addDepartmentValidator = addDepartmentValidator;
        this.departmentsService = departmentsService;

    }

    @RequestMapping("/department/list")
    public String getList(Model departmentModel) {
        departmentModel.addAttribute("departmentsList", departmentsService.getDepartments());
        return "department/list";
    }

    @GetMapping(value  = "/department/add")
    public String getDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "department/add";
    }

    @PostMapping(value = "/department/add")
    public String setDepartment(@Validated Department department, BindingResult result) {
        addDepartmentValidator.validate(department, result);
        if(result.hasErrors()){
            return "department/add";
        }
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
