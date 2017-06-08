package ambient.controllers;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ambient.service.MeasureService;
import ambient.model.Measure;
import ambient.service.ConsultaService;


@RestController
@RequestMapping(path="/ambientService")
public class MainController {

	private static final Logger LOGGER = Logger.getLogger(MainController.class);
	
	/*@Autowired
	private ConsultaService conService;*/

	@Autowired
	private MeasureService mideService;

	@RequestMapping(value = "/uploadSensor", 
			method = RequestMethod.POST,
			consumes = "text/plain")
	public void sensorUpload(@RequestBody String sensorString, HttpServletResponse response) {

		try {
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode actualObj = mapper.readTree(sensorString);
		    System.out.println(sensorString);
		   
		    mideService.persistMeasure(actualObj);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

	}
	/*
	@RequestMapping(value = "/consultaSensor/{id}/",
			method = RequestMethod.POST,
			consumes = "text/plain",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Measure> getLecturaSensor((@PathVariable String id, HttpServletResponse response) {

		try {
			return conService(id);
			
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}*/

}