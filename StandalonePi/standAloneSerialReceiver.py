#SYSC3010_M1
#Daniel Gunther
#06-12-2019

import socket, sys, time, json, sqlite3, datetime
import standAlonePi2 as sap2

#Starts the serial receiving process.
sap2.setup()
print("Receiving on serial")
while True:
    sap2.parseAdd(sap2.serialDataCollect())
    time.sleep(5)
    