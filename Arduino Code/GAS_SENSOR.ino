/***
 * Vishal Ghaie, John Singer
 * 
 * The Gas sensor detects the smoke entering a mask for emergency personel. The sensor takes 20 seconds to turn on.
 * The sensor currently is detecting smoke and for Debug purposes, it prints via the serial port the current reading and if the 
 * sensor is reading smoke in the enviroment.
 * 
 * LAST EDIT: Novemeber, 11,2019
 */
#define GAS_SENSOR 0 //Analog Input for Gas Sensor

int gasSensor = GAS_SENSOR;
int smokeDetect;


void setup() {
  Serial.begin(9600); // initialize serial communication at 9600 bits per second
}

void loop() {
  smokeDetect = analogRead(gasSensor); //Retrieve a sensor reading from the gasSensor
  Serial.println(smokeDetect, BIN); //DEBUG: Display the reading in decimal for what the sensor is outputting (200 IS SMOKE)

  //Detecting smoke 
  if(smokeDetect >= 200){
    Serial.println("SMOKE_DETECTED");//DEBUG: Determine if smoke has been detected (SENSOR IS CURRENTLY WORKING)
  }
  delay(1000);//Delay the reading by 1 second;  
}
