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

    // empty constructor
    public Capteur()
    {
        this.valeurs = new ArrayList<>();
    }

    // regular constructor
    public Capteur(String name, String type)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.valeurs = new ArrayList<>();
    }

    // copy constructor
    public Capteur(String id, String name, String type, List<Valeur> valeurs)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.valeurs = valeurs;
    }

}

