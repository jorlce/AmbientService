package ambient.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
import javax.persistence.MapsId;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "sensor_values")
public class Measure {

	
	/*@EmbeddedId
	private LecturaSensor MeasureId;*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		protected long idLectura;
		
	@ManyToOne(targetEntity=SensorData.class)
	//@MapsId("idSensor_ID")
	@JoinColumn(name="sensor_id_id_sensor_id", referencedColumnName="id_sensor_id")
		private SensorData sensor;
	
	
	
	//protected String idSensor_ID_Value;

	@Column(name = "timeLectura", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	@CreationTimestamp
	//@Temporal(TemporalType.TIMESTAMP)
		protected Timestamp timeLectura;
	
	protected float temperatura;
	protected float humedad;
	protected float nivelCO;
	protected float nivelCO2;
	protected float metano;
	
	
	
		
	public long getId() {
		//return sensor.getId();
		return idLectura;
	}

	public void setId(long id) {
		this.idLectura = id;
	}

	/*public Timestamp getLectura() {
		return lectura;
	}*/
	
	public float getTemperature() {
		return temperatura;
	}

	public void setTemperature(float temperatura) {
		this.temperatura = temperatura;
	}

	public float getHumedad() {
		return humedad;
	}

	public void setHumedad(float humedad) {
		this.humedad = humedad;
	}

	public float getNivelCO() {
		return nivelCO;
	}

	public void setNivelCO(float nivelCO) {
		this.nivelCO = nivelCO;
	}

	public float getNivelCO2() {
		return nivelCO2;
	}

	public void setNivelCO2(float nivelCO2) {
		this.nivelCO2 = nivelCO2;
	}

	public float getMetano() {
		return metano;
	}

	public void setMetano(float metano) {
		this.metano = metano;
	}

	
	public SensorData getSensorID() {
		return this.sensor;
	}

	public void setSensorID(SensorData sensor) {
		this.sensor = sensor;
	}
	
	
}

/*@Embeddable
class LecturaSensor implements Serializable {
   Timestamp lectura;
   String idSensor_ID_Value;

   //implements equals and hashCode
   
   @Override
   public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((idSensor_ID_Value == null) ? 0 : idSensor_ID_Value.hashCode());
	result = prime * result + ((lectura == null) ? 0 : lectura.hashCode());
	return result;
   }

   @Override
   public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	LecturaSensor other = (LecturaSensor) obj;
	if (idSensor_ID_Value == null) {
		if (other.idSensor_ID_Value != null)
			return false;
	} else if (!idSensor_ID_Value.equals(other.idSensor_ID_Value))
		return false;
	if (lectura == null) {
		if (other.lectura != null)
			return false;
	} else if (!lectura.equals(other.lectura))
		return false;
	return true;
   }

   public LecturaSensor(Timestamp lectura, String sensorId) {
       this.lectura = lectura;
       this.idSensor_ID_Value = sensorId;
   }
}*/

