package epita.conception.activators.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "capteurs")
public class Activator {
    @Id
    private String id;
    private String name;
    private String type;
    private ActivatorState state;
    private String content;
    
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

    public void setState(ActivatorState state) { this.state = state; }

    public ActivatorState getState() { return state; }

    public void setContent(String content) { this.content = content; }

    public String getContent() { return content; }


    // empty constructor
    public Activator()
    {
    }

    // regular constructor
    public Activator(String name, String type)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.state = ActivatorState.OFF ;
        this.content = "";
    }

    // copy constructor
    public Activator(String id, String name, String type, ActivatorState state, String content)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.state = state;
        this.content = content;
    }

}

