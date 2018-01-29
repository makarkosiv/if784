package com.example.demo.controller;

import com.example.demo.controllers.EmployeeController;
import com.example.demo.models.EmployeeDTO;
import com.example.demo.services.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.demo.creator.CreatorEmployeeDTO.creator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerMvcIT {

    private EmployeeDTO employeeExpected;
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeServiceImpl service;

    private JacksonTester<EmployeeDTO> jsonEmployee;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        employeeExpected = creator(2,"newEmpl",300);
    }
    

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        given(service.findOne(2))
                .willReturn(employeeExpected);

        MockHttpServletResponse response = mvc.perform(
                get("/demo/api/it.employee/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonEmployee.write(employeeExpected).getJson());
    }

}