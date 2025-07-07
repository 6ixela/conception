package epita.conception.activators.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import epita.conception.activators.model.ActivatorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epita.conception.activators.model.Activator;
import epita.conception.activators.repository.ActivatorRepository;

@Service
public class ActivatorService {

    @Autowired
    private ActivatorRepository repository;

    public List<Activator> getAllActivator() {
        return repository.findAll();
    }

    public Optional<Activator> getActivatorById(String id) {
        return repository.findById(id);
    }

    public Activator addActivator(Activator activator) {
        return repository.save(activator);
    }

    public List<Activator> getActivatorsByType(String type) {
        return repository.findAll().stream()
                .filter(activator -> activator.getType().equals(type))
                .toList();
    }

    public Activator setContent(String activatorId, String content) {
        return repository.findById(activatorId).map(activator -> {
            activator.setContent(content);
            return repository.save(activator);
        }).orElse(null);
    }

    public String getContent(String id) {
        var activator = getActivatorById(id).orElse(null);
        if (activator == null) {
            return null;
        }
        return activator.getContent();
    }

    public Activator setStatus(String activatorId, ActivatorState status) {
        return repository.findById(activatorId).map(activator -> {
            activator.setState(status);
            return repository.save(activator);
        }).orElse(null);
    }

    public ActivatorState getStatus(String id) {
        var activator = getActivatorById(id).orElse(null);
        if (activator == null) {
            return null;
        }
        return activator.getState();
    }
}
