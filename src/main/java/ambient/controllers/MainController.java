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
			// Transform the string sent to the service in a Json
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode actualObj = mapper.readTree(sensorString);
		    System.out.println(sensorString);
		   
		    mideService.persistMeasure(actualObj);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			System.out.println(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
	
	
	@RequestMapping(value = "/consultSensor/{id}",
			method = RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public String getLecturaSensor(@PathVariable("id") String id, HttpServletResponse response) {
		String jsonSensor = "";
		try {
			jsonSensor= mideService.findSensorMeasure(id);
			 System.out.println(jsonSensor);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return jsonSensor;
	}
	
	@RequestMapping(value = "/getSensor/{id}",
			method = RequestMethod.POST,
			consumes = "application/json",
			produces="application/json")
	@ResponseBody
	public String getSensorData(@PathVariable("id") String id, HttpServletResponse response) {
		String jsonSensor = "";
		try {
			jsonSensor= mideService.findBySensorLabel(id);
			
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return jsonSensor;
	}
	
	@RequestMapping(value = "/addSensor",
			method = RequestMethod.PUT,
			consumes = "application/json")
	@ResponseBody
	public void putSensorData(@RequestBody String sensorJson, HttpServletResponse response) {
		
		try {
			mideService.addSensorData(sensorJson);
			
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
	
	@RequestMapping(value = "/deleteSensor/{id}",
			method = RequestMethod.PUT,
			consumes = "application/json")
	@ResponseBody
	public void deleteSensorData(@PathVariable("id") String id, HttpServletResponse response) {
		
		try {
			mideService.delSensorData(id);
			
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
	
	@RequestMapping(value = "/listSensors",
			method = RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public String getListaSensores(HttpServletResponse response) {
		String jsonSensor = "";
		try {
			jsonSensor= mideService.listaSensores();
			
			System.out.println(jsonSensor);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return jsonSensor;
	}
	


	/*@RequestMapping("*")
	@ResponseBody
	public String fallbackMethod(){
		return "fallback method";
	}*/
}