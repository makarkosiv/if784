package com.example.demo.it;

import com.example.demo.AbstractBaseIT;
import com.example.demo.DemoApplication;
import com.example.demo.config.H2DbConfig;
import com.example.demo.models.EmployeeDTO;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.example.demo.creator.CreatorEmployeeDTO.creator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DemoApplication.class, H2DbConfig.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext
public class EmployeeControllerRestTemplateIT extends AbstractBaseIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateEmployee() throws JSONException, IOException {
        //create an employee
        EmployeeDTO request =  creator(30, "newEmpl", 1000);

        ResponseEntity<String> response = postRequest(ResponseConverter.requestToJson(request), restTemplate);

        EmployeeDTO createResponse = ResponseConverter.jsonToResponse(response.getBody(), EmployeeDTO.class);

        final int createdId = createResponse.getId();

        Assert.assertTrue(createdId > 0);

        //delete the created employee
        response = deleteRequest(createdId, restTemplate);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertNull(response.getBody());

        //get the deleted employee and check response
        response = getRequest(createdId, restTemplate);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertNull(response.getBody());
    }

}
