package com.uniovi.sdi.services;

import com.uniovi.sdi.entities.Department;
import com.uniovi.sdi.repositories.DepartmentsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DepartmentsService {

    private final DepartmentsRepository departmentsRepository;

   public DepartmentsService(DepartmentsRepository departmentsRepository){
       this.departmentsRepository =departmentsRepository;
   }


    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentsRepository.findAll().forEach(departments::add);
        return departments;
    }

    public void addDepartment(Department department) {
       departmentsRepository.save(department);
    }

    public Department getDepartment(Long id) {
       return departmentsRepository.findById(id).orElse(null);
    }

    public void deleteDepartment(Long id) {
       departmentsRepository.deleteById(id);
    }

}
