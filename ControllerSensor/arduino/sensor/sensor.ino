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
#define TEMP_HIGHT_THRESHOLD 30
#define LUMINOSITY_THRESHOLD 2000
#define BUTTON_PIN D7

DHT dht(DHTPIN, DHTTYPE);
rgb_lcd lcd;
Si115X si1151;
ChainableLED leds(LED1, LED2, 1);


#if defined(ARDUINO_ARCH_AVR)
    #define debug  Serial

#elif defined(ARDUINO_ARCH_SAMD) ||  defined(ARDUINO_ARCH_SAM)
#ifdef SerialUSB
    #define debug  SerialUSB
#else
    #define debug  Serial
#endif
#else
    #define debug  Serial
#endif

bool led_on = true;
int buttonState = 0; 

void setup() {
    // print setup
    debug.begin(115200);
    debug.println("DHTxx test!");
    Wire.begin();

    // humidity / temp sensor
    dht.begin();

    // set up the LCD's number of columns and rows:
    lcd.begin(16, 2);
    
    // luminosity setup
    uint8_t conf[4];

    // luminosity 
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

}

void loop() {

    buttonState = digitalRead(BUTTON_PIN);
    if (si1151.ReadHalfWord_VISIBLE() > LUMINOSITY_THRESHOLD) {
      led_on = true;
    }
    else
    {
      led_on = false;
    }
    if (buttonState == HIGH)
    {
      led_on = true;
    }

    //button handeling


    Serial.println("---humidity/Temp");
    float temp_hum_val[2] = {0};
    if (!dht.readTempAndHumidity(temp_hum_val)) {

        if(led_on) {
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
          lcd.print("Basse");
        }
        else if (temp_hum_val[1] > TEMP_HIGHT_THRESHOLD) {
          lcd.setCursor(0, 1);
          lcd.print("Elevee");
        }
        else {
          lcd.setCursor(0, 1);
          lcd.print("Normale");
        }

    } else {
        debug.println("Failed to get temprature and humidity value.");
    }

    // glocal delay
    delay(500);
}

