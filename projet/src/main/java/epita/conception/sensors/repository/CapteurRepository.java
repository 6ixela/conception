package epita.conception.sensors.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import epita.conception.projet.model.Capteur;

public interface CapteurRepository extends MongoRepository<Capteur, String> {
}

