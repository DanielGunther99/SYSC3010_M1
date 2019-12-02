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
 * LAST FILE EDIT: December, 2,2019
 */
//STUB TESTING (SET TO 1 IF USING STUB)
int smokeTEST = 0;
int heartTEST = 0;
int gpsTEST = 0;

//DRIVER TEST

 
//ARDUINO ID
#define ARDUINO_ID 0 //the ID of the arduino being used (Just in case if multiple Arduino's)
 
//Allowing the Sensor Analog input to be changed
#define GAS_SENSOR 0 //Analog Input for Gas Sensor
#define HEART_SENSOR A1; //Analog Input for Heart Rate Sensor

//Initalising the Basic Variables to be Used By the Code
int arduinoID = ARDUINO_ID;
int gasSensor = GAS_SENSOR;
int heartSensor = HEART_SENSOR

//Smoke Sensor Variables
int smokeReading = 0;
int smokeDetect;

//Heart Rate Sensor Variables
float sensorValue = 0;
unsigned long currTime = 0;
int beatThreshold = 350;
bool beatCounted = false;
int beatCounter = 0;

//GPS Sensor Variables CURRENTLY USING FAKE DATA*****************************
int personLong = 0;
int personLat = 0;

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
  GPS();
  arduinoRunTime = millis();
  opCode(arduinoID, smokeDetect, smokeReading, heartRate, personLong, personLat, arduinoRunTime); //Generate OPCODE and send it over the SerialPort
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
    if(smokeReading == 201 || smokeReading < 40) smokeReading = 40;
  }
  
  //If the sensor reading is above 200, there is a fire
  if(smokeReading >= 200)return 1;
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
  * LAST EDIT: December, 2,2019
  */
int Heart_Rate(){
  currTime = millis();      
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
  return beatCounter;
}

/**
  * GPS (UNDER DEVELOPMENT):
  * This function will...
  * 
  * Variables Input: N/a
  * Variables Output: N/a
  * 
  * LAST EDIT: Novemeber, 18,2019
  */
void GPS(){
  personLong = 0; //CURRENTLY USING FAKE DATA*****************************
  personLat = 0; //CURRENTLY USING FAKE DATA*****************************
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
void opCode(int ArduinoID,int gasDetect, int gasRead, int heartRead, int gpsReadLong, int gpsReadLat, unsigned long timeStamp){
    Serial.print(ArduinoID, DEC);
    Serial.print("-");
    Serial.print(timeStamp, DEC);
    Serial.print("-");
    Serial.print(heartRead, DEC);
    Serial.print("-");
    Serial.print(gasDetect, DEC);
    Serial.print("-");
    Serial.print(gasRead, DEC);
    Serial.print("-");
    Serial.print(gpsReadLat, DEC);
    Serial.print("-");
    Serial.print(gpsReadLong, DEC);
    Serial.print("\n");
  }
