import standAlonePi as sap
import socket, sys, time, json, sqlite3, datetime

#Testing the setup function
sap.setup()
#(EPMDiD,
dan = "dan"
fighter = "fighter"
sap.addPersonnel(1000, dan, fighter)
sap.addPersonnel(-1000, 100, 1)

#Testing the addPersonnel function
sap.addPersonnel(2001, "dannoooo", "med") #Regular Values
sap.addPersonnel(-1000, "avocado", 999999.01)
sap.testPersonnel()

#Testing the addEPMDInfo function
sap.addEPMDInfo(1001, 92010, 120, 1000, 200, True)
sap.testEPMDInfo()

#Testing the add