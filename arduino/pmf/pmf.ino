#include <math.h>
#include "ArduinoJson.h"
#include "Adafruit_Sensor.h"
#include "DHT.h"


#define DHTPIN 2
#define DHTTYPE DHT22
#define PIN_NTC 0

double Rref = 10000.0; //Résistance de référence à 25°C
double V_IN = 5.0; //Alimentation électrique

//Information de la thermistance
double A_1 = 3.354016E-3;
double B_1 = 2.569850E-4;
double C_1 = 2.620131E-6;
double D_1 = 6.383091E-8;

//Consigne
double order = 20;

double SteinhartHart(double R)
{
  //Division de l'équation en 4 parties. La premiere est
  //uniquement A1
  double equationB1 = B_1 * log(R/Rref);
  double equationC1 = C_1 * pow(log(R/Rref), 2);
  double equationD1 = D_1 * pow(log(R/Rref), 3);
  double equation = A_1 + equationB1 + equationC1 + equationD1;
  return pow(equation, -1);

}

DHT dht(DHTPIN, DHTTYPE);

void setup() {
  pinMode(12,OUTPUT);
  Serial.begin(9600);
  dht.begin();

}

void loop() {
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();
  //Calcul de la tension sur la borne analogique
  double valeurAnalog = analogRead(PIN_NTC);
  double V =  valeurAnalog / 1024 * V_IN;

  //Calcul de la résistance de la thermistance
  double Rth = (Rref * V ) / (V_IN - V);
  //Calcul de la température en kelvin( Steinhart and Hart)
  double kelvin = SteinhartHart(Rth);
  double celsius = kelvin - 273.15; //Conversion en celsius
  // Reading temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  float h = dht.readHumidity();
  // Read temperature as Celsius (the default)
  float t = dht.readTemperature();
  // Read temperature as Fahrenheit (isFahrenheit = true)
  float f = dht.readTemperature(true);

  // Check if any reads failed and exit early (to try again).
  if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  // Compute heat index in Fahrenheit (the default)
  float hif = dht.computeHeatIndex(f, h);
  // Compute heat index in Celsius (isFahreheit = false)
  float hic = dht.computeHeatIndex(t, h, false);

  float temperature = t;
  float humidite = h/100;

  float alpha = ((17.27 * temperature)/(237.7 + temperature)) + log(humidite);
  float rosee = (237.7 * alpha) / (17.27 - alpha);
  root["temp"] = celsius;
  root["hum"] = h;
  root["inside"] = t;
  root["rosee"] = rosee;
  root["door"] = random(0,1);
  root.printTo(Serial);
  Serial.println("");
  
  // send instruction to arduino
  String reader = Serial.readString();
  StaticJsonBuffer<200> jsonBufferReader;
  JsonObject& object = jsonBufferReader.parseObject(reader);
  if (object["type"] == "order") {
    order = object["value"];
  }
  // control
  float difference = celsius - order;
  if(difference > 0.2) {
    digitalWrite(12, HIGH);
  } else if (difference < -0.2) {
    digitalWrite(12, LOW);
  }
  delay(3000);
  
}
