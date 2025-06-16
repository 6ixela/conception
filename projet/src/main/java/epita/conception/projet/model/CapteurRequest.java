package epita.conception.projet.model;

import java.util.ArrayList;
import java.util.List;

public class CapteurRequest {

    private String name;
    private String type;

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

    public CapteurRequest()
    { 
    }

    public CapteurRequest(String name, String type)
    {
        this.name = name;
        this.type = type;
    }
}

