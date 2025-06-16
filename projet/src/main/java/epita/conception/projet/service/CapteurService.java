package epita.conception.projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epita.conception.projet.repository.SensorRepository;

@Service
public class CapteurService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ValeurMesureeRepository valeurMesureeRepository;

    public List<Capteur> getAllCapteurs() {
        return capteurRepository.findAll();
    }

    public Optional<Capteur> getCapteurById(String id) {
        return capteurRepository.findById(id);
    }

    public Optional<ValeurMesuree> getDerniereValeurMesuree(String capteurId) {
        List<ValeurMesuree> valeurs = valeurMesureeRepository.findByCapteurIdOrderByTimestampDesc(capteurId);
        return valeurs.isEmpty() ? Optional.empty() : Optional.of(valeurs.get(0));
    }

    public Optional<ValeurMesuree> getPlusAncienneValeurMesuree(String capteurId) {
        List<ValeurMesuree> valeurs = valeurMesureeRepository.findByCapteurIdOrderByTimestampAsc(capteurId);
        return valeurs.isEmpty() ? Optional.empty() : Optional.of(valeurs.get(0));
    }

    public Capteur ajouterCapteur(Capteur capteur) {
        return capteurRepository.save(capteur);
    }

    public ValeurMesuree ajouterValeurMesuree(String capteurId, double valeur) {
        ValeurMesuree vm = new ValeurMesuree();
        vm.setCapteurId(capteurId);
        vm.setValeur(valeur);
        vm.setTimestamp(new Date());
        return valeurMesureeRepository.save(vm);
    }
}
