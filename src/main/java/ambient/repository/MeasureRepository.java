package ambient.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import ambient.model.Measure;
import ambient.model.SensorData;

public interface MeasureRepository extends CrudRepository<Measure, Long>	{
	List<Measure> findBySensor(SensorData sensor);
	//Measure findTopByOrderByTimelecturaDesc(String idUnse);
}


/*public interface MeasureRepository extends CrudRepository<Measure, Long>	{
	Measure findTopByOrderByTimelecturaDesc(String idUnse);
	
	List<Measure> findBySensor(SensorData sensor);
	
	@Query("SELECT * FROM Measure lm join lm.SensorData sd where" +
			"sd.sensorLabel= ?1 and lm.timelectura between ?2 and ?3")
	List<Measure> findBetweenTimelectura(String idsensor, Timestamp fecha1, Timestamp fecha2);

}*/
