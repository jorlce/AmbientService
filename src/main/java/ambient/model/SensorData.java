package ambient.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Bean related to sensorId table
@Entity
@Table(name = "sensorid")
public class SensorData {

	@Id
	protected String sensorlabel;

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
	
	
	public String getSensorlabel() {
		return sensorlabel;
	}

	public void setSensorlabel(String sensorlabel) {
		this.sensorlabel = sensorlabel;
	}

	
}
