package ambient.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Measure bean related to sensor_id table
@Entity
@Table(name = "sensor_id")
public class SensorData {

	@Id
	protected String id_Sensor_id;

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
		return id_Sensor_id;
	}

	public void setId(String id_Sensor_id) {
		this.id_Sensor_id = id_Sensor_id;
	}

	
}
