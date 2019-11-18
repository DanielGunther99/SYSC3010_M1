/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m1.standalonepi;

/**
 * SYSC3010
 * @author Daniel Gunther
 */

public class frameInterpret {
    
    public byte[] COMMANDCODE;
    public byte[] EPMDID;
    public byte[] GASVAL;
    public byte[] GASDETECTED;
    public byte[] LOCATION;
    public byte[] HEARTRATE;
    public byte[] TIMERECEIVED;
    
    public byte[] readData(byte[] data, int lowerBound, int upperBound) {
        int j = 0;
        byte[] newArray = new byte[upperBound - lowerBound];
        for(int i = lowerBound; i < upperBound; i ++) {
            j++;
            newArray[j] = data[i];
        }
        return newArray;
    }
    
    public void frameInterpret(byte[] packetData) {
        COMMANDCODE = readData(packetData, 0, 3);
        EPMDID = readData(packetData, 4 , 7);
        GASVAL = readData(packetData, 8, 11);
        GASDETECTED = readData(packetData, 12, 15);
        LOCATION = readData(packetData, 16, 23);
        HEARTRATE = readData(packetData, 24, 31);
        TIMERECEIVED = readData(packetData, 32, 39);
    }
    
}
