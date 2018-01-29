package com.example.demo;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.*;

import java.io.IOException;

import static org.junit.Assert.assertNull;

public abstract class AbstractBaseIT {

    @LocalServerPort
    protected int port;

    protected String rootMapping() {return "demo/api/employee";}

    protected String createURLWithPort(String uri) {
        return createURLWithPort(uri, true);
    }

    protected String createURLWithPort(String uri, boolean includeRootMapping) {
        String url = "http://localhost:" + port + '/';
        if (includeRootMapping) {
            url += rootMapping();
        }

        return url + '/' + uri;
    }

    protected HttpHeaders prepareHeaders(MediaType contentType) {
        HttpHeaders headers = new HttpHeaders();

        if (contentType == null) {
            headers.add("Content-Type", "application/json");
        } else {
            headers.setContentType(contentType);
        }

        return headers;
    }

    protected HttpHeaders prepareHeaders() {
        return prepareHeaders(null);
    }

    protected void assertEqualsJSON(Resource expectedJson, String actualJson) throws JSONException, IOException {
        if (actualJson == null) assertNull(expectedJson);
        else JSONAssert.assertEquals(parseResponse(expectedJson), actualJson, false);
    }

    protected String parseResponse(Resource resource) throws IOException {
        return IOUtils.toString(resource.getInputStream());
    }

    protected ResponseEntity<String> postRequest(String payLoad, TestRestTemplate restTemplate) {

        HttpEntity<String> entity = new HttpEntity<>(payLoad, prepareHeaders());

        return restTemplate.exchange(createURLWithPort(""),
                HttpMethod.POST, entity, String.class);
    }

    protected ResponseEntity<String> deleteRequest(int id, TestRestTemplate restTemplate) {
        HttpEntity<String> entity = new HttpEntity<>(prepareHeaders());
        return restTemplate.exchange(createURLWithPort(String.valueOf(id)), HttpMethod.DELETE, entity, String.class);
    }

    protected ResponseEntity<String> getRequest(int id, TestRestTemplate restTemplate) {
        HttpEntity<String> entity = new HttpEntity<>(prepareHeaders());
        return restTemplate.exchange(createURLWithPort(String.valueOf(id)), HttpMethod.GET, entity, String.class);
    }

}
