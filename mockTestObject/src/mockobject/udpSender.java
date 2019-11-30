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
 * 11/21/2019
 *
 * @author Cameron McFadden
 */
public class udpSender {

    private DatagramSocket socket; 
    
    public udpSender() {
        // Constructor
    }

    private void sendPacket(DatagramPacket packet, int port) {
        try {
            socket = new DatagramSocket(port);
            socket.send(packet);
            System.out.println("Packet sent!");
        } catch (IOException ex) {
            Logger.getLogger(udpSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
