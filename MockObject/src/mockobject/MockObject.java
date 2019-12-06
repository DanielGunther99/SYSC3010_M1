package mockobject;

import java.net.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import static java.sql.Date.valueOf;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import mockobject.udpReciever;
import mockobject.udpSender;

/**
 * November 21, 2019 Mock test object for design project group M1
 *
 * @author Cameron McFadden
 */
public class MockObject {

    private int hostPort;
    private int destPort;
    private InetAddress host;
    private final int PACKETSIZE = 100;
    private int stubHR = 50;
    private float stubLong = 0;
    private float stubLat = 0;
    private int stubGas = 0;
    private int seqID = 0;
    public String identifier = "test";
    private boolean countup = true;
    private java.sql.Time t;
    private java.sql.Date d;
    private udpSender sender;
    private udpReciever reciever;
    public int id = 1;
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    public MockObject(int hostPort, int destPort) {
        // constructor
        this.hostPort = hostPort;
        this.destPort = destPort;
        try {
            t = new java.sql.Time(timeFormat.parse("00:00:00").getTime());
            d = new java.sql.Date(System.currentTimeMillis() - (2 * 86400000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            host = InetAddress.getLocalHost();
            sender = new udpSender();
            reciever = new udpReciever(hostPort);
        } catch (Exception ex) {
            Logger.getLogger(MockObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void incID() {
        id++;
    }

    public int getSeqID() {
        seqID++;
        return seqID;
    }

    /**
     * stub heart rate will increase from 50 to 120 then decrease back to 50
     *
     * @return stub heart rate
     */
    public int getHR() {

        if (countup) {
            stubHR++;
        } else {
            stubHR--;
        }

        if (stubHR == 120) {
            countup = false;
        } else if (stubHR == 50) {
            countup = true;
        }
        return stubHR;
    }

    public float getLat() {
        stubLat += 10;
        return stubLat;
    }

    public float getLong() {
        stubLong += 5;
        return stubLong;
    }

    public int getGas() {
        return stubGas;
    }

    // Method to test gas readings. Starts with low readings and increments with each call until stubGas = 300
    public int getGas_delay() {
        if (stubGas < 300) {
            stubGas++;
        }
        return stubGas;
    }

    public void reset() {
        stubHR = 50;
        stubLong = 0;
        stubLat = 0;
        stubGas = 0;
    }

    // Starts counting from 00:00:00
    public java.sql.Time getTime() {
        long t1 = t.getTime();
        t1 += 1000;
        t = new java.sql.Time(t1);
        return t;
    }

    public java.sql.Date getDate() {
        long d1 = d.getTime();
        d1 += 86400000;
        d = new java.sql.Date(d1);
        return d;
    }

    /**
     * sends stub data to server Pi for testing purposes
     */
    public void sendData() {
        seqID = getSeqID();
        String toSend = Integer.toString(seqID) + "_" + identifier + "_" + id + "_" + getDate() + "_" + getTime() + "_" + Integer.toString(getHR()) + "_" + Integer.toString(getGas()) + "_" + Float.toString(getLong()) + "_" + Float.toString(getLat());
        byte[] b = toSend.getBytes();
        DatagramPacket packet = new DatagramPacket(b, b.length, host, destPort);
        sender.sendPacket(packet);
    }

    public void sendApp() {
        String toSend = "1_App_1";
        byte[] b = toSend.getBytes();
        DatagramPacket packet = new DatagramPacket(b, b.length, host, destPort);
        sender.sendPacket(packet);
    }

    public boolean recieveACK() {
        reciever.recieve();
        DatagramPacket packet = reciever.getPacket();
        String str = new String(packet.getData()).trim();
        String expected = seqID + "-" + identifier;

        if (str.equals(expected)) {
            return true;
        }
        return false;
    }

    public void recieveData() {
        reciever.recieve();
    }
}
