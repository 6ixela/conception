package epita.conception.projet.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "valeurs")
public class ValueModel {
    @Id
    private String id;
    
    private String capteurId;
    private String capteurName;
    private Double valeur;
    private LocalDateTime timestamp;

    public ValueModel(String capteurId, Double valeur, LocalDateTime timestamp) {
        this.capteurId = capteurId;
        this.valeur = valeur;
        this.timestamp = timestamp;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCapteurId() {
        return capteurId;
    }
    public void setCapteurId(String capteurId) {
        this.capteurId = capteurId;
    }
    public String getCapteurName() {
        return capteurName;
    }
    public void setCapteurName(String capteurName) {
        this.capteurName = capteurName;
    }
    public Double getValeur() {
        return valeur;
    }
    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

