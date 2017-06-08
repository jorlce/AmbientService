package ambient.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


//Input example
//{"idSensor":"SPA001","latitud":0.,"patches":[[1,0],[2,2],[2,3]],"instructions":"NNESEESWNWW"}
@JsonPropertyOrder({"idSensor", "latitud", "longitud"})
public class AskSensor {
	
	@JsonProperty("idSensor")
	 private String idSensor = "";
	
	@JsonProperty("latitud")
	 private float latitud = 0;
	
	@JsonProperty("longitud")
	 private float longitud = 0;

	@JsonProperty("idSensor")
	public String getIdSensor() {
		return idSensor;
	}

	@JsonProperty("idSensor")
	public void setIdSensor(String idSensor) {
		this.idSensor = idSensor;
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
