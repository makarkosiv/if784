package com.example.demo.model;

import com.example.demo.models.EmployeeDTO;
import com.example.demo.models.EmployeeRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Test
    public void whenFindById_thenReturnEmployee() {
        EmployeeDTO  expected= new EmployeeDTO();
        expected.setName("newEmpl");
        expected.setSalary(200);
        entityManager.persist(expected);
        entityManager.flush();

        EmployeeDTO actual = employeeRepo.findOne(expected.getId());

        assertTrue(actual.equals(expected));
    }

}