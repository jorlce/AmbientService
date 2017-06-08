package ambient.repository;

import org.springframework.data.repository.CrudRepository;

import ambient.model.Measure;

public interface MeasureRepository extends CrudRepository<Measure, Long> {

}
