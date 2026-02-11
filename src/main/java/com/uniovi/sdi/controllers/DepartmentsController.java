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

    @RequestMapping(value  = "/department/add")
    public String getDepartment() {
        return "department/add";
    }

    @RequestMapping(value = "/department/add", method = RequestMethod.POST)
    public String setDepartment(@ModelAttribute Department department) {
       departmentsService.addDepartment(department);
       return "redirect:/department/list";
    }

    @RequestMapping("/department/details")
    public String getDetail(Model departmentModel, @RequestParam("code") String code) {
        departmentModel.addAttribute("department", departmentsService.getDepartment(code));
        return "department/details";
    }

    @RequestMapping(value =  "/department/edit/{code}")
    public String getEdit(@PathVariable String code) {
        return "department/edit";
    }

    @RequestMapping(value="/department/edit",method = RequestMethod.POST)
    public String setEdit(@ModelAttribute Department department){
      departmentsService.addDepartment(department);
        return "redirect:/department/details/"+ department.getCode();
    }

    @RequestMapping("/department/delete/{code}")
    public String deleteDepartment(@PathVariable String code) {
        departmentsService.deleteDepartment(code);
        return "redirect:/department/list";
    }
}
