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
import ambient.model.json.*;

import com.fasterxml.jackson.annotation.JsonView;

//Measure bean related to sensor_values table
@Entity
@Table(name = "sensorvalues")
public class Measure {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		protected long idLectura;
		
	//Foreign key from sensor_id table
	@ManyToOne(targetEntity=SensorData.class)
	@JoinColumn(name="sensoridfk", referencedColumnName="sensorlabel")
		private SensorData sensor;
	
	
	//Let the Database Server write the timestamp value for the reading
	@Column(name = "timelectura", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	@CreationTimestamp
	@JsonView(ViewsJson.Completa.class)
	//@Temporal(TemporalType.TIMESTAMP)
		protected Timestamp timelectura;
	
	@JsonView(ViewsJson.Normal.class)
	protected float temperatura; 
	@JsonView(ViewsJson.Normal.class)
	protected float humedad;
	@JsonView(ViewsJson.Normal.class)
	protected float nivelCO;
	@JsonView(ViewsJson.Normal.class)
	protected float nivelCO2;
	@JsonView(ViewsJson.Normal.class)
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
	
	public Timestamp getTimelectura() {
		return this.timelectura;
	}
	
}



