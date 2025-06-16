package epita.conception.projet.repository;


import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import epita.conception.projet.model.ValueModel;

public interface ValueRepository extends MongoRepository<ValueModel, String> {
    List<ValueModel> findByCapteurIdOrderByTimestampAsc(String capteurId);
    List<ValueModel> findByCapteurIdOrderByTimestampDesc(String capteurId);
}