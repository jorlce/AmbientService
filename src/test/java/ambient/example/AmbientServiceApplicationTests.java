package ambient.example;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ambient.AmbientServiceApplication;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

import org.junit.experimental.categories.Category;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class AmbientServiceApplicationTests {
	

	

	@Test
	public void update() throws ExecutionException {
	
		String prb = "{\"id\":\"SPA001\",\"Temperatura\":\"20\",\"Humedad\":\"60\",\"CO\":\"90\",\"CO2\":\"95\",\"Metano\":\"83\"}";
		
		Date globalStart, globalEnd;
		globalStart = new Date();
			
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
		headers.setContentType(MediaType.TEXT_PLAIN);
			
		
		ResponseEntity<Void> response=callEndpoint(headers, prb);
		
		assertEquals(201, response.getStatusCode().value());
		
		
		globalEnd = new Date();
		System.out.println("Total Time: "
				+ ((globalEnd.getTime() - globalStart.getTime()) / 1000)
				+ " seconds.");
		
	}

	
	private ResponseEntity<Void> callEndpoint(HttpHeaders headers, String lecturaPrb) {


		RestTemplate restTemplate=new RestTemplate();
		
		HttpEntity<String> entity = new HttpEntity<String>(lecturaPrb, headers);
			
	
		ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:8080/ambientService/uploadSensor/", entity, null);
		

		return response;

	}

}
