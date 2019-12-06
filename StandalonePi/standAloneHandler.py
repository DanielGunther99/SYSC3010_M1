#SYSC3010_M1
#Daniel Gunther
#06-12-2019

import socket, sys, time, serial, json, sqlite3, datetime
import standAlonePi as sap
from socket import  AF_INET, SOCK_DGRAM

#Main process for sending packets and receiving acknowledgements.

sap.sendDataFn()
while True:
    sap.recieveAck()