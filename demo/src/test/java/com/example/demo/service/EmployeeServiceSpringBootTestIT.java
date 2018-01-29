package com.example.demo.service;

import com.example.demo.config.H2DbConfig;
import com.example.demo.models.EmployeeDTO;
import com.example.demo.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static com.example.demo.creator.CreatorEmployeeDTO.creator;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.example.demo")
@SpringBootTest(classes = {H2DbConfig.class })
@EnableConfigurationProperties
public class EmployeeServiceSpringBootTestIT {

    @Autowired
    EmployeeService employeeService;

    @Test
    public void shouldInsertAndGetOneAndDelete() throws SQLException {
        EmployeeDTO employeeDTO = creator(30, "newEmpl", 300l);

        employeeDTO = employeeService.save(employeeDTO);
        EmployeeDTO employeeDTOGet = employeeService.findOne(employeeDTO.getId());
        employeeService.delete(employeeDTO.getId());

        assertEquals(employeeDTO, employeeDTOGet);
        assertEquals(null, employeeService.findOne(employeeDTO.getId()));
    }


}

