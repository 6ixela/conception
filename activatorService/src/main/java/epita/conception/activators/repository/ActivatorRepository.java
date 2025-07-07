package epita.conception.activators.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import epita.conception.activators.model.Activator;

public interface ActivatorRepository extends MongoRepository<Activator, String> {
}

