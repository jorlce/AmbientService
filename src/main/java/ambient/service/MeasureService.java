package ambient.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ambient.model.Measure;
import ambient.model.SensorData;
import ambient.model.json.ViewsJson;
import ambient.repository.MeasureRepository;
import ambient.repository.SensorDataRepository;



@Service
public class MeasureService {

	//CRUD Repository for sensor_values table
	@Autowired
	private MeasureRepository measureRepository;
	
	//CRUD Repository for sensor_id table
	@Autowired
	private SensorDataRepository sensorDataRepository;
	
	
	@Transactional
	public void persistMeasure(JsonNode actualObj ){
		
		float mesValor; //Save float values from the Json
		Measure measure=new Measure();
		SensorData sensorData=new SensorData();
		
		//Build the measure bean using the Json nodes
		JsonNode jsonNode1 = actualObj.get("sensorlabel");
	    System.out.println(jsonNode1.textValue());
	    
		sensorData.setSensorlabel(jsonNode1.textValue());
		measure.setSensorID(sensorData);

		//measure.setId(jsonNode1.textValue());
		
		jsonNode1 = actualObj.get("Temperatura");
		System.out.println(jsonNode1.textValue());
		mesValor = Float.valueOf(jsonNode1.textValue()).floatValue();
		measure.setTemperatura(mesValor);
		
		jsonNode1 = actualObj.get("Humedad");
		System.out.println(jsonNode1.textValue());
		mesValor = Float.valueOf(jsonNode1.textValue()).floatValue();
		measure.setHumedad(mesValor);
		
		jsonNode1 = actualObj.get("CO");
		System.out.println(jsonNode1.textValue());
		mesValor = Float.valueOf(jsonNode1.textValue()).floatValue();
		measure.setNivelCO(mesValor);
		
		jsonNode1 = actualObj.get("CO2");
		System.out.println(jsonNode1.textValue());
		mesValor = Float.valueOf(jsonNode1.textValue()).floatValue();
		measure.setNivelCO2(mesValor);
		
		jsonNode1 = actualObj.get("Metano");
		System.out.println(jsonNode1.textValue());
		mesValor = Float.valueOf(jsonNode1.textValue()).floatValue();
		measure.setMetano(mesValor);
		
		//Insert the Bean values in Database
		measureRepository.save(measure);
		//sensorDataRepository.save(sensorData);
		
	}
	
	// Json expected is like
	// {"sensorLabel": "value", "Latitud":"value", "Longitud":"value"}
	
	public void persistSensor(JsonNode actualObj){
		float mesValor; //Save float values from the Json
		SensorData unSensorData=new SensorData();
		
		//Build the measure bean using the Json nodes
		JsonNode jsonNode1 = actualObj.get("sensorLabel");
	    System.out.println(jsonNode1.textValue());
	    
	    unSensorData.setSensorlabel(jsonNode1.textValue());
		
		jsonNode1 = actualObj.get("Latitud");
		System.out.println(jsonNode1.textValue());
		mesValor = Float.valueOf(jsonNode1.textValue()).floatValue();
		unSensorData.setLatitud(mesValor);
		
		jsonNode1 = actualObj.get("Longitud");
		System.out.println(jsonNode1.textValue());
		mesValor = Float.valueOf(jsonNode1.textValue()).floatValue();
		unSensorData.setLongitud(mesValor);
				
		sensorDataRepository.save(unSensorData);
	}
	
	public String datosSensor(String unIdSensor) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonSensor = "";
		SensorData unSensor = null;
		try {
			unSensor = sensorDataRepository.findBySensorlabel(unIdSensor);
			jsonSensor = mapper.writeValueAsString(unSensor);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonSensor;
	}
	
	public String listaSensores() {
		ObjectMapper mapper = new ObjectMapper();
		String arrayToJson = "";
		try {
			List<SensorData> listSensors = sensorDataRepository.findAll();
			arrayToJson = mapper.writeValueAsString(listSensors);		
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayToJson;
	}
	
	public String listaLecturas(SensorData unSensor){
		ObjectMapper mapper = new ObjectMapper();
		String arrayToJson = "";
		try {
			List<Measure> listMeasure = measureRepository.findBySensorOrderByIdLecturaDesc(unSensor);
			arrayToJson = mapper.writerWithView(ViewsJson.Completa.class).writeValueAsString(listMeasure);	
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayToJson;
		
	}
	
	//Returns the last measure data of the sensor with label idUnse
	public String findSensorMeasure(String unIdSensor) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonSensor = "";
		SensorData unSensor = null;
		Measure unMeasure = null;
		try {
			
			unSensor = sensorDataRepository.findBySensorlabel(unIdSensor);
			unMeasure = measureRepository.findTopBySensorOrderByIdLecturaDesc(unSensor);
			jsonSensor = mapper.writerWithView(ViewsJson.Normal.class).writeValueAsString(unMeasure);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonSensor;
	}
	
	//Returns the location data of the sensor with label unIdSensor
	public String findBySensorLabel(String unIdSensor) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonSensor = "";
		SensorData unSensor = null;
		try {
			unSensor = sensorDataRepository.findBySensorlabel(unIdSensor);
			jsonSensor = mapper.writeValueAsString(unSensor);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonSensor;
	}
	
	//Creates a new sensor in the database
	public void addSensorData(String sensorJson) {
		ObjectMapper mapper = new ObjectMapper();
		SensorData unSensor = null;
		try {
			unSensor = mapper.readValue(sensorJson, SensorData.class);
			sensorDataRepository.save(unSensor);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Creates a new sensor in the database
	public void delSensorData(String unIdSensor) {
		SensorData unSensor = null;

			unSensor = sensorDataRepository.findBySensorlabel(unIdSensor);
			sensorDataRepository.delete(unSensor);
		
	}
	
	/*public Measure consultaSensor(SensorData unSensor){
		return measureRepository.findTopByOrderByTimelecturaDesc(unSensor.getId());
	}*/
	
}
