import socket, sys, time, serial, json, sqlite3, datetime
import standAlonePi as sap


sap.setup()
while true:
    sap.parseData(sap.serialDataCollect())
    


print(data)
