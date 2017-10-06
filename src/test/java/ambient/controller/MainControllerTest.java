package ambient.controller;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import com.fasterxml.jackson.databind.ObjectMapper;

import ambient.controllers.MainController;



@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
//@WebMvcTest(MainController.class)
public class MainControllerTest {

	@Autowired
    private TestRestTemplate restTemplate;
	
	/*@Autowired
    MockMvc mockMvc;
	
    @MockBean
    CreateClientService createClientServiceMock;*/
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    public void testlistaSensores() throws Exception {
    	String arrayJson = "";
    	
        
        
		//arrayJson = restTemplate.postForEntity("http://localhost:8080/ambientService/listSensors/", String.class, null);

    }
    
    /*given(createClientServiceMock.createClient("Foo")).willReturn(new Client("Foo"));
    mockMvc.perform(post("/clients")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsBytes(new CreateClientRequest("Foo"))))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is("Foo")))
        .andExpect(jsonPath("$.number", notNullValue()));*/
}
