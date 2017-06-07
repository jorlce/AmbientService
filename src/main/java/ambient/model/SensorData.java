package ambient.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sensor_id")
public class SensorData {

	@Id
	protected String idSensor_ID;

	protected float latitud;
	protected float longitud;
	
	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	
	
	public String getId() {
		return idSensor_ID;
	}

	public void setId(String idSensor_ID) {
		this.idSensor_ID = idSensor_ID;
	}


	@OneToMany(mappedBy="sensor")
	private List<Measure> measures;

	
	
}
