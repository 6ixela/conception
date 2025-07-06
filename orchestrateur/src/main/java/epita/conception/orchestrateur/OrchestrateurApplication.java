package epita.conception.orchestrateur;

import epita.conception.orchestrateur.model.Capteur;
import epita.conception.orchestrateur.model.Device;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.LinkedList;
import java.util.List;


@EnableDiscoveryClient
@SpringBootApplication
public class OrchestrateurApplication {

    static public Device device = new Device();
    static public Double lum_threshold = 40.d;
    static public Double humid_threshold = 50.d;

    public static void main(String[] args) {
        SpringApplication.run(OrchestrateurApplication.class, args);

        while (true)
        {

            // TODO get List of all sensor
            List<Capteur> All_sensor = new LinkedList<>();

            for (Capteur sensor  : All_sensor)
            {
                if (device.capteurs.stream().noneMatch(capteur -> capteur.getId().equals(sensor.getId())))
                {
                    device.capteurs.add(sensor);
                }

                if (sensor.getType().equals("light") && sensor.getValeurs().getFirst().getValeur() < lum_threshold)
                {
                    // Send Request to ESP8266 to activate led
                }
                else if (sensor.getType().equals("light") && sensor.getValeurs().getFirst().getValeur() > lum_threshold)
                {
                    // Send Request to ESP8266 to deactivate led
                }

                if (sensor.getType().equals("humidity") && sensor.getValeurs().getFirst().getValeur() < humid_threshold)
                {
                    // Send Request to ESP8266 to display "vmc low speed"
                }
                else if (sensor.getType().equals("humidity") && sensor.getValeurs().getFirst().getValeur() > humid_threshold)
                {
                    // Send Request to ESP8266 to display "vmc high speed"
                }
            }
            
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

