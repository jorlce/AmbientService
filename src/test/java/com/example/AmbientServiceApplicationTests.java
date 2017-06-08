package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ambient.AmbientServiceApplication;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import static org.junit.Assert.assertEquals;

import org.junit.experimental.categories.Category;

import org.springframework.boot.test.IntegrationTest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmbientServiceApplication.class)
@WebAppConfiguration
public class AmbientServiceApplicationTests {

	@Test
	public void update() throws ExecutionException {
	
		
		Date globalStart, globalEnd;
		globalStart = new Date();
			
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
			
		
		ResponseEntity<Void> response=callEndpoint(headers, 40D);
		
		assertEquals(201, response.getStatusCode().value());
		
		
		globalEnd = new Date();
		System.out.println("Total Time: "
				+ ((globalEnd.getTime() - globalStart.getTime()) / 1000)
				+ " seconds.");
		
	}

	
	private ResponseEntity<Void> callEndpoint(HttpHeaders headers, Double temperature) {


		RestTemplate restTemplate=new RestTemplate();
		
		HttpEntity<Double> entity = new HttpEntity<Double>(temperature, headers);
			
	
		ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:8080/demo/upload/"+temperature.toString(), entity, null);

		return response;

	}

}
