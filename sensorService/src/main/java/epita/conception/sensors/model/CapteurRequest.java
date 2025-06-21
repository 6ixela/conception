package epita.conception.sensors.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "capteurs")
public class CapteurRequest {
    @Id
    public String name;
    public String type;
}
