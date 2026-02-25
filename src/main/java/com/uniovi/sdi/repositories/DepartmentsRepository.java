package com.uniovi.sdi.repositories;

import com.uniovi.sdi.entities.Department;
import com.uniovi.sdi.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentsRepository extends CrudRepository<Department,Long> {
    Department findByCode(String code);

    Page<Department> findAll(Pageable pageable);

    @Query("SELECT r FROM Department r WHERE (LOWER(r.code) LIKE LOWER(?1) OR LOWER(r.name) LIKE LOWER(?1))")
    Page<Department> searchByCodeAndName(Pageable pageable, String searchText);

}
