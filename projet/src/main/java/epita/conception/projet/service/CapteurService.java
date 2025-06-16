package epita.conception.projet.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epita.conception.projet.model.Capteur;
import epita.conception.projet.model.Valeur;
import epita.conception.projet.repository.CapteurRepository;

@Service
public class CapteurService {

    @Autowired
    private CapteurRepository repository;

    public List<Capteur> getAllCapteurs() {
        return repository.findAll();
    }

    public Optional<Capteur> getCapteurById(String id) {
        return repository.findById(id);
    }

    public Capteur addCapteur(Capteur capteur) {
        return repository.save(capteur);
    }

    public Capteur addValeur(String capteurId, Valeur valeur) {
        return repository.findById(capteurId).map(capteur -> {
            capteur.getValeurs().add(valeur);
            return repository.save(capteur);
        }).orElseThrow(() -> new RuntimeException("Capteur non trouvé"));
    }

    public Valeur getDerniereValeur(String id) {
        return getCapteurById(id)
                .orElseThrow(() -> new RuntimeException("Capteur non trouvé"))
                .getValeurs()
                .stream()
                .max(Comparator.comparing(Valeur::getDate))
                .orElse(null);
    }

    public List<Capteur> getCapteursByType(String type) {
        return repository.findAll().stream()
                .filter(capteur -> capteur.getType().equals(type))
                .toList();
    }

    public Valeur getPremiereValeur(String id) {
        return getCapteurById(id)
                .orElseThrow(() -> new RuntimeException("Capteur non trouvé"))
                .getValeurs()
                .stream()
                .min(Comparator.comparing(Valeur::getDate))
                .orElse(null);
    }
}