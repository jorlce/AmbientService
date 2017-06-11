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

	@Autowired
	private MeasureRepository measureRepository;
	
	@Autowired
	private SensorDataRepository sensorDataRepository;
	
	
	@Transactional
	public void persistMeasure(JsonNode actualObj ){
		
		Measure measure=new Measure();
		SensorData sensorData=new SensorData();
		
		
		JsonNode jsonNode1 = actualObj.get("id");
	    System.out.println(jsonNode1.textValue());
	    
		sensorData.setId(jsonNode1.textValue());
		//sensorDataRepository.f
		measure.setSensorID(sensorData);

		//measure.setId(jsonNode1.textValue());
		
		jsonNode1 = actualObj.get("Temperatura");
		System.out.println(jsonNode1.textValue());
		float mesValor = Float.valueOf(jsonNode1.textValue()).floatValue();
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
		
		
		
		
	
		
		
		measureRepository.save(measure);
		//sensorDataRepository.save(sensorData);
		
	}
}
