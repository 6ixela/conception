package epita.conception.orchestrateur.model;

import java.time.LocalDateTime;

public class Valeur {
    private LocalDateTime date;
    private Double valeur;

    public Valeur() {
    }

    public Valeur(Double valeur) {
        this.date = LocalDateTime.now();
        this.valeur = valeur;
    }

    public Valeur(LocalDateTime date, Double valeur) {
        this.date = date;
        this.valeur = valeur;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate()
    {
        return date;
    }
}
