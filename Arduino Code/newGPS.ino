#include <Adafruit_GPS.h>
// Software Serial object after including the library
SoftwareSerial mySerial(3, 2);

//attach Serial object pins to GPS module
Adafruit_GPS GPS(&mySerial);

void setup() {
Serial.begin(9600); 
GPS.begin(9600); 
//These lines configure the GPS Module
GPS.sendCommand(PMTK_SET_NMEA_OUTPUT_RMCGGA); //Sets output to only RMC and GGA sentences
GPS.sendCommand(PMTK_SET_NMEA_UPDATE_1HZ); //Sets the output to 1/second.
GPS.sendCommand(PGCMD_ANTENNA); //report if antenna is connected or not
}

void loop() {
//start our GPS module, parse last received sentence 
GPS.parse(GPS.lastNMEA()); //This is going to parse the last NMEA sentence the Arduino has received
GPS.newNMEAreceived(); //This will return a boolean TRUE/FALSE depending on the case.
//Print the current date/time/etc
    Serial.print("\nTime: ");
    Serial.print(GPS.hour, DEC); Serial.print(':');
    Serial.print(GPS.minute, DEC); Serial.print(':');
    Serial.print(GPS.seconds, DEC); Serial.print('.');
    Serial.println(GPS.milliseconds);
    Serial.print("Date: ");
    Serial.print(GPS.day, DEC); Serial.print('/');
    Serial.print(GPS.month, DEC); Serial.print("/20");
    Serial.println(GPS.year, DEC);
    Serial.print("Fix: "); Serial.print((int)GPS.fix);
    Serial.print(" quality: "); Serial.println((int)GPS.fixquality);
//If GPS module has a fix, line by line prints the GPS information
    if (GPS.fix) {
      Serial.print("Location: ");
      Serial.print(GPS.latitude, 4); Serial.print(GPS.lat);
      Serial.print(", ");
      Serial.print(GPS.longitude, 4); Serial.println(GPS.lon);
      Serial.print("Location (in degrees, works with Google Maps): ");
      Serial.print(GPS.latitudeDegrees, 4);
      Serial.print(", ");
      Serial.println(GPS.longitudeDegrees, 4);
      Serial.print("Satellites: "); Serial.println((int)GPS.satellites);
    }
}
