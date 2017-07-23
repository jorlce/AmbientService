package ambient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import ambient.model.Measure;
import ambient.model.SensorData;
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
		JsonNode jsonNode1 = actualObj.get("id");
	    System.out.println(jsonNode1.textValue());
	    
		sensorData.setId(jsonNode1.textValue());
		measure.setSensorID(sensorData);

		//measure.setId(jsonNode1.textValue());
		
		jsonNode1 = actualObj.get("Temperatura");
		System.out.println(jsonNode1.textValue());
		mesValor = Float.valueOf(jsonNode1.textValue()).floatValue();
		measure.setTemperature(mesValor);
		
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
}
