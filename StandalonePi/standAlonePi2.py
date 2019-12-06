#SYSC3010_M1
#Daniel Gunther
#21-11-2019

import socket, sys, time, json, sqlite3, datetime, serial

#Connecting to the database and setting up a cursor
#so data can be stored in the database.
db = sqlite3.connect("OnBoardEPMD.db")
cursor = db.cursor()
#ser = serial.Serial('/dev/ttyACM0', 9600)
       
# This function initializes the tables in the database if they are not already initialized.
def setup():
    try:
        #Makes the data tables in the database.
        cursor.execute('CREATE TABLE IF NOT EXISTS queueDatabase(sequenceVal INT, packetData TEXT, PRIMARY KEY(sequenceVal));')
        db.commit()
    except:
        print('Failure while initializing the database')
    return

#Collects data from the serial input from the arduino.
def serialDataCollect():
    data = ser.readline().decode('utf-8')
    
    #Print for tracking incoming data when running the command.
    print(data)
    return data

#Parses and adds the data.
def parseAdd(packetData):
    cursor.execute('SELECT MAX(sequenceVal) FROM queueDatabase;')
    fetch = cursor.fetchone()
    if fetch == None:
        fetch = 0, 0
    newSequenceID = fetch[0] + 1    
    cursor.execute('INSERT into queueDatabase VALUES(?, ?);',( newSequenceID, packetData))
    db.commit() 
    return

#Test function checking the data stored in the queuedatabase table.
def testQueueDatabase():
    cursor.execute("SELECT * FROM queueDatabase;")
    for row in cursor:
        print(row)
    return