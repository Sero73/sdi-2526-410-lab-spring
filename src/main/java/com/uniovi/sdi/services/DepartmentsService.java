package com.uniovi.sdi.services;

import com.uniovi.sdi.entities.Department;
import com.uniovi.sdi.entities.User;
import com.uniovi.sdi.repositories.DepartmentsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Department getDepartmentByCode(String code) {
        return departmentsRepository.findByCode(code);
    }

    public Page<Department> searchByCodeAndName(Pageable pageable, String searchText) {
        Page<Department> departments = new PageImpl<Department>(new LinkedList<Department>());
        searchText = "%" + searchText + "%";

        departments = departmentsRepository.searchByCodeAndName(pageable, searchText);
        return departments;
    }


    public Page<Department> findAllDepartments(Pageable pageable) {
        return departmentsRepository.findAll(pageable);
    }
}
