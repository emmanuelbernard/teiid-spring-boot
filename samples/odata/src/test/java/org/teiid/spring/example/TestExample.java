package org.teiid.spring.example;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class, TestConfiguration.class, RestTemplate.class })
@Ignore
public class TestExample {
	@Autowired
	RestTemplate web;
	
    @Test
    public void test() throws Exception{
    	ResponseEntity<String> response
    	  = web.getForEntity("http://localhost:8080/$metadata", String.class);
    	assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}
