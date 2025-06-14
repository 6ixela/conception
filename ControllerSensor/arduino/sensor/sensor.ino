#include <ESP8266WiFi.h>
#include <DHT.h>
#include <ESP8266HTTPClient.h>


#define DHTPIN D4
#define DHTTYPE DHT11

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  dht.begin();

  // connexion au wifi ?
}

void loop() {
  // put your main code here, to run repeatedly:

}
