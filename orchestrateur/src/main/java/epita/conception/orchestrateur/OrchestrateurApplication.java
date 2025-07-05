package epita.conception.orchestrateur;

import epita.conception.orchestrateur.model.Activator;
import epita.conception.orchestrateur.model.Capteur;
import epita.conception.orchestrateur.model.Device;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@EnableDiscoveryClient
@SpringBootApplication
public class OrchestrateurApplication {

    static public Map<String, Device> knowned_device = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(OrchestrateurApplication.class, args);

        while (true)
        {
            // update Activator

            // update Sensors

            for (Device device : knowned_device.values()) {
                for (Activator activator : device.activators) {
                    // update value
                }

                for (Capteur capteur : device.capteurs) {
                    // update vaue
                }

                // apply senario


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

