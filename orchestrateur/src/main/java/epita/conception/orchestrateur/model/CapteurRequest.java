package epita.conception.orchestrateur.model;

public class CapteurRequest {
    private int luminosite;
    private int humidite;

    // Getters et setters
    public int getLuminosite() {
        return luminosite;
    }

    public int getHumidite() {
        return humidite;
    }

    public void setLuminosite(int luminosite) {
        this.luminosite = luminosite;
    }

    public void setHumidite(int humidite) {
        this.humidite = humidite;
    }
}
