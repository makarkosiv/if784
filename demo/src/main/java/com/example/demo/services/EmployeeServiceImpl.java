package com.example.demo.services;
/**
 * The service contains business logic
 */

import com.example.demo.models.EmployeeDTO;
import com.example.demo.models.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo repository) {
        this.repository = repository;
    }

    public EmployeeServiceImpl() {}

    @Override
    public List<EmployeeDTO> findAll() {
        return repository.findAll();
    }
    @Override
    public Page<EmployeeDTO> findAllToPage(Pageable pageable, int page) {
        Pageable pageRequest = createPageRequest(page);
        Page<EmployeeDTO> searchResultPage = repository.findAll(pageRequest);
        return searchResultPage;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        return repository.save(employeeDTO);
    }

    @Override
    public EmployeeDTO update(int id, EmployeeDTO employeeDTO) {
        EmployeeDTO employeeDTONew = repository.findOne(id);
        employeeDTONew.setName(employeeDTO.getName());
        employeeDTONew.setSalary(employeeDTO.getSalary());
        return repository.save(employeeDTONew);
    }

    @Override
    public EmployeeDTO findOne(int id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public EmployeeDTO changeName(int id, String name) {
        EmployeeDTO employeeDTO = repository.findOne(id);
        employeeDTO.setName(name);
        return repository.save(employeeDTO);
    }

    /**
     *
     * @param id of employee who has been canceled bonus
     * @return employee with new salary = previous salary - 200
     */
    @Override
    public EmployeeDTO annulBonus(int id) {
        EmployeeDTO employeeDTO = repository.findOne(id);
        employeeDTO.setSalary(employeeDTO.getSalary() - 200);
        return repository.save(employeeDTO);
    }

    @Override
    public EmployeeDTO findRichest() {
        return repository.findEmployeeWithMaxSalary();
    }

    @Override
    public double sumSalary() {
        List<EmployeeDTO> list = repository.findAll();
        double sum = 0;
        for (EmployeeDTO employeeDTO : list) {
            sum += employeeDTO.getSalary();
        }
        return sum;
    }

    @Override
    public int countEmployee() {
        return repository.findAll().size();
    }

    private Pageable createPageRequest(int page) {
        return new PageRequest(page, 5);
    }
}
