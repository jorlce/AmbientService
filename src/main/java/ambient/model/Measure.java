package ambient.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "sensor_values")
public class Measure {


  @EmbeddedId
  private LecturaSensor MeasureId;

  @ManyToOne(targetEntity = SensorData.class)
  @MapsId("idSensor_ID")
  @JoinColumn(name = "idSensor_ID_Value", referencedColumnName = "idSensor_ID")
  private SensorData sensor;

  // protected String idSensor_ID_Value;
  protected float temperatura;
  protected float humedad;
  protected float nivelCO;
  protected float nivelCO2;
  protected float metano;



  public String getId() {
    // return sensor.getId();
    return MeasureId.idSensor_ID_Value;
  }

  public void setId(String id) {
    this.MeasureId.idSensor_ID_Value = id;
  }

  public Timestamp getLectura() {
    return MeasureId.lectura;
  }

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



  /*
   * public SensorData getSensorID() { return this.sensor; }
   * 
   * public void setSensorID(SensorData sensor) { this.sensor = sensor; }
   */


}


@Embeddable
class LecturaSensor implements Serializable {

  private static final long serialVersionUID = 1L;
  Timestamp lectura;
  String idSensor_ID_Value;

  // implements equals and hashCode

  public LecturaSensor() {}

  public LecturaSensor(Timestamp lectura, String sensorId) {
    this.lectura = lectura;
    this.idSensor_ID_Value = sensorId;
  }
}

