package epita.conception.projet.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epita.conception.projet.model.Capteur;
import epita.conception.projet.model.Valeur;
import epita.conception.projet.service.CapteurService;


@RestController
@RequestMapping("/Sensor")
public class CapteurController {

    @Autowired
    private CapteurService service;

    @GetMapping
    public List<Capteur> getAll() {
        return service.getAllCapteurs();
    }

    @GetMapping("/{id}")
    public Capteur getById(@PathVariable String id) {
        var capteur =  service.getCapteurById(id);
        if (capteur.isPresent()) {
            return capteur.get();
        } else {
            throw new RuntimeException("Capteur non trouvé");
        }
    }

    @PostMapping
    public Capteur create(@RequestBody Capteur capteur) {
        return service.addCapteur(capteur);
    }

    @PostMapping("/{id}/valeurs")
    public Capteur addValeur(@PathVariable String id, @RequestBody Valeur valeur) {
        return service.addValeur(id, valeur);
    }

    @GetMapping("/{id}/valeurs/derniere")
    public Valeur getDerniere(@PathVariable String id) {
        return service.getDerniereValeur(id);
    }

    @GetMapping("/{id}/valeurs/premiere")
    public Valeur getPremiere(@PathVariable String id) {
        return service.getPremiereValeur(id);
    }
}
