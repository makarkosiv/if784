package com.example.demo.service;

import com.example.demo.models.EmployeeDTO;
import com.example.demo.models.EmployeeRepo;
import com.example.demo.services.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.example.demo.creator.CreatorEmployeeDTO.creator;
import static com.example.demo.models.EmployeeAssert.assertThatEmployee;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    private EmployeeDTO  employeeExpected;

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Before
    public void setUp() {
        employeeExpected= creator(30, "newEmpl", 300);
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        when(employeeRepo.findOne(employeeExpected.getId())).thenReturn(employeeExpected);
        Integer id = 30;
        EmployeeDTO employeeActual = employeeServiceImpl.findOne(id);

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(employeeRepo, times(1)).findOne(captor.capture());
        assertEquals(30, captor.getValue().intValue());
        assertEquals(employeeActual,employeeExpected);
    }

    @Test
    public void whenSave_thenEmployeeShouldBeReturn() {
        when(employeeRepo.findOne(employeeExpected.getId())).thenReturn(null);

        employeeServiceImpl.save(employeeExpected);

        ArgumentCaptor<EmployeeDTO> captor = ArgumentCaptor.forClass(EmployeeDTO.class);
        verify(employeeRepo, times(1)).save(captor.capture());

        EmployeeDTO employeeActual = captor.getValue();

        assertThatEmployee(employeeActual).hasName("newEmpl").hasSalary(300);
    }

}

