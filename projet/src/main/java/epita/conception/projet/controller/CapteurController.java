package epita.conception.projet.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epita.conception.projet.model.Capteur;
import epita.conception.projet.model.CapteurRequest;
import epita.conception.projet.model.Valeur;
import epita.conception.projet.model.ValueRequest;
import epita.conception.projet.service.CapteurService;


@RestController
@RequestMapping("/sensor")
public class CapteurController {

    @Autowired
    private CapteurService service;

    @GetMapping
    public ResponseEntity<List<Capteur>> getAll() {
        return ResponseEntity.ok(service.getAllCapteurs());
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

    @GetMapping("type/{type}")
    public List<Capteur> getByType(@PathVariable String type)
    {
        return service.getCapteursByType(type);   
    }

    @PostMapping
    public Capteur create(@RequestBody  CapteurRequest request) {
        Capteur res = new Capteur(request.name, request.type);
        return service.addCapteur(res);
    }

    @PostMapping("/{id}/value")
    public Capteur addValeur(@PathVariable String id, @RequestBody ValueRequest valueRequest) {
        Valeur value = new Valeur(valueRequest.value);
        // check argument, if value is null, return invalid arg
        return service.addValeur(id, value);
    }

    @GetMapping("/{id}/value/last")
    public Valeur getLastValue(@PathVariable String id) {
        return service.getDerniereValeur(id);
    }

    @GetMapping("/{id}/value/first")
    public Valeur getFirstValue(@PathVariable String id) {
        return service.getPremiereValeur(id);
    }
}
