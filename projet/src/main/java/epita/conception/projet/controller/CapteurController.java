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
    public ResponseEntity<Capteur> getById(@PathVariable String id) {
        var capteur =  service.getCapteurById(id);
        if (capteur.isPresent()) {
            return ResponseEntity.ok(capteur.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("type/{type}")
    public ResponseEntity<List<Capteur>> getByType(@PathVariable String type)
    {
        return ResponseEntity.ok(service.getCapteursByType(type));   
    }

    @PostMapping
    public ResponseEntity<Capteur> create(@RequestBody  CapteurRequest request) {
        Capteur res = new Capteur(request.name, request.type);
        if (request.name == null || request.type == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.addCapteur(res));
    }

    @PostMapping("/{id}/value")
    public ResponseEntity<Capteur> addValeur(@PathVariable String id, @RequestBody ValueRequest valueRequest) {
        Valeur value = new Valeur(valueRequest.value);
        if (valueRequest.value == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.addValeur(id, value));
    }

    @GetMapping("/{id}/value/last")
    public ResponseEntity<Valeur> getLastValue(@PathVariable String id) {
        var value = service.getLastValeur(id);
        if (value == null) {
            return ResponseEntity.badRequest().build();
        }
        // check si le capteur existe
        return ResponseEntity.ok(value);
    }

    @GetMapping("/{id}/value/first")
    public ResponseEntity<Valeur> getFirstValue(@PathVariable String id) {
        // check si le capteur existe
        var value = service.getFirstValeur(id);
        if (value == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(value);
    }
}
