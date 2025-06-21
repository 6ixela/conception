package epita.conception.orchestrateur.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epita.conception.orchestrateur.model.CapteurRequest;

@RestController
@RequestMapping("/orchestrateur")
public class OrchestrateurController {

    @PostMapping("/traiter")
    public ResponseEntity<String> traiterCapteurs(@RequestBody CapteurRequest request) {
        String action = "";

        if (request.getLuminosite() < 300) {
            action += "LED allumée. ";
        } else {
            action += "LED éteinte. ";
        }

        if (request.getHumidite() < 50) {
            action += "Affichage LCD: valeur normale, VMC : faible vitesse.";
        } else {
            action += "Affichage LCD: valeur élevée, VMC : forte vitesse.";
        }

        return ResponseEntity.ok(action);
    }
}
