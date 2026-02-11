package com.uniovi.sdi.controllers;

import com.uniovi.sdi.entities.Department;
import com.uniovi.sdi.entities.Mark;
import com.uniovi.sdi.services.DepartmentsService;
import com.uniovi.sdi.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {
    private final DepartmentsService departmentsService;

    public DepartmentController(DepartmentsService departmentsService){
        this.departmentsService = departmentsService;
    }

    @RequestMapping("/departments/list")
    public String getList() {
        return departmentsService.getDepartments().toString();
    }

    @RequestMapping(value  = "/departments/add")
    public String getDepartment(@ModelAttribute Department department) {
        departmentsService.addDepartment(department);
        return "OK";
    }

    @RequestMapping(value = "/deparments/add")
    public String setDepartment(@ModelAttribute Department department) {
       departmentsService.addDepartment(department);
       return "departamento añadido";
    }

    @RequestMapping("/departments/details/{code}")
    public String getDetail(@PathVariable String code) {
        return departmentsService.getDepartment(code).toString();
    }

    @RequestMapping(value =  "/departments/edit/{code}")
    public String getEdit(@ModelAttribute Department department) {
        departmentsService.addDepartment(department);
        return "Editando departamento";
    }

    @PostMapping(value="/departments/edit")
    public String setEdit(@ModelAttribute Department department){
      departmentsService.addDepartment(department);
      return "redirect:/department/details" + department.getCode();
    }

    @RequestMapping("/departments/delete/{code}")
    public String deleteDepartment(@PathVariable String code) {
        departmentsService.deleteDepartment(code);
        return "Ok";
    }
}
