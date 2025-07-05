package epita.conception.orchestrateur.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Device {
    public String Ip;
    public List<Capteur> capteurs;
    public List<Activator> activators;

    public Device()
    {
        this.capteurs = new ArrayList<>();
        this.activators = new ArrayList<>();
    }

    public Device(String ip)
    {
        this.Ip = ip;
        this.capteurs = new ArrayList<>();
        this.activators = new ArrayList<>();
    }

    // regular constructor
    public Device(String Ip, List<Activator> activators, List<Capteur> capteurs)
    {
        this.Ip = UUID.randomUUID().toString();
        this.activators = activators;
        this.capteurs = capteurs;
    }

}
