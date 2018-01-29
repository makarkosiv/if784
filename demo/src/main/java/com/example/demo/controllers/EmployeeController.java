package com.example.demo.controllers;

import com.example.demo.models.EmployeeDTO;
import com.example.demo.services.EmployeeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "demo/api/employee")
@Api(value="onlinestore", description="Storing information of salary of employees")
public class EmployeeController {

    final EmployeeServiceImpl employeeService;

    @Autowired
    EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    @ApiOperation(value = "View a list of stored employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping
    public List<EmployeeDTO> getAllEmployee() {
        return employeeService.findAll();
    }

    @ApiOperation(value = "Search a page of stored employees for pagination",response = Page.class)
    @GetMapping("/page/{page}")
    public Page<EmployeeDTO> getAllEmployeeToPage(Pageable pageable, @PathVariable int page) {
        return employeeService.findAllToPage(pageable, page);
    }

    @ApiOperation(value = "Search a employee with an ID",response = EmployeeDTO.class)
    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable int id) {
        return employeeService.findOne(id);
    }

    @ApiOperation(value = "Search the employee with the largest salary",response = EmployeeDTO.class)
    @GetMapping("/richest")
    public EmployeeDTO getRichestEmployee() {
        return employeeService.findRichest();
    }

    @ApiOperation(value = "Calculation the summary salary",response = double.class)
    @GetMapping("/sum")
    public double getSumSalary() {
        return employeeService.sumSalary();
    }

    @ApiOperation(value = "Calculation the count of the employees",response = int.class)
    @GetMapping("/count")
    public int getCountEmployee() {
        return employeeService.countEmployee();
    }

    @ApiOperation(value = "Add a employee")
    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return employeeService.save(employeeDTO);
    }

    @ApiOperation(value = "Update the employee")
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable int id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return employeeService.update(id, employeeDTO);
    }

    @ApiOperation(value = "Update the name of the employee")
    @PutMapping("/{id}/{name}")
    public EmployeeDTO changeNameEmployee(@PathVariable int id, @PathVariable String name) {
        return employeeService.changeName(id, name);
    }

    @ApiOperation(value = "Annul the bonus of the employee")
    @PutMapping("/{id}/annul")
    public EmployeeDTO annulBonusEmployee(@PathVariable int id) {
        return employeeService.annulBonus(id);
    }

    @ApiOperation(value = "Delete the employee")
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable int id) {
        employeeService.delete(id);
    }
}
