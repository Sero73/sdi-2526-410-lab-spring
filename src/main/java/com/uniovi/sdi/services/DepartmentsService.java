package com.uniovi.sdi.services;

import com.uniovi.sdi.entities.Department;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DepartmentsService {

    private final List<Department> departments = new LinkedList<>();

    @PostConstruct
    public void init() {
        departments.add(new Department("Departamento1", "Nombre1", "Facultad1","111",1));
        departments.add(new Department("Departamento2", "Nombre2", "Facultad2","222",2));
        departments.add(new Department("Departamento3", "Nombre3", "Facultad3","333",3));
    }


    public List<Department> getDepartments() {
        return new LinkedList<>(departments);
    }

    //Para añadir con get -> http://localhost:8090/departments/add?code=Departamento4&name=Nombre4&faculty=Facultad4&phone=444&professors=4
    public void addDepartment(Department department) {
        departments.add(department);
    }

    public String getDepartment(String code) {

        for (Department department : departments) {
            if (department.getCode().equals(code)) {
                return department.toString();
            }
        }
        return "Code no encontrado";
    }

    public void deleteDepartment(String code) {
        Department d = departments.stream().filter(department -> department.getCode().equals(code)).findFirst().orElse(null);
        if (d != null) {
            departments.remove(d);
        }
    }

}
