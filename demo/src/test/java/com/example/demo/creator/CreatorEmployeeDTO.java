package com.example.demo.creator;

import com.example.demo.models.EmployeeDTO;

public class CreatorEmployeeDTO {
    public static EmployeeDTO creator(int id, String name, double salary) {
        EmployeeDTO  employeeExpected= new EmployeeDTO();
        employeeExpected.setId(id);
        employeeExpected.setName(name);
        employeeExpected.setSalary(salary);
        return employeeExpected;
    }

}
