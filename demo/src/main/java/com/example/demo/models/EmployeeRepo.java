package com.example.demo.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
interface MyBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>{

    T findOne(ID id);

}

public interface EmployeeRepo extends MyBaseRepository<EmployeeDTO, Integer>{

    Page<EmployeeDTO> findAll(Pageable pageRequest);

    @Query(value = "SELECT * from demo.employees order by salary desc limit 1", nativeQuery = true)
    EmployeeDTO findEmployeeWithMaxSalary();

}
