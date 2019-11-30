package mockobject;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import mockobject.udpReciever;
import mockobject.udpSender;

/** November 21, 2019
 *  Mock test object for design project group M1
 * @author Cameron McFadden
 */
public class MockObject {

    private int TestPort;
    private InetAddress host;
    private final int PACKETSIZE = 100;
    private int stubHR = 50;
    private int stubLong = 0;
    private int stubLat = 0;
    private int stubGas = 0;
    private boolean countup = true;
    
    public MockObject(int TestPort) {
        // constructor
        this.TestPort = TestPort; 
        try {
            host = InetAddress.getLocalHost();
            udpReciever reciever = new udpReciever(TestPort);
            udpSender sender = new udpSender();

            //DatagramPacket packet = new DatagramPacket(data, data.length, host, TestPort);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MockObject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * stub heart rate will increase from 50 to 120 then decrease back to 50
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
    public int getLat() {
        stubLat += 10;
        return stubLat;
    }
    
    public int getLong() {
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
    
    /**
     * sends stub data to server Pi for testing purposes
     */
    public void sendData() {
        String toSend = Integer.toString(getHR()) + Integer.toString(getLat()) + Integer.toString(getLong()) + Integer.toString(getGas());
        byte[] b = toSend.getBytes();
        DatagramPacket packet = new DatagramPacket(b, b.length, host, TestPort);
        System.out.println("Stub data sent.");
    }
    /**
     * Simulates an app requesting to connect
     */
    public void connectApp() {
        
    }
}
