#include "TinyGPS++.h"
#include "SoftwareSerial.h"

SoftwareSerial serial_connection(0,1);//RX=pin0  TX=pin1
TinyGPSPlus gps;//GPS object that will do all grunt work with NMEA

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  serial_connection.begin(9600);
  Serial.println("GPS Start");//Just show to the monitor that the sketch has started
}

void loop() {
  // put your main code here, to run repeatedly:
   while(serial_connection.available()){//While there is data coming from the GPS
    gps.encode(serial_connection.read());//This feeds the serial NMEA data into the library one char at a time
   }
    if(gps.location.isUpdated())//This will pretty much be fired all the time anyway but will at least reduce it to only after a package of NMEA data comes in
  {
    //Get the latest info from the gps object which it derived from the data sent by the GPS unit
    Serial.println("Satellite Count:");
    Serial.println(gps.satellites.value());/*
    Serial.println("Date");    
    Serial.println(gps.date.month());
    Serial.print(gps.date.day());
    Serial.print(gps.date.year());
    Serial.println("Time"); 
    Serial.println(gps.time.hour());
    Serial.print(gps.time.minute());
    Serial.print(gps.time.second());
    Serial.println("Latitude:");
    Serial.println(gps.location.lat(), 6);
    Serial.println("Longitude:");
    Serial.println(gps.location.lng(), 6);
 
    Serial.println("");*/
  }
}
