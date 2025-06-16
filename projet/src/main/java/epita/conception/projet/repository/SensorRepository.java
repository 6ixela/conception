package epita.conception.projet.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import epita.conception.projet.model.ValueModel;


public interface SensorRepository extends MongoRepository<ValueModel, String> {
    List<ValueModel> findByCapteurIdOrderByTimestampAsc(String capteurId);
    List<ValueModel> findByCapteurIdOrderByTimestampDesc(String capteurId);
}

