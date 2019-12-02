/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockobject;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cameron McFadden
 */
public class udpReciever {

    public final static int PACKETSIZE = 100;
    private DatagramSocket socket;
    public DatagramPacket packet;
    private int  port;
    
    public udpReciever(int port) {
        try {
        	this.port = port;
            // construct the socket
            socket = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(udpReciever.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recieve() {
        try {
            System.out.println("Receiving on port " + port);
            DatagramPacket packet = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
            socket.receive(packet);

            //Make the packet data accessible from any class.
            this.packet = packet;
            //For Testing Purposes
            System.out.println(packet.getAddress() + " " + packet.getPort() + ": " + new String(packet.getData()).trim());

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
