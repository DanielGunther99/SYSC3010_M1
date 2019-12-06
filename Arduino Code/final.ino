/***
 * Authors: John Singer and Vishal Ghaie
 * 
 * This code will retrieve data from a gas sensor, heart rate sensor, and a GPS Sensor
 * via serial connection. Once the data is recieved, the data will then be transmited 
 * to a paired Raspberry Pi for further processing.
 * 
 * played around with fake gps data
 * ADDING IN TIME FUNCTIONALITY
 * 
 * LAST FILE EDIT: December, 5,2019
 */
//Mandatory Includes
#include <Adafruit_GPS.h>//for gps
#include <SoftwareSerial.h>

 
//STUB TESTING (SET TO 1 IF USING STUB)
int smokeTEST = 0;
int heartTEST = 1; //CURRENTLY SENDING FAKE HEART RATE DATA BECAUSE SENSOR IS NOT HERE
int gpsTEST = 0;

//ARDUINO ID
#define ARDUINO_ID 0 //the ID of the arduino being used (Just in case if multiple Arduino's)
 
//Allowing the Sensor Analog input to be changed
#define GAS_SENSOR 0 //Analog Input for Gas Sensor
#define HEART_SENSOR A1; //Analog Input for Heart Rate Sensor
#define FAN 10; //Output Pin For Fan

//Initalising the Basic Variables to be Used By the Code
int arduinoID = ARDUINO_ID;
int gasSensor = GAS_SENSOR;
int heartSensor = HEART_SENSOR
int fan = FAN;

//Smoke Sensor Variables
int smokeReading = 0;
int smokeDetect;

//Heart Rate Sensor Variables
float sensorValue = 0;
unsigned long currTime = 0;
int beatThreshold = 350;
bool beatCounted = false;
int beatCounter = 0;

//GPS Sensor Variables
SoftwareSerial mySerial(8, 7);
Adafruit_GPS GPS(&mySerial);
#define GPSECHO  false

float personLong = 0;
float personLat = 0;

uint8_t personHour;
uint8_t personMin;
uint8_t personSec;

uint8_t personDay;
uint8_t personMonth;
uint8_t personYear;

uint32_t timer = millis();

//OPCode Variables
int heartRate = 0;
unsigned long arduinoRunTime; 

/**
 * SETUP
 * Functionality: This is where code will be ran once. So currently it is 
 * being used for serial port initalization and Heart Rate Sensor Initalization.
 * 
 * Variables Input: N/a
 * Variables Output: N/a
 * 
 * LAST EDIT: Novemeber, 18,2019
 */
void setup() {
  Serial.begin(9600); // initialize serial communication at 9600 bits per second
  pinMode(heartSensor,INPUT); //initialize Heart Rate Sensor
  pinMode(fan, OUTPUT); //Set pin as output for fan
  //GPS Sensor Initalisation
  GPS.begin(9600);
  GPS.sendCommand(PMTK_SET_NMEA_OUTPUT_RMCGGA);
  GPS.sendCommand(PMTK_SET_NMEA_UPDATE_1HZ);
  mySerial.println(PMTK_Q_RELEASE);
}

/**
 * LOOP
 * Functionality: This is where code will be ran continously. Currently it is being used
 * to retrieve data in Real-Time from the sensors periodically.
 * 
 * Variables Input: N/a
 * Variables Output: N/a
 * 
 * LAST EDIT: December, 2,2019
 */
void loop() {
  smokeDetect = Gas_Sensor(); //Smoke Detection Reading
  heartRate = Heart_Rate(); //Heart Rate Reading

  //Running the GPS
  char c = GPS.read();
  if ((c) && (GPSECHO))
    Serial.write(c);
  if (GPS.newNMEAreceived()) {
    if (!GPS.parse(GPS.lastNMEA()))
      return;  
  }

  // if millis() or timer wraps around, we'll just reset it
  if (timer > millis())  timer = millis();

  // approximately every 2 seconds or so, print out the current stats
  if (millis() - timer > 2000) {
    timer = millis(); // reset the timer
    GPSA(); //GPS Reading
  }
}

/**
  * GAS SENSOR:
  * The Gas sensor detects the smoke entering a mask for emergency personel. The sensor takes 20 seconds to turn on.
  * The sensor currently is detecting smoke in enviroments and is fully operational.
  * Variables Input: N/a
  * Variables Output: Returns 1 if there is smoke detected and returns 0 if there is no smoke.
  * 
  * LAST EDIT: December, 2,2019
  */
int Gas_Sensor(){
  //REAL CODE RUNNING
  if(smokeTEST == 0)smokeReading = analogRead(gasSensor); //Retrieve a sensor reading from the gasSensor 
  //STUB CODE RUNNING
  if(smokeTEST == 1){
    smokeReading++;
    if(smokeReading == 600 || smokeReading < 400) smokeReading = 400;
  }
  
  //If the sensor reading is above 200, there is a fire
  if(smokeReading >= 600){
    digitalWrite(fan, HIGH);
    return 1;
  }
  if(smokeReading < 600) digitalWrite(fan, LOW); 
  return 0;
}

/**
  * HEART RATE SENSOR:
  * The Heart Sensor detects the heart rate of the emergency personel. The sensor is currently being delivered
  * so the stub test is most likely going to run.
  * 
  * Variables Input: N/a
  * Variables Output: Returns the current heart rate of the person.
  * 
  * LAST EDIT: December, 5,2019
  */
int Heart_Rate(){
  currTime = millis();

  //Real Heart Rate Sensor Code
  if(heartTEST == 0){
    beatCounter = 0;
    while(millis() < currTime + 10000){ //collect heartbeat for 10 seconds
      sensorValue = analogRead(heartSensor); 
  
      //If the sensor picks up analog reading greater than threshold (heartbeat)
      if(sensorValue > beatThreshold && !beatThreshold){
        beatCounter++; 
        beatThreshold = true;
      }
      //If sensor picks up reading below threshold (noise)
      if(sensorValue < beatThreshold) beatThreshold = false;
    }
  }

  //Fake Heart Rate Sensor Data
  if(heartTEST == 1){
    beatCounter++;
    if(beatCounter < 40 || beatCounter > 80) beatCounter = 40;
  }
  
  return beatCounter;
}

/**
  * GPS (UNDER DEVELOPMENT):
  * This function will display date, time and location of the emergency responder. the stub test uses the gps time and date data
  * but has simulated coordinates that update.  * 
  * Variables Input: N/a
  * Variables Output: N/a
  * 
  * LAST EDIT: Novemeber, 18,2019
  */
void GPSA(){
    
    //Serial.print(GPS.hour, DEC); Serial.print(':');
    //Serial.print(GPS.minute, DEC); Serial.print(':');
    //Serial.print(GPS.seconds, DEC);
  
    //Serial.print("Date: ");
    //Serial.print(GPS.day, DEC); Serial.print('/');
    //Serial.print(GPS.month, DEC); Serial.print("/20");
    //Serial.println(GPS.year, DEC);
    if (gpsTEST==0) {
      int reallat1=(GPS.latitude/100);                //converts to degrees minutes from degrees minutes seconds
      float reallat2 =(GPS.latitude-(reallat1*100))/60;
      float reallat = reallat1+reallat2;
      int reallon1=(GPS.longitude/100);
      float reallon2 =(GPS.longitude-(reallon1*100))/60;
      float reallon = reallon1+reallon2;
      if(GPS.lat=='S'){                               // south and west are negative coordinates
        reallat=reallat*-1;
      }
       if(GPS.lon=='W'){
        reallon=reallon*-1;
      }
      
      //Long and Lat of person
      personLong = reallon;
      personLat = reallat;

      //Current Transmission Time
      personHour = GPS.hour;
      personMin = GPS.minute;
      personSec = GPS.seconds;

      //Current Transmission Date
      personDay = GPS.day;
      personMonth = GPS.month;
      personYear = GPS.year;

      //OpCode Generated
      opCode(arduinoID, smokeDetect, smokeReading, heartRate, personLat, personLong); //Generate OPCODE and send it over the SerialPort
    }

  if(gpsTEST == 1){
      //simulated long and Lat of person
      float personLat=43.1583;
      float personLong=80.5684;

      int ran=random(3);
      int ran2=random(3);
      if(ran==0){                     //randomly incrimenting lat and long
        personLat =  personLat+0.0001;
      }
      if(ran==1){
         personLat =  personLat-0.0001;
      }
      if(ran2==0){
        personLong =  personLong+0.0001;
      }
      if(ran2==1){
        personLong =  personLong-0.0001;
      }
      //Current Transmission Time
      personHour = GPS.hour;
      personMin = GPS.minute;
      personSec = GPS.seconds;

      //Current Transmission Date
      personDay = GPS.day;
      personMonth = GPS.month;
      personYear = GPS.year;

      //OpCode Generated
      opCode(arduinoID, smokeDetect, smokeReading, heartRate, personLat, personLong); //Generate OPCODE and send it over the SerialPort
  }
}

/**
  * OPCODE (UNDER DEVELOPMENT):
  * This function will print out the opcode to the serial port. This will print the 
  * ArduinoID (0 or 1), HeartRate(0 to 200), gasReading (0 to 900), gpsLat(CURRENTLY JUST 1), gpsLong(CURRENTLY JUST 1)
  * To seperate the data, there will be dashes in between each element of the opCode
  * 
  * 
  * Variables Input: 
  * ArduinoID: The ID of the current Ardino so the firefighter can be Identified
  * gasRead: The reading of the gas sensor currently
  * hearteRead: 
  * gpsReadLong: 
  * gpsReadLat:
  * timeStamp:
  * 
  * Variables Output: The data is transmited onto the Serial Bus.
  * 
  * LAST EDIT: Novemeber, 18,2019
  */
void opCode(int ArduinoID,int gasDetect, int gasRead, int heartRead, float lat, float lon){
    Serial.print(ArduinoID, DEC); 
    Serial.print("_");
    Serial.print("20");
    Serial.print(personYear, DEC);
    Serial.print("-");
    Serial.print(personMonth, DEC);
    Serial.print("-");
    Serial.print("0");
    Serial.print(personDay, DEC);
    Serial.print("_");
    Serial.print(personHour-5, DEC);
    Serial.print(":");
    Serial.print(personMin, DEC);
    Serial.print(":");
    Serial.print(personSec, DEC);
    Serial.print("_");
    Serial.print(heartRead, DEC);
    Serial.print("_");
    Serial.print(gasRead, DEC);
    Serial.print("_");
    Serial.print(lon, 4);
    Serial.print("_");
    Serial.print(lat, 4);
    Serial.print("\n");
    
}
