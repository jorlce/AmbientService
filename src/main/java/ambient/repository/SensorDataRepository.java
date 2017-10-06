package ambient.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ambient.model.SensorData;

public interface SensorDataRepository extends CrudRepository<SensorData, Long> {
	List<SensorData> findAll();
	SensorData findBySensorlabel(String unIdSensor);
}
