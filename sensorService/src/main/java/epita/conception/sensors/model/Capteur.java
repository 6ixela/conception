package epita.conception.sensors.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "capteurs")
public class Capteur {
    @Id
    private String id;
    private String name;
    private String type;
    private String HostIp;
    private List<Valeur> valeurs = new ArrayList<>();

    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }
    
    public void setName(String Name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setValeurs(List<Valeur> valeurs) {
        this.valeurs = valeurs;
    }
    
    public List<Valeur> getValeurs() {
        return valeurs;
    }

    public void setHostIp(String valeur) {
        HostIp = valeur;
    }

    public String getHostIp() {
        return HostIp;
    }

    // empty constructor
    public Capteur()
    {
        this.valeurs = new ArrayList<>();
    }

    // regular constructor
    public Capteur(String name, String type, String HostIp)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.HostIp = HostIp;
        this.valeurs = new ArrayList<>();
    }

    // copy constructor
    public Capteur(String id, String name, String type, String HostIp, List<Valeur> valeurs)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.HostIp = HostIp;
        this.valeurs = valeurs;
    }

}

