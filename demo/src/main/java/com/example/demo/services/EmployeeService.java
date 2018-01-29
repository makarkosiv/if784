package com.example.demo.services;


import com.example.demo.models.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findAll();

    Page<EmployeeDTO> findAllToPage(Pageable pageable, int page);

    EmployeeDTO findOne(int id);

    EmployeeDTO save(EmployeeDTO employeeDTO);

    EmployeeDTO update(int id, EmployeeDTO employeeDTO);

    void delete(int id);

    EmployeeDTO changeName(int id, String name);

    EmployeeDTO annulBonus(int id);

    EmployeeDTO findRichest();

    double sumSalary();

    int countEmployee();

}


