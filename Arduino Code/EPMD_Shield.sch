EESchema Schematic File Version 4
LIBS:EPMD_Shield-cache
EELAYER 30 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L Connector:Conn_01x06_Male J2
U 1 1 5DC099FA
P 5450 3350
F 0 "J2" H 5558 3731 50  0000 C CNN
F 1 "Analog In" H 5558 3640 50  0000 C CNN
F 2 "Connector_PinSocket_2.54mm:PinSocket_1x06_P2.54mm_Vertical" H 5450 3350 50  0001 C CNN
F 3 "~" H 5450 3350 50  0001 C CNN
	1    5450 3350
	1    0    0    -1  
$EndComp
$Comp
L Connector:Conn_01x03_Male J5
U 1 1 5DC0CFD3
P 6350 3050
F 0 "J5" H 6458 3331 50  0000 C CNN
F 1 "Right Side" H 6458 3240 50  0000 C CNN
F 2 "Connector_PinSocket_2.54mm:PinSocket_1x03_P2.54mm_Vertical" H 6350 3050 50  0001 C CNN
F 3 "~" H 6350 3050 50  0001 C CNN
	1    6350 3050
	1    0    0    -1  
$EndComp
$Comp
L Connector:Conn_01x04_Male J4
U 1 1 5DC0E873
P 6000 4400
F 0 "J4" H 6108 4681 50  0000 C CNN
F 1 "GPS" H 6108 4590 50  0000 C CNN
F 2 "Connector_PinSocket_2.54mm:PinSocket_1x04_P2.54mm_Vertical" H 6000 4400 50  0001 C CNN
F 3 "~" H 6000 4400 50  0001 C CNN
	1    6000 4400
	0    -1   -1   0   
$EndComp
$Comp
L Connector:Conn_01x04_Male J3
U 1 1 5DC0EE82
P 5450 4400
F 0 "J3" H 5558 4681 50  0000 C CNN
F 1 "Gas" H 5558 4590 50  0000 C CNN
F 2 "Connector_PinSocket_2.54mm:PinSocket_1x04_P2.54mm_Vertical" H 5450 4400 50  0001 C CNN
F 3 "~" H 5450 4400 50  0001 C CNN
	1    5450 4400
	0    -1   -1   0   
$EndComp
NoConn ~ 5650 2850
NoConn ~ 5650 3650
NoConn ~ 5650 3550
NoConn ~ 5650 3450
NoConn ~ 5650 3350
$Comp
L Connector:Conn_01x03_Male J6
U 1 1 5DC0FB99
P 6600 4400
F 0 "J6" H 6708 4681 50  0000 C CNN
F 1 "Heart Rate" H 6708 4590 50  0000 C CNN
F 2 "Connector_PinSocket_2.54mm:PinSocket_1x03_P2.54mm_Vertical" H 6600 4400 50  0001 C CNN
F 3 "~" H 6600 4400 50  0001 C CNN
	1    6600 4400
	0    -1   -1   0   
$EndComp
$Comp
L Connector:Conn_01x02_Male J7
U 1 1 5DC2511F
P 6950 4400
F 0 "J7" V 7104 4212 50  0000 R CNN
F 1 "Fan" V 7013 4212 50  0000 R CNN
F 2 "Connector_PinSocket_2.54mm:PinSocket_1x02_P2.54mm_Vertical" H 6950 4400 50  0001 C CNN
F 3 "~" H 6950 4400 50  0001 C CNN
	1    6950 4400
	0    -1   -1   0   
$EndComp
$Comp
L Connector:Conn_01x04_Male J1
U 1 1 5DC08B20
P 5450 2650
F 0 "J1" H 5558 2931 50  0000 C CNN
F 1 "Power" H 5558 2840 50  0000 C CNN
F 2 "Connector_PinSocket_2.54mm:PinSocket_1x04_P2.54mm_Vertical" H 5450 2650 50  0001 C CNN
F 3 "~" H 5450 2650 50  0001 C CNN
	1    5450 2650
	1    0    0    -1  
$EndComp
Wire Wire Line
	5650 4200 5650 4000
Wire Wire Line
	5650 4000 5100 4000
Wire Wire Line
	5100 4000 5100 2350
Wire Wire Line
	5100 2350 5750 2350
Wire Wire Line
	5750 2350 5750 2550
Wire Wire Line
	5750 2550 5650 2550
Wire Wire Line
	5650 4000 6200 4000
Wire Wire Line
	6200 4000 6200 4200
Connection ~ 5650 4000
Wire Wire Line
	6200 4000 6600 4000
Connection ~ 6200 4000
Wire Wire Line
	5800 2650 5800 2300
Wire Wire Line
	5800 2300 5050 2300
Wire Wire Line
	5050 2300 5050 4050
Wire Wire Line
	5050 4050 5350 4050
Wire Wire Line
	5350 4050 5350 4200
Wire Wire Line
	5350 4050 5900 4050
Wire Wire Line
	5900 4050 5900 4200
Connection ~ 5350 4050
NoConn ~ 5550 4200
Wire Wire Line
	5450 4200 5450 3750
Wire Wire Line
	5450 3750 5350 3750
Wire Wire Line
	5350 3750 5350 2900
Wire Wire Line
	5350 2900 5750 2900
Wire Wire Line
	5750 2900 5750 3150
Wire Wire Line
	5750 3150 5650 3150
NoConn ~ 5650 2750
Wire Wire Line
	6700 4200 6700 3700
Wire Wire Line
	6700 3700 5750 3700
Wire Wire Line
	5750 3700 5750 3250
Wire Wire Line
	5750 3250 5650 3250
Connection ~ 5900 4050
Wire Wire Line
	6500 4050 6500 4200
Wire Wire Line
	5900 4050 6500 4050
Wire Wire Line
	6600 4000 6600 4200
Wire Wire Line
	6500 4050 6950 4050
Wire Wire Line
	6950 4050 6950 4200
Connection ~ 6500 4050
Wire Wire Line
	7050 2950 6550 2950
Wire Wire Line
	7050 2950 7050 4200
Wire Wire Line
	6100 4200 6100 3900
Wire Wire Line
	6100 3900 7000 3900
Wire Wire Line
	6000 4200 6000 3850
Wire Wire Line
	6000 3850 6950 3850
$Comp
L power:+5V #PWR?
U 1 1 5DC34122
P 5650 2550
F 0 "#PWR?" H 5650 2400 50  0001 C CNN
F 1 "+5V" H 5665 2723 50  0000 C CNN
F 2 "" H 5650 2550 50  0001 C CNN
F 3 "" H 5650 2550 50  0001 C CNN
	1    5650 2550
	1    0    0    -1  
$EndComp
Connection ~ 5650 2550
$Comp
L power:GND #PWR?
U 1 1 5DC345A3
P 5650 2650
F 0 "#PWR?" H 5650 2400 50  0001 C CNN
F 1 "GND" H 5655 2477 50  0000 C CNN
F 2 "" H 5650 2650 50  0001 C CNN
F 3 "" H 5650 2650 50  0001 C CNN
	1    5650 2650
	1    0    0    -1  
$EndComp
Connection ~ 5650 2650
Wire Wire Line
	5650 2650 5800 2650
Wire Wire Line
	6950 3050 6550 3050
Wire Wire Line
	6950 3050 6950 3850
Wire Wire Line
	7000 3900 7000 3150
Wire Wire Line
	7000 3150 6550 3150
$EndSCHEMATC
