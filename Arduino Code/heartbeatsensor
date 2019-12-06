int heartsensorPin = A1;
float sensorValue = 0;
unsigned long currTime = 0;
int beatThreshold = 350;
bool beatCounted = false;
int beatCounter = 0;


void setup() {
  // put your setup code here, to run once:
  pinMode(heartsensorPin,INPUT);
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  currTime = millis();      
  beatCounter = 0;
  while(millis() < currTime + 10000){ //collect heartbeat for 10 seconds
    sensorValue = analogRead(heartsensorPin); 
    if(sensorValue> beatThreshold && !beatThreshold){// if the sensor picks up analog reading greater than threshold (heartbeat)
      beatCounter++; 
      beatThreshold= true;
    }
    if(sensorValue < beatThreshold){//if sensor picks up reading below threshold (noise) 
      beatThreshold = false;
    }
  }
  Serial.println(beatCounter);
}
