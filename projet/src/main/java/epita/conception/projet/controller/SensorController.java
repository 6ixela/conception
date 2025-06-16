package epita.conception.projet.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import epita.conception.projet.repository.*;
import epita.conception.projet.model.SensorModel;
import epita.conception.projet.model.ValueModel;

@RestController
@RequestMapping("/Sensor")
public class SensorController {
    @Autowired
    private SensorRepository sensorRepository;

    public SensorController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    // liste tous les capterurs
    @GetMapping
    public List<ValueModel> listeProduits() {
        return sensorRepository.findAll();
    }

    // liste un capteur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<SensorModel> afficherUnProduit(@PathVariable String id) {
        var value = sensorRepository.findById(id);
        if (value != null) {
            var valueModel = value.get();
            SensorModel sensorModel = new SensorModel(valueModel.getCapteurId(), valueModel.getCapteurName());
            return ResponseEntity.ok(sensorModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // afficher la premiere valeur du capteur
    @GetMapping("/value")
    public ResponseEntity<String> afficherValeur() {
        var value = sensorRepository.findByCapteurIdOrderByTimestampAsc(null);
        if (value != null) {
            var firstValue = value.get(0);
            return ResponseEntity.ok(firstValue.getValeur().toString());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // afficher la derniere valeur du capteur
    @GetMapping("/lastValue")
    public ResponseEntity<String> afficherDerniereValeur() {
        var value = sensorRepository.findByCapteurIdOrderByTimestampDesc(null);
        if (value != null) {
            var firstValue = value.get(0);
            return ResponseEntity.ok(firstValue.getValeur().toString());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // ajoute un capteur
    @PostMapping
    public ResponseEntity<SensorModel> ajouterCapteur(@RequestBody SensorModel capteur) {
        SensorModel nouveauCapteur = sensorRepository.save(capteur);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauCapteur);
    }


    // ajoute un capteur par un Id
    /*@PostMapping("/{id}")
    public ResponseEntity<SensorModel> updateProduct(@PathVariable int id, @RequestBody SensorModel product) {

        SensorModel updatedProduct = sensorRepository.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }*/
}
