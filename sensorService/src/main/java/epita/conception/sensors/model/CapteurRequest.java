package epita.conception.sensors.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class CapteurRequest {
    public String name;
    public String type;
    public String HostIp;
}
