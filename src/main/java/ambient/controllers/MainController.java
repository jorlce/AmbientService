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
	
	//Receive from the user program the new desired frequency for Arduino
	@RequestMapping(value = "/cambiafreq", 
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json")
	@ResponseBody
	public String cambiaFreqSensor(@RequestBody String sensorJson, HttpServletResponse response) {
		System.out.println(sensorJson);
		String confirma = "ERROR";
		try {
			confirma = mideService.cambiaFreq(sensorJson);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return confirma;
	}
	
	//Returns to Arduino its current frequency configuration
	//The response will be in a JSON like "{\"f\":1}"
	@RequestMapping(value = "/confSensorNet/{id}", 
			method = RequestMethod.GET,
			produces = "text/plain")
	@ResponseBody
	public String confSensorNet(@PathVariable("id") String id, HttpServletResponse response) {
		
		String conf = "";
		try {
			conf = mideService.configSensor(id);
			System.out.println(conf);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return conf;
	}

	
	//Returns the last values from the sensors of a given Arduino id
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
	
	//Create or Modify the attributes of an Arduino
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
	
	//Remove of the readings sensors from an Arduino, and remove the 
	// information of the given Arduino itself from Data Base
	@RequestMapping(value = "/deleteSensor/{id}",
			method = RequestMethod.DELETE,
			produces = "application/json")
	@ResponseBody
	public String deleteSensorData(@PathVariable("id") String id, HttpServletResponse response) {
		String confirm = "";
		try {
			confirm =mideService.delSensorData(id);
			
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return confirm;
	}
	
	//Returns in a JSON all the measurement dispositives saved 
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
	
	//Check the login and password of a given user
	@RequestMapping(value = "/login/{user}/{pass}",
			method = RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public String getLogin(@PathVariable("user") String user, @PathVariable("pass") String pass,
			HttpServletResponse response) {
		String login = "";
		try {
			login= mideService.credentials(user, pass);
			
			System.out.println(login);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return login;
	}
	
	//Returns all the readings of a measurement dispositive in a given period
	//of time
	@RequestMapping(value = "/chart/{id}/{period}",
			method = RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public String getChart(@PathVariable("id") String id, @PathVariable("period") String period,
			HttpServletResponse response) {
		String jsonEstadistica = "";
		try {
			jsonEstadistica= mideService.datosEstadistica(id, period);
			
			System.out.println(jsonEstadistica);
			response.setStatus(HttpStatus.CREATED.value());
		} catch (Exception e) {
			LOGGER.error(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return jsonEstadistica;
	}


	/*@RequestMapping("*")
	@ResponseBody
	public String fallbackMethod(){
		return "fallback method";
	}*/
}