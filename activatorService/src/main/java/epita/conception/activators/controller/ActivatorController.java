package epita.conception.activators.controller;

import java.util.List;

import epita.conception.activators.model.ActivatorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epita.conception.activators.model.Activator;
import epita.conception.activators.model.ActivatorRequest;
import epita.conception.activators.service.ActivatorService;


@RestController
@RequestMapping("/activator")
public class ActivatorController {

    @Autowired
    private ActivatorService service;

    @GetMapping
    public ResponseEntity<List<Activator>> getAll() {
        return ResponseEntity.ok(service.getAllActivator());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activator> getById(@PathVariable String id) {
        var capteur = service.getActivatorById(id);
        if (capteur.isPresent()) {
            return ResponseEntity.ok(capteur.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("type/{type}")
    public ResponseEntity<List<Activator>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(service.getActivatorsByType(type));
    }

    @PostMapping
    public ResponseEntity<Activator> create(@RequestBody ActivatorRequest request) {
        Activator res = new Activator(request.name, request.type);
        if (request.name == null || request.type == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.addActivator(res));
    }

    @PostMapping("/{id}/content")
    public ResponseEntity<Activator> changeContent(@PathVariable String id, @RequestBody String content) {
        if (content == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.setContent(id, content));
    }

    @GetMapping("/{id}/content")
    public ResponseEntity<String> getContent(@PathVariable String id) {
        var value = service.getContent(id);
        if (value == null) {
            return ResponseEntity.badRequest().build();
        }
        // check si le capteur existe
        return ResponseEntity.ok(value);
    }


    @PostMapping("/{id}/status")
    public ResponseEntity<Activator> changeStatus(@PathVariable String id, @RequestBody String status) {
        if (status == null) {
            return ResponseEntity.badRequest().build();
        }
        ActivatorState refineStatus = ActivatorState.OFF;
        switch (status) {
            case "ON":
                refineStatus = ActivatorState.ON;
                break;
            case "OFF":
                refineStatus = ActivatorState.OFF;
                break;
        }
        return ResponseEntity.ok(service.setStatus(id, refineStatus));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> getStatus(@PathVariable String id) {
        ActivatorState value = service.getStatus(id);
        if (value == null) {
            return ResponseEntity.badRequest().build();
        }
        // check si le capteur existe
        return ResponseEntity.ok(value.name());
    }

}
