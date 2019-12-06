#SYSC3010_M1
#Daniel Gunther
#21-11-2019

import socket, sys, time, json, sqlite3, datetime,serial
from uuid import getnode as get_mac

#Connecting to the database and setting up a cursor
#so data can be stored in the database.
db = sqlite3.connect("OnBoardEPMD.db")
cursor = db.cursor()
sendMAC = get_mac()
print (hex(sendMAC))

#Network setup.
sport = 2949
rport = 2950
server_address = ("192.168.43.6", sport)
rserver_address = ("", rport)
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
r = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
r.settimeout(10.0)
connected = False
while not connected:
    try:
        s.connect(server_address)
        connected = True
        r.bind(rserver_address)
        print("Connected.")
    except Exception as e:
        pass
    

#Sends the data to the server Pi.
def sendDataFn():
    while True: 
        cursor.execute('SELECT count(*) FROM queueDatabase;')
        count = cursor.fetchone()
        if  count == 0:
            time.sleep(2)
            continue
        else:
            cursor.execute('SELECT MIN(sequenceVal) FROM queueDatabase;')
            sendSequenceID = cursor.fetchone()
            cursor.execute('SELECT packetData FROM queueDatabase WHERE sequenceVal = %d' % sendSequenceID[0])
            sendData = cursor.fetchone()
            sendPacket = "%s_%s_%s" % (sendSequenceID[0], sendMAC, sendData[0])
            print(sendPacket)
            s.sendto(sendPacket.encode('utf-8'), server_address)    
            return

#Receives acknowledgement from the server pi and deletes the entry if a proper acknowledgement is received.
def recieveAck():
    try:
        print("Ack coming?")
        buf, hostIP = r.recvfrom(2950)
        print("Maybe!")
        try: 
          data = str(buf).split("-")
          sequenceVal = data[0]
          MACAddress = data[1]

          if sendMAC == MACAddress:
              print("got correct mac")
              cursor.execute('DELETE from queueDatabase WHERE sequenceVal = (SELECT MIN(sequenceVal) FROM queueDatabase);')
              db.commit()
              sendDataFn()
        except Exception as e:
            print("malformed packet received")
    except Exception as e:
            print("timeout exception")
            sendDataFn()
    return