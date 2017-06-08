package ambient.repository;

import org.springframework.data.repository.CrudRepository;

import ambient.model.SensorData;

public interface SensorDataRepository extends CrudRepository<SensorData, Long> {


}
