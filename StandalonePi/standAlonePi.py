#SYSC3010_M1
#Daniel Gunther
#21-11-2019

import socket, sys, time, json, sqlite3, datetime

#Connecting to the database and setting up a cursor
#so data can be stored in the database.
db = sqlite3.connect("OnBoardEPMD.db")
cursor = db.cursor()
"""
host = sys.argv[1]
textport = sys.argv[2]
ser = serial.Serial('/dev/ttyACM0', 9600)
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = (host, port)

while True:
    data = str(int(ser.readline(),16))
    print (data)
    if not len(data):
        break
    jfile = json.loads(data)

    break
"""

# This function initializes the tables in the database if they are not already initialized.
def setup():
	try:
		#Allows the creation of foreign key linked tables.
		cursor.execute("""PRAGMA foreign_keys = ON;""")
		#Makes the Personnel and sensorData tables in the database.
		cursor.execute("""CREATE TABLE IF NOT EXISTS personnel(EPMD_ID INT, personnel_Name TEXT, occupation TEXT, PRIMARY KEY(EPMD_ID));""")
		cursor.execute("""CREATE TABLE IF NOT EXISTS EPMDInfo(personID INT, currentTime DATE, heartRate FLOAT, GPSData FLOAT, gasVal INT, gasDetected BOOLEAN, FOREIGN  KEY(personID) REFERENCES personnel(EPMD_ID))""")
		db.commit()
	except:
		print('Failure while initializing the database')

	return

#Adds personnel to the database.
def addPersonnel(EPMD_ID, personnel_Name, occupation):
	#Add data filter
	#if (EPMD_ID > 1000)
	
	cursor.execute('''INSERT or IGNORE INTO personnel VALUES(?, ?, ?);''',(EPMD_ID, personnel_Name, occupation))
	db.commit()	
	return

#Adds EPMDInfo to the database.
def addEPMDInfo(personID, currentTime, heartRate, GPSData, gasVal, gasDetected):
	counter = 0
	if personID < 1000 or personID > 9999:
		counter = counter + 1
		print("Person ID is out of acceptable range.(1000 -  9999)")
	elif heartRate < 0 or heartRate > 1000:
		counter = counter + 1
		print("Heart rate is out of acceptable range.(0 - 1000)")
	elif GPSData < 0:
		counter = counter + 1
		print("GPS Data is out of acceptable range.(GPS Data > 0)")
	elif gasVal < 0 or gasVal > 2000:
		counter = counter + 1
		print("Gas Value is out of acceptable range.(0 - 2000)")
	elif gasDetected < 0 or gasDetected > 1:
		counter = counter + 1
		print("Gas detected is out of acceptable range.(0 - 1)")
	elif counter > 0:
		break
	else:
		cursor.execute('''INSERT INTO EPMDInfo VALUES(?, ?, ?, ? ,? ,?);''',(personID, currentTime, heartRate, GPSData, gasVal, gasDetected))
		db.commit()		
	return

#Collects data from the serial input from the arduino.
def serialDataCollect():
	ser = serial.Serial('/dev/ttyACM0', 9600)
	s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
	data = str(int(ser.readline(),16))
	return data

#Sends the data to the GUI Pi.
def sendData(host, textport, data):
	s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
	port = int(textport)
	server_address = (host, port)
	s.sendto(data.encode('utf-8'), server_address)	
	return
#Test function checking the personnel Table.
def testPersonnel():
	cursor.execute("SELECT * FROM personnel;")
	for row in cursor:
		print(row)
	return

#Test function checking the EPMDInfo Table.
def testEPMDInfo():
	cursor.execute("SELECT * FROM EPMDInfo;")
	for row in cursor:
		print(row)
	return

def parseData(data):
	
	return
