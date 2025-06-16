#include <ESP8266HTTPClient.h>
#include <ESP8266WiFi.h>

#include <Wire.h>
#include "rgb_lcd.h"
#include "Grove_Temperature_And_Humidity_Sensor.h"
#include "Si115X.h"
#include <ChainableLED.h>


#define DHTTYPE DHT11   // DHT 11
#define DHTPIN D3       // what pin we're connected to（DHT10 and DHT20 don't need define it）
#define LED1 D8
#define LED2 D4

#define HIMIDITY_THRESHOLD 70
#define TEMP_LOW_THRESHOLD 20
#define TEMP_HIGHT_THRESHOLD 27
#define LUMINOSITY_THRESHOLD 2000
#define BUTTON_PIN D7

const char* HOST =  "http://jigsaw.w3.org/HTTP/connection.html";

DHT dht(DHTPIN, DHTTYPE);
rgb_lcd lcd;
Si115X si1151;
ChainableLED leds(LED1, LED2, 1);


//const char* ssid = "Nerv02-bridge";
//const char* password = "projetIOT";

// const char* ssid = "xiaomi11T";
// const char* password = "alexisss";

#define SECRET_WIFI_SSID "iphone-aurelien"
#define SECRET_WIFI_PASS "Marson2019"

bool led_off = true;
int buttonState = 0; 
String sensorReadings;

void setup() {
    // print setup
    Serial.begin(115200);
    Serial.println("DHTxx test!");
    Wire.begin();

    // humidity / temp sensor
    dht.begin();

    // set up the LCD's number of columns and rows:
    lcd.begin(16, 2);
    
    // luminosity setup
    uint8_t conf[4];

    // luminosiled_ofty 
    Wire.begin();
    Serial.begin(115200);
    if (!si1151.Begin())
        Serial.println("WARNING Si1151 is not ready!");
    else
        Serial.println("Si1151 is ready!");

    // led init
    leds.init();

    // button
    pinMode(BUTTON_PIN, INPUT_PULLUP);


    //wifi setUP
    WiFi.begin(SECRET_WIFI_SSID, SECRET_WIFI_PASS);

    int i = 0;
    // waiting for wifi conection
    while (WiFi.status() != WL_CONNECTED) {
      delay(500);
      Serial.println(WiFi.status());
      if (i == 0)
      {
        lcd.clear();
        lcd.setCursor(0, 0);
        //lcd.print("connection");
      }
      else
      {
        lcd.print(".");
      }
      i = (i +1) % 7;
    }
    //print a new line, then print WiFi connected and the IP address
    lcd.setCursor(0, 0);
    lcd.print("connected       ");
    lcd.setCursor(0, 1);
    lcd.print(WiFi.localIP());
    delay(3000);
    lcd.clear();
    // Print the IP address
    Serial.println(WiFi.localIP());

}

void loop() {
    buttonState = digitalRead(BUTTON_PIN);
    if (si1151.ReadHalfWord_VISIBLE() > LUMINOSITY_THRESHOLD) {
      led_off = true;
    }
    else
    {
      led_off = false;
    }
    if (buttonState == HIGH)
    {
      httpGETRequest(HOST);
      //Serial.println(sensorReadings);
      //JSONVar myObject = JSON.parse(sensorReadings);
     
      led_off = false;
    }

    float temp_hum_val[2] = {0};
    if (!dht.readTempAndHumidity(temp_hum_val)) {

        if(led_off) {
            leds.setColorRGB(0, 0, 0, 0); 
        }
        else
        {
            if (temp_hum_val[0] > HIMIDITY_THRESHOLD) {
              leds.setColorRGB(0, 255, 0, 0);  
            }
            else {
                leds.setColorRGB(0, 48, 255, 117);  
            }
        }  

        lcd.setCursor(0, 0);
        lcd.print("Temperature ");
        if (temp_hum_val[1] < TEMP_LOW_THRESHOLD) {
          lcd.setCursor(0, 1);
          lcd.print("Basse  ");
        }
        else if (temp_hum_val[1] > TEMP_HIGHT_THRESHOLD) {
          lcd.setCursor(0, 1);
          lcd.print("Elevee ");
        }
        else {
          lcd.setCursor(0, 1);
          lcd.print("Normale");
        }

    } else {
        Serial.println("Failed to get temprature and humidity value.");
    }

    // glocal delay
    delay(500);
}

void httpGETRequest(const char* host) {

    WiFiClient client;

    HTTPClient http;

  if (http.begin(client, host)) {
    int httpCode = http.GET();
    Serial.printf("[HTTP] GET... code: %d\n", httpCode);
    if (httpCode == HTTP_CODE_OK ||  httpCode == HTTP_CODE_MOVED_PERMANENTLY) {
      String payload = http.getString();
      Serial.println(payload);
    }
    else
    {
      Serial.printf("[HTTP] GET... failed, error: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
  }
  else
  {
    Serial.println("[HTTP] unable to connect");
  }
}

