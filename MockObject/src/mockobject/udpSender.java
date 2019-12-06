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
        try {
            socket = new DatagramSocket();
        } catch (Exception ex) {
            Logger.getLogger(MockObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void sendPacket(DatagramPacket packet) {
        try {
            socket.send(packet);
            System.out.println("Packet sent on " + packet.getPort());
        } catch (IOException ex) {
            Logger.getLogger(udpSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
