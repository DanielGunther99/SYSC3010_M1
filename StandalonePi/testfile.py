#SYSC3010_M1
#Daniel Gunther
#06-12-2019

import socket, sys, time, json, sqlite3, datetime
import standAlonePi2 as sap2

#Prints the contents of the local database for debug viewing.
sap2.testQueueDatabase()