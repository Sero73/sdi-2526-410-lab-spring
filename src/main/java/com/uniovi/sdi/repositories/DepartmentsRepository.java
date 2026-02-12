package com.uniovi.sdi.repositories;

import com.uniovi.sdi.entities.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentsRepository extends CrudRepository<Department,Long> {
}
