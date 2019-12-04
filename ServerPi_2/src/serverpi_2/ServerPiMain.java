package serverpi_2;

import java.net.DatagramPacket;
import static java.sql.Date.valueOf;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ServerPiMain {

    private static java.sql.Time time;
    private static ArrayList<Integer> ids = new ArrayList<Integer>();
    private java.sql.Date d;
    
    public static void main(String[] args) {
        udpReceiver listener = new udpReceiver(2949);
        udpSender sender = new udpSender();
        DatabaseHandler db = new DatabaseHandler();
        DatagramPacket packet;
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

        for (;;) {
            // Wait for a packet to come in
            listener.recieve();

            // Turn raw string into array of strings containing each data piece
            packet = listener.getPacket();
            String str = new String((packet.getData())).trim();
            String[] data = str.split("/");

            // Cast each piece of data to respective types
            int seqID = Integer.parseInt(data[0]);
            String identifier = data[1];
            int id = Integer.parseInt(data[2]);
            java.sql.Date date;
            
            try {
                date = valueOf(data[3]);
                time = new java.sql.Time(timeFormat.parse(data[4]).getTime());
                
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
            }

            int bpm = Integer.parseInt(data[5]);
            int ppm = Integer.parseInt(data[6]);
            float latitude = Float.parseFloat(data[7]);
            float longitude = Float.parseFloat(data[8]);

            if (!ids.contains(id)) {
                db.initDatabase(id);
                ids.add(id);
            }

            // Add data to database
            db.add(id, date, time, bpm, ppm, latitude, longitude);

            // Send acknowledgement
            String toSend = seqID + "-" + identifier;
            byte[] b = toSend.getBytes();
            DatagramPacket ack = new DatagramPacket(b, b.length, packet.getAddress(), 2950);
            sender.sendPacket(ack);
        }
    }

}
