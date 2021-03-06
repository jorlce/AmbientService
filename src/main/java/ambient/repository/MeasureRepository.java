package ambient.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;




import ambient.model.Measure;
import ambient.model.SensorData;

public interface MeasureRepository extends CrudRepository<Measure, Long>	{
	List<Measure> findBySensor(SensorData sensor); 
	List<Measure> findBySensorOrderByIdLecturaDesc(SensorData sensor);
	Measure findTopBySensorOrderByIdLecturaDesc(SensorData sensor);
	
	@Modifying
	@Query("delete Measure as lm where lm.sensor.sensorlabel= ?1")
	void removeLecturas(String idsensor);
	
	@Query("FROM Measure as lm where " +
			"lm.sensor.sensorlabel= ?1 and lm.timelectura between ?2 and ?3")
	List<Measure> entreLecturas(String idsensor, Timestamp fecha1, Timestamp fecha2);
}


