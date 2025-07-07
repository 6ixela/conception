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

const char* HOST =  "http://192.168.1.106:8082/";
const char* HOST_SENSOR = "http://192.168.1.106:8082/sensor";
const char* HOST_LISTEN = "http://192.168.1.106:8082/ord";

DHT dht(DHTPIN, DHTTYPE);
rgb_lcd lcd;
Si115X si1151;
ChainableLED leds(LED1, LED2, 1);


//const char* ssid = "Nerv02-bridge";
//const char* password = "projetIOT";

// const char* ssid = "xiaomi11T";
// const char* password = "alexisss";

#define SECRET_WIFI_SSID "robot-Wifi"
#define SECRET_WIFI_PASS "epita2024"

bool led_off = true;
int buttonState = 0;
String sensorReadings;
bool send_request = true;
int led_red   = 0;
int led_green = 0;
int led_bleu  = 0;
String temperature_status = "Normale";

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

    // waiting for wifi conection
    int i = 0;
    while (WiFi.status() != WL_CONNECTED) {
      delay(500);
      Serial.println(WiFi.status());
      if (i == 0)
      {
        lcd.clear();
        lcd.setCursor(0, 0);
        lcd.print("connection");
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
    Serial.println(WiFi.localIP());

    // register sensor to webserver
    String light = httpPOSTRequest(HOST_SENSOR, "{\"name\":\"light_1\",\"type\"=\"light\"}");
    String humid = httpPOSTRequest(HOST_SENSOR, "{\"name\":\"humidity_1\",\"type\"=\"humidity\"}");
    String temp  = httpPOSTRequest(HOST_SENSOR, "{\"name\":\"temp_1\",\"type\"=\"temperature\"}");

    Serial.println(light);
    Serial.println(humid);
    Serial.println(temp);

}

void loop() {
    // get new button state
    buttonState = digitalRead(BUTTON_PIN);
    // get new sensor values
    int light_value  = si1151.ReadHalfWord_VISIBLE();
    float temp_hum_val[2] = {0};
    dht.readTempAndHumidity(temp_hum_val);

    // handle button push
    if (buttonState == HIGH)
    {
      //leds.setColorRGB(0, led_red, led_green, led_bleu);  
    }
    // refresh lcd display
    lcd.setCursor(0, 0);
    lcd.print("Temperature ");
    lcd.setCursor(0, 1);
    lcd.print(temperature_status);
    lcd.print(" ");
    lcd.print(temp_hum_val[1]);
    lcd.print("C*");

    // send new info to server

    // listen for request from ord


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
      Serial.println("[HTTP]{GET} unable to connect");
    }
}

String httpPOSTRequest(const char* host, const char* body) {

    String res = "none";

    WiFiClient client;
    HTTPClient http;

    http.begin(client, host); 
    http.addHeader("Content-Type", "application/json");

    int httpCode = http.POST(body);
    
    if (httpCode > 0) {
      Serial.printf("[HTTP] POST... code: %d\n", httpCode);

      if (httpCode == HTTP_CODE_OK) {
        const String& payload = http.getString();
        Serial.println("received payload:\n<<");
        Serial.println(payload);
        Serial.println(">>");
        res = payload;
      }
    } else {
      Serial.printf("[HTTP] POST... failed, error: %s\n", http.errorToString(httpCode).c_str());
    }

    http.end();
    return res;
}

void ListenOn(const char* host)
{
    WiFiClient client;

    HTTPClient http;

    if (http.begin(client, host)) {

      Serial.printf("currnet hots = %d\n", client.available());
      if (client) {
        String s = client.readStringUntil('\n');  // read the message incoming from one of the clients
        s.trim();                                 // trim eventual \r
        Serial.println(s);                        // print the message to Serial Monitor
        client.print("echo: ");                   // this is only for the sending client
        //server.println(s);                        // send the message to all connected clients
        //server.flush();    
      }
    }
    else {q
      Serial.print("[Listen] can't connect");
    }
}

