package epita.conception.activators.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Activator")
public class ActivatorRequest {
    @Id
    public String name;
    public String type;
}
