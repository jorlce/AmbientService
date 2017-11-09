package ambient.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import ambient.model.Measure;
import ambient.model.SensorData;

public interface MeasureRepository extends CrudRepository<Measure, Long>	{
	List<Measure> findBySensor(SensorData sensor);
	//List<Measure> findTopBySensor(SensorData sensor); 
	List<Measure> findBySensorOrderByIdLecturaDesc(SensorData sensor);
	Measure findTopBySensorOrderByIdLecturaDesc(SensorData sensor);
	
	@Query("FROM Measure as lm where " +
			"lm.sensor.sensorlabel= ?1 and lm.timelectura between ?2 and ?3")
	List<Measure> entreLecturas(String idsensor, Timestamp fecha1, Timestamp fecha2);
}


/*public interface MeasureRepository extends CrudRepository<Measure, Long>	{
	//Measure findTopByOrderByTimelecturaDesc(String idUnse);
	
	List<Measure> findBySensor(SensorData sensor);
	
	
	@Query("select * from Measure me where me.sensoridfk= :idsensor order by timelectura DESC limit 1")
	Measure findLastMeasure(@Param("idsensor") String idUnse);
	
	@Query("SELECT * FROM Measure lm join lm.SensorData sd where" +
			"sd.sensorLabel= ?1 and lm.timelectura between ?2 and ?3")
	List<Measure> findBetweenTimelectura(String idsensor, Timestamp fecha1, Timestamp fecha2);

}
*/