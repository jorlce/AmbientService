package ambient.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


//Input example
//{"id":"SPA001","latitud":0.,"longitud"}
@JsonPropertyOrder({"sensorLabel", "latitud", "longitud"})
public class AskSensor {
	
	@JsonProperty("sensorLabel")
	 private String sensorLabel = "";
	
	@JsonProperty("latitud")
	 private float latitud = 0;
	
	@JsonProperty("longitud")
	 private float longitud = 0;

	@JsonProperty("idSensor")
	public String getSensorLabel() {
		return sensorLabel;
	}

	@JsonProperty("sensorLabel")
	public void setSensorLabel(String sensorLabel) {
		this.sensorLabel = sensorLabel;
	}

	@JsonProperty("latitud")
	public float getLatitud() {
		return latitud;
	}

	@JsonProperty("latitud")
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	@JsonProperty("longitud")
	public float getLongitud() {
		return longitud;
	}

	@JsonProperty("longitud")
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	
	
	
	

}
